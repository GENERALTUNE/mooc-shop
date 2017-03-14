package org.generaltune.web.interceptor;

import org.generaltune.entity.User;
import org.generaltune.service.UserService;
import org.generaltune.util.SSOUtils;
import org.generaltune.util.StringUtil;
import org.generaltune.web.base.DefaultAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zhumin on 2017/3/12.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);


    @Autowired
    UserService userService;

    User user;

    private String loginPath = "/login";

    private String getLoginURL(HttpServletRequest request) {
        String previousURL = request.getRequestURI();

        if(request.getQueryString() != null) {
            try {
                previousURL = URLEncoder.encode(previousURL + "?" + request.getQueryString(), "utf-8");
            } catch (Exception e) {
                logger.error("[module:AuthInterceptor][action:getLoginURL][Exception:{}]", e);
            }
        }

        return loginPath + "?url=" + previousURL;
    }



//    private String getJSONContent(DefaultAction action, String msg) {
//        if (msg == null) {
//            msg = "you have not login.";
//        }
//        String content = action.getJSONResult(null,
//                Constants.CODE_ERROR_UNLOGIN, "permission denied. " + msg);
//        return content;
//    }





    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws  Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
//            AuthRedirect authRedirect = ((HandlerMethod) handler).getMethodAnnotation(AuthRedirect.class);
            Object controller = ((HandlerMethod) handler).getBean();
//            CacheManager cacheManager = (CacheManager) ApplicationContextUtils.getBean(CacheManager.class);
//            Cache cache = cacheManager.getObjectCache(Constants.CACHE_AUTH);
            DefaultAction defaultAction = null;
            boolean isLogin = false;
//            boolean isOuter = isOuter(request);

            if (controller instanceof  DefaultAction) {
                defaultAction = ((DefaultAction) controller);
            }
            logger.info("执行是否已登录检查！########################");

            // not require auth and it's not extends the default action
//            if (authRedirect == null || authRedirect.validate() == false) {
                if (defaultAction == null) {

                    return true;
                }
//            }

            if (defaultAction != null) {
                String url = request.getRequestURI();
//                StringBuffer ur = request.getRequestURL();
//                user = defaultAction.getUser();
                //登录页面不校验！
                if (url.equals("/login") || url.equals("/register")){
                    return  true;
                }
//                boolean isLogin = defaultAction.isLogin(request, response, isOuter);
                String username = SSOUtils.getSsoUsername(request);
                if(username != null) {
                    isLogin = true;
                }

                // the cookie auth passed and not set authentication
//                if (isLogin && user.getId() == null) {
//                    user = getRemoteUser(request, isOuter);
//                    defaultAction.setUser(user);
//                }

                // not require authentication
//                if (authRedirect == null || authRedirect.validate() == false) {
//                    if (isLogin) {
//                        setUserAuth(defaultAction, user, cache);
//                    }
//                    return true;
//                }

                // have logined
                if (isLogin) {
//                    setUserAuth(defaultAction, user, cache);
                    return true;
                }

//                logger.info("[action:preHandle][controller:{}][request failed:{}]", controller, "user not login.");
                response.sendRedirect("/index.jsp");

                // validate failed
//                if (authRedirect != null && authRedirect.resultType() == ResultTypeEnum.JSON) {
//                    defaultAction.renderJSON(response, getJSONContent(defaultAction, null));
//                } else {
//                    if (isOuter) {
//                        response.sendRedirect(getPassportLoginURL(request));
//                    } else {
//                        response.sendRedirect(getLoginURL(request));
//                    }
//                }

            }
//            return false;
            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView mv) throws Exception {
        // auth success do something
    }


}