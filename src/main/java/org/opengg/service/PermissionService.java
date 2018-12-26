package org.opengg.service;


import org.opengg.bean.Permission;
import org.opengg.bean.User;

import java.util.List;

public interface PermissionService {


    List<Permission> queryAll();

    void insert(Permission permission);

    void update(Permission permission);

    void delete(Integer id);

    Permission queryById(Integer id);

    List<Integer> queryPermissionIdsByRoleId(Integer roleId);

    List<Permission> queryPermissionsByUser(User dbUser);
}
