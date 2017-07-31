package org.generaltune.web.resource;

import com.alibaba.fastjson.JSON;
import org.generaltune.constants.Constants;
import org.generaltune.service.ResourceService;
import org.generaltune.web.base.DefaultAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by zhumin on 2017/6/13.
 */
@Controller
@RequestMapping("/resource")
public class ResourceAction extends DefaultAction {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/cardlist", method = RequestMethod.GET)
    public void cardTemplateList() {
        String result;
        try {
            result = getJSONResult(resourceService.getAllCardTemplates(1, 100, "desc"), Constants.RESPONSE_SUCCESS , "获取成功！");
        } catch (Exception e) {
            result = getJSONResult(null, Constants.RESPONSE_FAIL,"获取列表失败");
        }
        renderJSON(getResponse(), result);
    }

    @RequestMapping(value="/card/{id}", method = RequestMethod.GET)
    public void getCardById(
            @PathVariable(value = "id") Long id
    ) {
        String result;
        try {

            result = getJSONResult(JSON.toJSON(resourceService.queryByCardId(id)).toString(), Constants.RESPONSE_SUCCESS , "获取成功！");
        } catch (Exception e) {
            result = getJSONResult(null, Constants.RESPONSE_FAIL,"获取列表失败");
        }
        renderJSON(getResponse(), result);
    }
}
