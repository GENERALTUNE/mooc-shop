package org.generaltune.web.base.impl;

import org.apache.commons.lang.StringUtils;
import org.generaltune.web.base.ResultWrapper;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by zhumin on 2017/3/13.
 */

@Component
public class ResultWrapperDefaultImpl implements ResultWrapper {

    public String getResult(Object data, String code, String callback, Object message) {
        JSONObject map = new JSONObject();
        if (StringUtils.isEmpty(callback)) {
            callback = "";
        }
        if (data == null) {
            data = new Object();
        }
        map.put("code", code);
        map.put("data", data);
        map.put("msg", message);


//        String content = map.toJSONString();
        String content = map.toString();
        if (StringUtils.isNotBlank(callback)) {
            content = "try{" + callback + "(" + content + ")}catch(e){}";
        }
        return content;
    }

    public void renderResult(HttpServletResponse response, String contentType, String content, String callback) {
        response.setCharacterEncoding("utf-8");
        if (StringUtils.isBlank(contentType)) {
            contentType = "text/html; charset=utf-8";
        }
        response.setContentType(contentType);
        if (StringUtils.isNotBlank(callback)) {
            content = "try{" + callback + "(" + content + ")}catch(e){}";
        }
        try {
            OutputStream os = response.getOutputStream();
            os.write(content.getBytes("utf-8"));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
