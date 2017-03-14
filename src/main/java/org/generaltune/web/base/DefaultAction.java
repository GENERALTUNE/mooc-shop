package org.generaltune.web.base;

import org.generaltune.entity.User;
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

    @ModelAttribute

    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){

        this.request = request;

        this.response = response;

        this.session = request.getSession();

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



    //    @ModelAttribute("user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
