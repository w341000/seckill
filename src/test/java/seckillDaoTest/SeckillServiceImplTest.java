package seckillDaoTest;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.pojo.Seckill;
import org.seckill.service.SeckillService;
import org.springframework.util.Assert;

public class SeckillServiceImplTest extends BaseJunit4Test {
	@Resource
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		List<Seckill> list = seckillService.getSeckillList();
		System.out.println(list);
	}

	@Test
	public void testGetSeckillById() {
		Seckill seckill = seckillService.getSeckillById((long) 1000);
		System.out.println(seckill);
	}
	//测试代码完整逻辑,可重复执行
	@Test
	public void testSeckillLogic() {
		long id=10011;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		if(exposer.isExposed()) {
			System.out.println(exposer);
			long phone=1;
			String md5=exposer.getMd5();
			try {
				SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone,md5);
				System.out.println(seckillExecution);
			} catch (SeckillCloseException e) {
				e.printStackTrace();
			} catch (RepeatKillException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("秒杀尚未开始");
		}
	}

	@Test
	public void testExecuteSeckill() {
		SeckillExecution seckillExecution = null;
		try {
			seckillExecution = seckillService.executeSeckill((long) 1000, 1, seckillService.exportSeckillUrl((long) 1000).getMd5());
		} catch (SeckillCloseException e) {
			e.printStackTrace();
		} catch (RepeatKillException e) {
			e.printStackTrace();
		}
		Assert.notNull(seckillExecution, "must not null");
		 SeckillExecution seckill = seckillService.executeSeckill((long) 1000, 1, "abc123");
		 
	}

}
