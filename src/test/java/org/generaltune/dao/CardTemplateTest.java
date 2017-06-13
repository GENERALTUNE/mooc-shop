package org.generaltune.dao;

import org.generaltune.entity.CardTemplate;
import org.generaltune.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhumin on 2017/6/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class CardTemplateTest {

    //    注入实现类依赖
    @Resource
    private CardTemplateDao cardTemplateDao;

    @Test
    public void testQueryAll() throws Exception {
        /**
         * java没有保存形参的记录： queryAll(int offset, int limit) -> queryAll(arg0, arg1)
         *
         */
        List<CardTemplate> cardTemplates = cardTemplateDao.queryAll(0, 100, "desc");
        for(CardTemplate cardTemplate: cardTemplates) {
            System.out.println(cardTemplate);
        }

    }

    @Test
    public  void testQueryByCardId () throws Exception {
        long id = 10;
        CardTemplate cardTemplate = cardTemplateDao.queryByCardId(id);
        System.out.println(cardTemplate.getTemplateName());
        System.out.println(cardTemplate);
    }
}
