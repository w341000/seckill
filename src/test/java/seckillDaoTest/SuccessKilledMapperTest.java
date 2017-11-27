package seckillDaoTest;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.seckill.mapper.SuccessKilledMapper;
import org.seckill.pojo.SuccessKilled;

public class SuccessKilledMapperTest extends BaseJunit4Test {
	@Resource
	private SuccessKilledMapper successKilledMapper;

	@Test
	public void testSelectByPrimaryKey() {
		SuccessKilled successKilled = successKilledMapper.selectByPrimaryKey((long)1, (long)1);
		System.out.println(successKilled);
	}

	@Test
	public void testSelectByIdWithSeckill() {
		SuccessKilled successKilled = successKilledMapper.selectByIdWithSeckill((long)1000,(long)2);
		System.out.println(successKilled);
	}

}
