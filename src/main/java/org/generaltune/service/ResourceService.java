package org.generaltune.service;

import org.generaltune.entity.CardTemplate;

import java.util.List;

/**
 * Created by zhumin on 2017/6/13.
 */
public interface ResourceService {
    List<CardTemplate> getAllCardTemplates(int offset, int limit, String orderType);
    CardTemplate queryByCardId(long cardId);

}
