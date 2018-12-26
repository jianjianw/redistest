package org.opengg.service.impl;

import org.opengg.bean.User;
import org.opengg.mapper.UserMapper;
import org.opengg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    public User doAJAXLogin(User user) {
        return userMapper.doAJAXLogin(user);
    }

    public List<User> pageQuery(Map<String, Object> map) {
        return userMapper.pageQuery(map);
    }

    public int pageQueryCount(Map<String, Object> map) {
        return userMapper.pageQueryCount(map);
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public void deletes(Integer[] userId) {
        userMapper.deletes(userId);
    }

    public List<Integer> queryRoleIdsByUserId(String userId) {
        return userMapper.queryRoleIdsByUserId(userId);
    }

    public void insertUserRoles(Map<String, Object> map) {
        userMapper.insertUserRoles(map);
    }

    public void deleteUserRoles(Map<String, Object> map) {
        userMapper.deleteUserRoles(map);
    }




    @Cacheable(cacheNames = "userList", key = "'userList'")
    public List<User> findAll() {
        return userMapper.queryAll();
    }
    @Cacheable(cacheNames = "user", key = "#userId")
    public User queryById(String userId) {
        return userMapper.queryById(userId);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void deleteUserById(String userId) {
        userMapper.deleteUserById(userId);
    }
}
