package org.generaltune.service;

import org.generaltune.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhumin on 2017/3/9.
 */
public interface UserService {
    int addUser(User user);
    User findByUsername(String username);
    List<User> GetUserList(int offset, int limit);
    int updateUser(User user);
    boolean checkUser(String username, String password);
    boolean isLogin(HttpServletRequest request, HttpServletResponse response);
}
