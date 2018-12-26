package org.opengg.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.opengg.bean.Permission;
import org.opengg.bean.User;

import java.util.List;

public interface PermissionMapper {


    @Select("select * from t_permission")
    public List<Permission> queryAll();

    void insert(Permission permission);

    void update(Permission permission);

    @Delete("delete from t_permission where id = #{id} ")
    void delete(Integer id);

    @Select("select * from t_permission where id = #{id}")
    Permission queryById(Integer id);

    @Select("select permission_id from t_role_permission where role_id = #{roleId} ")
    List<Integer> queryPermissionIdsByRoleId(Integer roleId);

    List<Permission> queryPermissionsByUser(User dbUser);

}
