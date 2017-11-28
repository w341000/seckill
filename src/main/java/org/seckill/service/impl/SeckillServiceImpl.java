package org.seckill.service.impl;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private SeckillMapper seckillMapper;
	@Resource
	private SuccessKilledMapper successKilledMapper;
	// md5混淆
	private final String slat = "adwadwa2342!@#$%^&185~";

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
		Seckill seckill = seckillMapper.selectByPrimaryKey(seckillId);
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
			throw new SeckillException("seckill data error");
		}
		if(successKilledMapper.selectByPrimaryKey(seckillId, userPhone)!=null) {
			throw new RepeatKillException("重复秒杀");
		}
		try {
			// 执行秒杀逻辑:库存-1 记录购买行为
			Date now = new Date();
			int updateRows = seckillMapper.reduceNumber(seckillId, now);
			if (updateRows <= 0) {// 秒杀结束
				throw new SeckillCloseException("秒杀结束");
			} else {
				// 记录购买行为
					successKilledMapper.insert(new SuccessKilled(seckillId, userPhone, (byte) 0));
					
					SuccessKilled successKilled = successKilledMapper.selectByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);//success
				
			}
		} catch (SeckillCloseException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SeckillException("Seckill inner exception:" + e.getMessage(), e);
		}
	}

}
