package org.opengg.service.impl;

import org.opengg.bean.Role;
import org.opengg.mapper.RoleMapper;
import org.opengg.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> queryAll() {
        return roleMapper.queryAll();
    }

    public List<Role> pageQuery(Map<String, Object> map) {
        return roleMapper.pageQuery(map);
    }

    public int pageQueryCount(Map<String, Object> map) {
        return roleMapper.pageQueryCount(map);
    }

    public Role queryRoleById(Integer roleId) {
        return roleMapper.queryRoleById(roleId);
    }

    public void insertRolePermission(Map<String, Object> map) {
        //为了防止重复插入，先删后插
        roleMapper.deleteRolePermission(map);
        roleMapper.insertRolePermission(map);
    }
}
