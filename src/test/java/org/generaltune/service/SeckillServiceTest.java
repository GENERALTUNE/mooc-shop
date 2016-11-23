package org.generaltune.service;

import org.generaltune.dto.Exposer;
import org.generaltune.dto.SeckillExecution;
import org.generaltune.entity.Seckill;
import org.generaltune.exception.RepeatKillException;
import org.generaltune.exception.SeckillException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhumin on 2016/11/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList(0, 10);
        logger.info("list={}", list);
    }

    @Test
    public void testGetById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long id  = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exporse={}" , exposer);
        /**
         * 10:24:19.126 [main] INFO  o.g.service.SeckillServiceTest - result=org.generaltune.dto.SeckillExecution@49096b06
         * 10:26:57.170 [main] INFO  o.g.service.SeckillServiceTest -
         * result=SeckillExecution{seckillId=1000, state=1, stateInfo='秒杀成功',
         * successKilled=SuccessKilled{seckillId=1000, userPhone=12345678904, state=0, createTime=Wed Nov 16 10:26:57 CST 2016}}
         */

    }

    //测试代码完整逻辑，注意可重复执行
    @Test
    public  void testSeckillLogic() throws Exception {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()) {
            logger.info("exporse={}" , exposer);
            long phone = 12345678906L;
            String md5 = "3d3b82103d09e29643908362c4dad7b3";
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("result={}", seckillExecution);
            }catch (RepeatKillException e) {
                logger.error(e.getMessage());
            } catch (SeckillException e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("exporse={}" , exposer);
        }
    }

//    @Test
//    public void testExecuteSeckill() throws Exception {
//        long id = 1000;
//        long phone = 12345678904L;
//        String md5 = "a41d2e07b3f4b505c525eb761b53e9ff";
//        try {
//            SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
//            logger.info("result={}", seckillExecution);
//        }catch (RepeatKillException e) {
//            logger.error(e.getMessage());
//        } catch (SeckillException e) {
//            logger.error(e.getMessage());
//        }
//    }
}