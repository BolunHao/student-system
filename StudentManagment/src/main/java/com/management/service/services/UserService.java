package com.management.service.services;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.management.Parametric.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public interface UserService extends IService<User>{

    //Unpaginated query
    List<User> findAllUser(User user);

    /**
     * Query users by user name
     */
    User findUserByUsername(String username);

    /**
     * log in
     */
    Object login(User user, HttpSession session, HttpServletRequest request);

    /**
     * @desc: add new users
     */
    Object addUser(User user);


    /**
     * @desc: Query User
     */
    List<User> findUserByPage(Page<User> page, User user);

    /**
     * @desc: Deleting Users in batches
     */
    Object delUsers(String[] ids);

    /**
     * @desc: Login verification
     */
    List<User> checkUser(User user);

    /**
     * Modify User status
     */
    Object editUserStatus(User user);

    /**
     * User modifies user personal information
     */
    Object editUserInfo(User user);
}
