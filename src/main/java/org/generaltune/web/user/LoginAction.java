package org.generaltune.web.user;

import org.generaltune.constants.Constants;
import org.generaltune.entity.Seckill;
import org.generaltune.entity.User;
import org.generaltune.service.UserService;
import org.generaltune.util.SSOUtils;
import org.generaltune.util.StringUtil;
import org.generaltune.web.base.DefaultAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhumin on 2017/3/11.
 */
@Controller //@Service @Component
@RequestMapping("/")  //url
public class LoginAction extends DefaultAction {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 登录打开页面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "url", required = false) String url
    ) {
        ModelAndView mv = new ModelAndView();
        getMessageUtils().setMessage(null);
        setMessageUtils(null);
        setUser(user);
        mv.addObject("url", url);
        mv.addObject("message", getMessageUtils());
        mv.setViewName("login");
        return mv;
    }

    /**
     * 登录提交页面
     * @param username
     * @param password
     * @param
     * @param
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ModelAndView authenticate(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "remember", required = false) String remember
    ) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login");
        User user = getUserService().findByUsername(username);
        String md5 = StringUtil.getMD5(password);
        Long validTime = 30 * 24 * 60 * 60 * 1000L;

        Boolean checkUser = md5.equals(user.getPassword());
        try {

            if (checkUser) {
                SSOUtils.deleteSsoTicket(getRequest(), getResponse());
                SSOUtils.setSsoTicket(getRequest(), getResponse(), username, validTime);
                this.setUser(user);
                    if (url == "/login" || url == null) {
                        url = "/seckill/list";
                }
                getResponse().sendRedirect(url);
//                renderJSON(getResponse(), getResultWrapper().getResult(user, Constants.RESPONSE_SUCCESS, null, "登录成功！"));
            } else {
                logger.error("登录失败！！");
                getMessageUtils().setMessage("登录失败！");
                return mv;
//                renderJSON(getResponse(), getResultWrapper().getResult(user, Constants.RESPONSE_FAIL, null,  Constants.RESPONSE_FAIL_MSG));
            }

        }catch (Exception e) {
//            renderJSON(getResponse(), getResultWrapper().getResult(user, Constants.RESPONSE_FAIL, null,  Constants.RESPONSE_FAIL_MSG));
            logger.error("登录错误！");
            getMessageUtils().setMessage("登录错误！");
            return mv;
        }
        return mv;
    }

    @RequestMapping("logout")
    public String logout() {
        try {
            SSOUtils.deleteSsoTicket(getRequest(), getResponse());
            getResponse().sendRedirect("/login");
        } catch (Exception e) {
            logger.error("[module:LoginAction][action:logout][error:{}]", "退出登录异常", e);
            return "login";
        }
        return "login";
    }

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public void users() {
        String result;
        try {
            result = getJSONResult(getUserService().GetUserList(0, 100), Constants.RESPONSE_SUCCESS , "获取成功！");
        } catch (Exception e) {
            result = getJSONResult(null, Constants.RESPONSE_FAIL,"获取列表失败");
        }
        renderJSON(getResponse(), result);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signup() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
        produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void register(
        @RequestParam(value = "username", required = true) String username,
        @RequestParam(value = "name", required = true) String name,
        @RequestParam(value = "password", required = true) String password,
        @RequestParam(value = "email", required = true) String email,
        @RequestParam(value = "description", required = true) String description,
        @RequestParam(value = "phone", required = true) Long phone
    ) {
        String result;
        Calendar calendar= Calendar.getInstance();
        calendar.set(1988,12,12);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setName(name);
        user.setBirthday(calendar.getTime());
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setDescripiton(description);
        user.setRegion((short) 1);
        user.setStatus(2);
        user.setType("武将");
        user.setVersion(1111111l);

        logger.info(username +"=>"+ password +"=>"+  email+"=>"+ phone +"=>"+  "打印提交信息！");
        logger.error(username +"=>"+  password+"=>"+ email +"=>"+  phone+"=>"+  "打印提交信息！");

        result = getJSONResult(getUserService().addUser(user), Constants.RESPONSE_SUCCESS , "保存成功！");
        renderJSON(getResponse(), result);
    }



}
