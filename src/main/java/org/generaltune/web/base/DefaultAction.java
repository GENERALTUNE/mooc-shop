package org.generaltune.web.base;

import org.generaltune.entity.User;
import org.generaltune.service.UserService;
import org.generaltune.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhumin on 2017/3/10.
 */
public class DefaultAction extends BaseAction{

//    @Autowired
    public User user;

    @Autowired
    private HttpServletRequest request;

    private HttpServletResponse response;

    private HttpSession session;


    @Autowired
    private UserService userService;

    private MessageUtils messageUtils;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){

        this.request = request;

        this.response = response;

        this.session = request.getSession();

    }

    public boolean isLogin(HttpServletRequest request, HttpServletResponse response) {
        return userService.isLogin(request, response);
    }

    public boolean isLogin() {
        return isLogin(getRequest(), getResponse());
    }

    public void initMessage() {
        if (messageUtils == null) {
            messageUtils = new MessageUtils();
        }
    }


    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }



    @ModelAttribute("user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @ModelAttribute("message")
    public MessageUtils getMessageUtils() {
        if (messageUtils == null) {
            initMessage();
        }
        return messageUtils;
    }

    public void setMessageUtils(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
