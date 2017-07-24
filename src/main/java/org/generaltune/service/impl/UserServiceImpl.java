package org.generaltune.service.impl;

import org.generaltune.dao.UserDao;
import org.generaltune.entity.User;
import org.generaltune.service.UserService;
import org.generaltune.util.SSOUtils;
import org.generaltune.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhumin on 2017/3/9.
 */
@Service
public class UserServiceImpl implements UserService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;


    public int addUser(User user) {
       return  userDao.insertUser(user.getUsername(),user.getName(), user.getCreatetime(),user.getUpdatetime(),StringUtil.getMD5(user.getPassword()),user.getBirthday(),user.getType(),user.getStatus(),user.getRegion(),user.getPhone(),user.getDescripiton(),user.getEmail(),user.getVersion());
    }

    public boolean checkUser(String username, String password) {
        User user = userDao.queryByUsername(username);
        String md5 = StringUtil.getMD5(password);
        return md5.equals(user.getPassword());
    }

    public boolean isLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = SSOUtils.getSsoUsername(request);
        User user = userDao.queryByUsername(username);
        if (user == null) {
            return  false;
        }
        return true;
    }

    public User findByUsername(String username) {
        return userDao.queryByUsername(username);
    }

    public List<User> GetUserList(int offset, int limit) {
        return userDao.queryAll(offset, limit);
    }

    public int updateUser(User user) {
        return  userDao.update(user.getUid(),user.getUsername(),user.getName(),user.getUpdatetime(),user.getPassword(),user.getBirthday(),user.getType(),user.getStatus(),user.getRegion(),user.getPhone(),user.getDescripiton(),user.getEmail(),user.getVersion());
    }
}
