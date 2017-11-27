package seckillDaoTest;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.seckill.mapper.SeckillMapper;
import org.seckill.pojo.Seckill;

public class SeckillMapperTest extends  BaseJunit4Test{
	@Resource
	private SeckillMapper seckillMapper; 
	@Test
	public void test() {
		int number = seckillMapper.reduceNumber((long)1000, new Date());
		System.out.println(number);
	}

}
