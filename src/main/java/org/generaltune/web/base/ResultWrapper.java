package org.generaltune.web.base;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhumin on 2017/3/13.
 */
public interface ResultWrapper {
    public String getResult(Object data, String code, String callback, Object message);
    public void renderResult(HttpServletResponse response, String contentType, String content, String callback);
}
