package org.generaltune.dao;

import org.apache.ibatis.annotations.Param;
import org.generaltune.entity.CardTemplate;

import java.util.Date;
import java.util.List;

/**
 * Created by zhumin on 2017/6/13.
 */
public interface CardTemplateDao {

    /**
     * 根据id查询card模板对象
     * @param cardId
     * @return
     */
    CardTemplate queryByCardId(long cardId);

    /**
     *
     * @param cardId
     * @return
     */
    int deleteByCardId(long cardId);

    /**
     * 根据偏移量查询cardTemplate列表
     * @param offset
     * @param limit
     * @param orderType
     * @return
     */
    List<CardTemplate> queryAll(@Param("offset") int offset, @Param("limit") int limit, @Param("orderType") String orderType);

    /**
     * 新增一条card template
     * @param cardId
     * @param templateName
     * @param cardType
     * @param cardImgUrl
     * @param jsonCode
     * @param jsonCodeDraft
     * @param platform
     * @param status
     * @param createUser
     * @param createTime
     * @param updateUser
     * @param updateTime
     * @param publishState
     * @return
     */
    int insertCardTemplate(@Param("cardId") long cardId, @Param("templateName") String templateName, @Param("cardType") int cardType,
                      @Param("cardImgUrl") String cardImgUrl, @Param("jsonCode") String jsonCode, @Param("jsonCodeDraft") String jsonCodeDraft,
                      @Param("platform") int platform, @Param("status") int status, @Param("createUser") long createUser,
                      @Param("createTime") Date createTime, @Param("updateUser") long updateUser, @Param("updateTime") Date updateTime,
                      @Param("publishState") int publishState);
}
