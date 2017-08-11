package org.generaltune.web.frontend;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.generaltune.web.base.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhumin on 2017/7/26.
 */
@Controller
@RequestMapping("/front")
public class ZtreeAction extends BaseAction{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping( value = "/ztree", method = RequestMethod.GET)
    public ModelAndView ztree() {

        ModelAndView mv = new ModelAndView();


//        JSONArray channels = channelTools.getChannels();
//        JSONObject pageAttributes = new JSONObject();

//        pageAttributes.put("site", getPageAttrSite());
//        pageAttributes.put("language", getPageAttrLanguage());

//        mv.addObject("PAGE_ATTRIBUTES", pageAttributes);
//        mv.addObject("channels", channels);
        mv.setViewName("/frontend/ztree");

        return mv;
    }

}
