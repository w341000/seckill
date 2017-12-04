package org.seckill.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.seckill.dao.JedisClient;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.mapper.SeckillMapper;
import org.seckill.mapper.SuccessKilledMapper;
import org.seckill.pojo.Seckill;
import org.seckill.pojo.SeckillExample;
import org.seckill.pojo.SuccessKilled;
import org.seckill.service.SeckillService;
import org.seckill.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

@Service
public class SeckillServiceImpl implements SeckillService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private SeckillMapper seckillMapper;
	@Resource
	private SuccessKilledMapper successKilledMapper;
	// md5混淆
	private final String slat = "adwadwa2342!@#$%^&185~";
	
	private static final String SECKILL_REDIS_KEY="seckill:";
	@Resource
	private JedisClient jedisClient;

	@Override
	public List<Seckill> getSeckillList() {
		List<Seckill> list = seckillMapper.selectByExample(new SeckillExample());
		return list;
	}

	@Override
	public Seckill getSeckillById(Long seckillId) {
		Seckill seckill = seckillMapper.selectByPrimaryKey(seckillId);
		return seckill;
	}

	@Override
	public Exposer exportSeckillUrl(Long seckillId) {
		Seckill seckill=getSeckill(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date now = new Date();
		if (!inTime(now, startTime, endTime)) {// 秒杀时间错误
			return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), endTime.getTime());
		}
		// 转换特点字符串
		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}
	/**
	 * 从缓存获取秒杀商品
	 */
	private Seckill getSeckill(Long seckillId) {
		Seckill seckill=null;
		seckill=getFromCache(seckillId);
		if(seckill==null) {//miss from cache
			seckill = seckillMapper.selectByPrimaryKey(seckillId);
			putToCache(seckill);
		}
		return seckill;
		 
	}
	//从缓存中取
	private Seckill getFromCache(Long seckillId) {
		try {
			String json= jedisClient.get(SECKILL_REDIS_KEY+seckillId);//先从缓存取
			return  StringUtils.isEmpty(json)?null:JsonUtils.jsonToPojo(json, Seckill.class);
		}catch (Exception e) {
			logger.debug("can not get seckill  cause:"+e.getMessage());
			return null;
		}
	}
	//放入缓存
	private void putToCache(Seckill seckill) {
		try {
			if(seckill!=null) {
				jedisClient.set(SECKILL_REDIS_KEY+seckill.getSeckillId(), JsonUtils.objectToJson(seckill));//放入redis缓存
				int timeout=60*60;
				jedisClient.expire(SECKILL_REDIS_KEY+seckill.getSeckillId(), timeout);//设置过期时间
			}
		}catch (Exception e) {
			logger.debug("can not get seckill  cause:"+e.getMessage());
		}
	}

	private String getMD5(Long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	/**
	 * 判断当前时间是否在开始时间与结束时间之间
	 */
	private boolean inTime(Date now, Date startTime, Date endTime) {
		return now.getTime() >= startTime.getTime() && now.getTime() <= endTime.getTime();
	}

	@Override
	@Transactional
	public SeckillExecution executeSeckill(Long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException {

		if (md5 == null || !md5.equals(getMD5(seckillId))) {// md5不符合
			throw new SeckillException("数据被篡改");
		}
		if(successKilledMapper.selectByPrimaryKey(seckillId, userPhone)!=null) {
			throw new RepeatKillException("重复秒杀");
		}
		try {
			// 执行秒杀逻辑:库存-1 记录购买行为
			Date now = new Date();
			// 记录购买行为
			successKilledMapper.insert(new SuccessKilled(seckillId, userPhone, (byte) 0));
			int updateRows = seckillMapper.reduceNumber(seckillId, now);//减库存,热点商品竞争
			if (updateRows <= 0) {	
				throw new SeckillCloseException("秒杀结束");//秒杀结束,回滚事物
			}  
			SuccessKilled successKilled = successKilledMapper.selectByIdWithSeckill(seckillId, userPhone);
			return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);//success,commit
		} catch (SeckillCloseException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SeckillException("Seckill inner exception:" + e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public void save(Seckill seckill) {
		seckillMapper.insert(seckill);
	}

}
