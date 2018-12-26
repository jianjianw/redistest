package org.opengg.service.impl;

import org.opengg.bean.Permission;
import org.opengg.bean.User;
import org.opengg.mapper.PermissionMapper;
import org.opengg.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<Permission> queryAll() {
        return permissionMapper.queryAll();
    }

    public void insert(Permission permission) {
        permissionMapper.insert(permission);
    }

    public void update(Permission permission) {
        permissionMapper.update(permission);
    }

    public void delete(Integer id) {
        permissionMapper.delete(id);
    }

    public Permission queryById(Integer id) {
        return permissionMapper.queryById(id);
    }

    public List<Integer> queryPermissionIdsByRoleId(Integer roleId) {
        return permissionMapper.queryPermissionIdsByRoleId(roleId);
    }

    public List<Permission> queryPermissionsByUser(User dbUser) {
        return permissionMapper.queryPermissionsByUser(dbUser);
    }
}
