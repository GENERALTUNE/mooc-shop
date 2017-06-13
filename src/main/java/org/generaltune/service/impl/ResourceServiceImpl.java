package org.generaltune.service.impl;

import org.generaltune.dao.CardTemplateDao;
import org.generaltune.entity.CardTemplate;
import org.generaltune.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhumin on 2017/6/13.
 */
@Service
public class ResourceServiceImpl implements ResourceService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CardTemplateDao cardTemplateDao;

    public List<CardTemplate> getAllCardTemplates(int offset, int limit, String orderType) {
        return cardTemplateDao.queryAll(offset, limit, orderType);
    }

    public CardTemplate queryByCardId(long cardId) {
        return cardTemplateDao.queryByCardId(cardId);
    }
}
