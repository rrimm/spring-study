package com.mysite.springjpa.user;

import com.mysite.springjpa.user.User;
import com.mysite.springjpa.user.UserInfoMapping;

import java.util.List;

public interface UserService {


    public User insertUser(User user);

//    public void insertUser2(String id, String addr, int birth, int height, String mobile, String name);

    public List<User> listUser();

    public User getUser(String id);

    public List<User> getUserAddr(String addr);

    public List<User> getUserAddr2(String addr);

    public List<User> getUserAddrAndName(String addr, String name);

    public void updateUser(String id, String addr, String mobile);

    public void deleteUser(String id);

    public List<User> listUserOrderId();

    public List<User> listUserOrderName();

    public List<User> listUserOrderNameDesc();

    public List<UserInfoMapping> listUserAddr(String addr);
}
