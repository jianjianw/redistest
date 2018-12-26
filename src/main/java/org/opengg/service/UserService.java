package org.opengg.service;

import org.opengg.bean.User;

import java.util.List;
import java.util.Map;

;

public interface UserService {

    public List<User> queryAll();

    public List<User> findAll();

    User doAJAXLogin(User user);

    List<User> pageQuery(Map<String, Object> map);

    int pageQueryCount(Map<String, Object> map);

    void insertUser(User user);

    User queryById(String userId);

    void update(User user);

    void deleteUserById(String userId);

    void deletes(Integer[] userId);

    List<Integer> queryRoleIdsByUserId(String userId);

    void insertUserRoles(Map<String, Object> map);

    void deleteUserRoles(Map<String, Object> map);
}
