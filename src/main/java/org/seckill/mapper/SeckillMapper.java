package org.seckill.mapper;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.seckill.pojo.Seckill;
import org.seckill.pojo.SeckillExample;

public interface SeckillMapper {
    long countByExample(SeckillExample example);

    int deleteByExample(SeckillExample example);

    int deleteByPrimaryKey(Long seckillId);

    int insert(Seckill record);

    int insertSelective(Seckill record);

    List<Seckill> selectByExample(SeckillExample example);

    Seckill selectByPrimaryKey(Long seckillId);

    int updateByExampleSelective(@Param("record") Seckill record, @Param("example") SeckillExample example);

    int updateByExample(@Param("record") Seckill record, @Param("example") SeckillExample example);

    int updateByPrimaryKeySelective(Seckill record);

    int updateByPrimaryKey(Seckill record);
    /**
     * 减少商品库存
     * @param seckillId 商品id
     * @param killTime 秒杀时间
     * @return sql语句影响的行数
     */
    int reduceNumber(@Param("seckillId") Long seckillId,@Param("killTime")Date killTime);
}