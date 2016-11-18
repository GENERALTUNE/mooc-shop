package org.generaltune.dao;

import org.apache.ibatis.annotations.Param;
import org.generaltune.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * Created by zhumin on 2016/11/14.
 */
public interface SeckillDao {
    /**
     * 减库存，
     * @param seckillId
     * @param killTime
     * @return 如果影响行数>1， 表示更新的记录行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 新增一条秒杀
     * @param name
     * @param number
     * @param startTime
     * @param endTime
     * @return
     */
    int insertSeckill(@Param("name") String name, @Param("number") int number, @Param("startTime") Date startTime, @Param("endTime")Date endTime);
}
