package org.generaltune.dao;

import org.apache.ibatis.annotations.Param;
import org.generaltune.entity.User;

import java.util.Date;
import java.util.List;

/**
 * Created by zhumin on 2017/3/9.
 */
public interface UserDao {
    /**
     * 根据id查询秒杀对象
     * @param Id
     * @return
     */
    User queryById(long Id);

    /**
     * 判断用户是否存在
     * @param username
     * @param password
     * @return
     */
    boolean isUser(String username ,String password);
    /**
     * 根据 用户名查询
     * @param username
     * @return
     */
    User queryByUsername(String username);

    /**
     *
     * @param Id
     * @return
     */
    int deleteById(long Id);

    /**
     *
     * @param Id
     * @param username
     * @param name
     * @param updatetime
     * @param password
     * @param birthday
     * @param type
     * @param status
     * @param region
     * @param phone
     * @param description
     * @param email
     * @param version
     * @return
     */
    int update(  @Param("id") long Id, @Param("username") String username, @Param("name") String name, @Param("updatetime") Date updatetime, @Param("password") String password, @Param("birthday") Date birthday, @Param("type") String type, @Param("status") int status, @Param("region") short region, @Param("phone") long phone, @Param("description") String description, @Param("email") String email, @Param("version") long version);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    List<User> queryAll(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 增加用户
     * @param username
     * @param name
     * @param createtime
     * @param updatetime
     * @param password
     * @param birthday
     * @param type
     * @param status
     * @param region
     * @param phone
     * @param description
     * @param email
     * @return
     */
    int insertUser(@Param("username") String username, @Param("name") String name, @Param("createtime") Date createtime, @Param("updatetime") Date updatetime, @Param("password") String password, @Param("birthday") Date birthday, @Param("type") String type, @Param("status") int status, @Param("region") short region, @Param("phone") long phone, @Param("description") String description, @Param("email") String email, @Param("version") long version);



}
