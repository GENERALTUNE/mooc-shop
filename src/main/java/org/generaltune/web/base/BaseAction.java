package org.generaltune.web.base;

import org.apache.commons.lang.StringUtils;
import org.generaltune.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhumin on 2017/3/13.
 */
public abstract class BaseAction {
    protected Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    ResultWrapper resultWrapper;

    public String getJSONPSuccessResult(Object data, Object message, String callback) {
        return getJSONPResult(data, Constants.RESPONSE_SUCCESS, message, callback);
    }

    public String getJSONPFailResult(Object data, Object message, String callback) {
        return getJSONPResult(data, Constants.RESPONSE_EXCEPTION, message, callback);
    }

    public  String getJSONResult(Object data, String code, Object message) {
        return resultWrapper.getResult(data, code, null, message);
    }

    public String getJSONPResult(Object data, String code, Object message, String callback) {
        if (StringUtils.isBlank(callback)) {
            callback = "callback";
        }
        return resultWrapper.getResult(data, code, callback, message);
    }

    public void renderJSONP(HttpServletResponse response, String content, String callback) {
        if (StringUtils.isBlank(callback)) {
            callback = "callback";
        }
        resultWrapper.renderResult(response, "application/javascript; charset=utf-8", content, callback);
    }

    public void renderJSON(HttpServletResponse response,  String content) {
        resultWrapper.renderResult(response, "application/json; charset=utf-8", content, null);
    }

    public void renderHTML(HttpServletResponse response, String content) {
        resultWrapper.renderResult(response, "text/html; charset=utf-8", content, null);
    }

    public void renderXML(HttpServletResponse response, String content) {
        resultWrapper.renderResult(response, "text/xml; charset=utf-8", content, null);
    }

    public ResultWrapper getResultWrapper() {
        return resultWrapper;
    }

    public void setResultWrapper(ResultWrapper resultWrapper) {
        this.resultWrapper = resultWrapper;
    }

}