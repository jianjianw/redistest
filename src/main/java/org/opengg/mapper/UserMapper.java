package org.opengg.mapper;

import org.apache.ibatis.annotations.Select;
import org.opengg.bean.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    public List<User> queryAll();

    User doAJAXLogin(User user);

    List<User> pageQuery(Map<String, Object> map);

    int pageQueryCount(Map<String, Object> map);

    void insertUser(User user);

    @Select("select * from t_user where user_id = #{userId}")
    User queryById(String userId);

    void update(User user);

    void deleteUserById(String userId);

    void deletes(Integer[] userId);

    List<Integer> queryRoleIdsByUserId(String userId);

    void insertUserRoles(Map<String, Object> map);

    void deleteUserRoles(Map<String, Object> map);

}
