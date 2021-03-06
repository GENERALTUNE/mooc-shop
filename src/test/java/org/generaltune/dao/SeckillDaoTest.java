package org.generaltune.dao;


import org.generaltune.entity.Seckill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * Created by zhumin on 2016/11/15.
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test, junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //    注入实现类依赖
    @Resource
    private SeckillDao seckillDao;


    @Test
    public void testReduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("updateCount" + updateCount);
    }

    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
        /**
         * 1000元秒杀iPhone6
         org.generaltune.entity.Seckill@4fdfa676
         */
    }



    @Test
    public void testQueryAll() throws Exception {
        /**
         * java没有保存形参的记录： queryAll(int offset, int limit) -> queryAll(arg0, arg1)
         *
         */
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for(Seckill seckill: seckills) {
            System.out.println(seckill);
        }

    }
    @Test
    public void testInsertSeckill () throws Exception {
        Date startTime = new Date();
        Date endTime = new Date(2016, 12, 10);
        String name = "苹果7最低价1200元";
        seckillDao.insertSeckill(name, 200, startTime, endTime);
    }
}