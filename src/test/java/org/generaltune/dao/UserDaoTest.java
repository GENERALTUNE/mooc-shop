package org.generaltune.dao;

import org.generaltune.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhumin on 2017/3/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserDaoTest {
    @Resource
    private UserDao userDao;


    @Test
    public void testInsertSeckill () throws Exception {
        Date createtime = new Date();
        Date updatetime = new Date();
        Calendar calendar= Calendar.getInstance();
        calendar.set(1988,12,12);

        Date birthday = calendar.getTime();
        String username = "zhangfei";
        String email = "zhangfei@qiyi.com";
        String name = "张飞";
        String password = "zhangfei123";
        long version = 234324l;
        short region = 1;
        int status = 2;
        long phone = 12345678911l;
        String type = "管理员，武将";
        String description = "超级管理员，燕人张翼德！";


//        String name = "苹果7最低价1200元";
        userDao.insertUser(username, name, createtime, updatetime, StringUtil.getMD5(password), birthday, type, status, region, phone, description, email, version);
    }

    @Test
    public void testFindUser() throws Exception {
        String username = "zhumin";
        System.out.println(userDao.queryByUsername(username).getPassword());
    }

    @Test
    public void testUpdate() throws Exception {
        long id = 2l;
//        Date createtime = new Date();
        Date updatetime = new Date();
        Calendar calendar= Calendar.getInstance();
        calendar.set(1988,12,12);
        Date birthday = calendar.getTime();
        String username = "guanyu";
        String email = "guanyu@qiyi.com";
        String name = "关云长";
        String password = "guanyu123";
        long version = 1111111111l;
        short region = 2;
        int status = 3;
        long phone = 123456789221l;
        String type = "武圣";
        String description = "三国时期的一员超级猛将";

       System.out.print( userDao.update(id, username, name, updatetime, StringUtil.getMD5(password), birthday, type, status, region, phone, description, email, version));
    }
}
