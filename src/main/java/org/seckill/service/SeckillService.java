package org.seckill.service;

import java.util.List;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.pojo.Seckill;

public interface SeckillService {
	List<Seckill> getSeckillList();

	Seckill getSeckillById(Long seckillId);

	/**
	 * 输出秒杀接口地址,否则输出系统时间和秒杀时间
	 */
	Exposer exportSeckillUrl(Long seckillId);

	/**
	 * 执行秒杀操作
	 * 
	 * @return 秒杀状态信息
	 * @throws SeckillException
	 *             如果秒杀发生错误
	 * @throws SeckillCloseException
	 *             如果秒杀被关闭
	 * @throws RepeatKillException
	 *             如果重复秒杀
	 */
	SeckillExecution executeSeckill(Long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException;
}
