package org.generaltune.entity;

import java.util.Date;

/**
 * Created by zhumin on 2017/6/12.
 */
public class CardTemplate {
    private long id;
    private long cardId;
    private String templateName;
    private int cardType;
    private String cardImgUrl;
    private String jsonCode;
    private String jsonCodeDraft;
    private int platform;
    private int status;
    private long createUser;
    private Date createTime;
    private long updateUser;
    private Date updateTime;
    private int publishState;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getCardImgUrl() {
        return cardImgUrl;
    }

    public void setCardImgUrl(String cardImgUrl) {
        this.cardImgUrl = cardImgUrl;
    }

    public String getJsonCode() {
        return jsonCode;
    }

    public void setJsonCode(String jsonCode) {
        this.jsonCode = jsonCode;
    }

    public String getJsonCodeDraft() {
        return jsonCodeDraft;
    }

    public void setJsonCodeDraft(String jsonCodeDraft) {
        this.jsonCodeDraft = jsonCodeDraft;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getPublishState() {
        return publishState;
    }

    public void setPublishState(int publishState) {
        this.publishState = publishState;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", cardId=" + cardId +
                ", templateName='" + templateName + '\'' +
                ", cardType=" + cardType +
                ", cardImgUrl='" + cardImgUrl + '\'' +
                ", jsonCode='" + jsonCode + '\'' +
                ", jsonCodeDraft='" + jsonCodeDraft + '\'' +
                ", platform=" + platform +
                ", status=" + status +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateUser=" + updateUser +
                ", updateTime=" + updateTime +
                ", publishState=" + publishState +
                '}';
    }
}
