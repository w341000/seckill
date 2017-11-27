package org.seckill.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.seckill.pojo.SuccessKilled;
import org.seckill.pojo.SuccessKilledExample;

public interface SuccessKilledMapper {
    long countByExample(SuccessKilledExample example);

    int deleteByExample(SuccessKilledExample example);

    int deleteByPrimaryKey(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    int insert(SuccessKilled record);

    int insertSelective(SuccessKilled record);

    List<SuccessKilled> selectByExample(SuccessKilledExample example);

    SuccessKilled selectByPrimaryKey(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);

    int updateByExampleSelective(@Param("record") SuccessKilled record, @Param("example") SuccessKilledExample example);

    int updateByExample(@Param("record") SuccessKilled record, @Param("example") SuccessKilledExample example);

    int updateByPrimaryKeySelective(SuccessKilled record);

    int updateByPrimaryKey(SuccessKilled record);
    /**
     *  根据秒杀商品id获取所有相关秒杀信息
     * @param seckillId 秒杀商品id
     * @return 秒杀明细,关联了秒杀商品
     */
    SuccessKilled selectByIdWithSeckill(@Param("seckillId") Long seckillId,@Param("userPhone") Long userPhone);
}