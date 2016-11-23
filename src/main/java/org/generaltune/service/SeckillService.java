package org.generaltune.service;

import org.apache.ibatis.annotations.Param;
import org.generaltune.dto.Exposer;
import org.generaltune.dto.SeckillExecution;
import org.generaltune.entity.Seckill;
import org.generaltune.exception.RepeatKillException;
import org.generaltune.exception.SeckillCloseException;
import org.generaltune.exception.SeckillException;

import java.util.List;

/**
 * 业务接口：站在“使用者”角度设计接口
 * 三个方面：方法定义粒度，参数，返回类型（return 类型/ 异常）
 * Created by zhumin on 2016/11/15.
 *
 */
public interface SeckillService {
    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList(int offset, int limit);

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 删除秒杀记录
     * @param seckillId
     * @return
     */
    int deleteById(long seckillId);

    /**
     * 秒杀开启是输出秒杀接口地址，
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer  exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
    throws SeckillException, RepeatKillException, SeckillCloseException;

    int insertSeckill(Seckill seckill);
}
