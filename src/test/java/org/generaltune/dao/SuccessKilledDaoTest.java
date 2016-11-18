package org.generaltune.dao;

import org.generaltune.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by zhumin on 2016/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() throws Exception {
        long id = 1002L;
        long phone = 123456789L;
        int inserCount = successKilledDao.insertSuccessKilled(id, phone);
        System.out.println("inserCount" + inserCount);

    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        long id = 1002;
        long phone = 123456789L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
    }
}