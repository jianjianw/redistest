package org.opengg.service;


import org.opengg.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    List<Role> queryAll();

    List<Role> pageQuery(Map<String, Object> map);

    int pageQueryCount(Map<String, Object> map);

    Role queryRoleById(Integer roleId);

    void insertRolePermission(Map<String, Object> map);
}
