package org.opengg.controller;

import org.opengg.bean.AJAXResult;
import org.opengg.bean.Permission;
import org.opengg.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        return "/permission/index";
    }

    @RequestMapping("/loadData")
    @ResponseBody
    public List<Permission> loadData(){

        List<Permission> permissions = new ArrayList<Permission>();

        List<Permission> ps = permissionService.queryAll();

        Map<Integer,Permission> hashMap = new HashMap<Integer,Permission>();
        for(Permission permission : ps){
            hashMap.put(permission.getId(),permission);
        }
        for(Permission permission : ps){
            if(permission.getPid() == 0){
                permissions.add(permission);
            }else{
                Permission parent = hashMap.get(permission.getPid());
                parent.getChildren().add(permission);
            }
        }
        return permissions;
    }

    @RequestMapping("/add")
    public String add(){
        return "/permission/add";
    }

    @RequestMapping("insert")
    @ResponseBody
    public AJAXResult insert(Permission permission){

        AJAXResult result = new AJAXResult();

        try {
            permissionService.insert(permission);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/edit")
    public String edit(Integer id, Model model){
        Permission permission =  permissionService.queryById(id);
        model.addAttribute("permission",permission);
        return "/permission/edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public AJAXResult update(Permission permission){

        AJAXResult result = new AJAXResult();
        try {
            permissionService.update(permission);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public AJAXResult delete(Integer id){

        AJAXResult result = new AJAXResult();
        try {
            permissionService.delete(id);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    //加载角色的权限tree
    @RequestMapping("/loadAssignData")
    @ResponseBody
    public List<Permission> loadAssignData(Integer roleId){

        List<Permission> permissions = new ArrayList<Permission>();
        //查询所有权限
        List<Permission> ps = permissionService.queryAll();
        //查询该角色的权限
        List<Integer> roleIds = permissionService.queryPermissionIdsByRoleId(roleId);
        Map<Integer,Permission> hashMap = new HashMap<Integer,Permission>();
        for(Permission permission : ps){
            if(roleIds.contains(permission.getId())){
                permission.setChecked(true);
            }else{
                permission.setChecked(false);
            }
            hashMap.put(permission.getId(),permission);
        }
        for(Permission permission : ps){
            if(permission.getPid() == 0){
                //根节点
                permissions.add(permission);
            }else{
                //找到该节点的父节点
                Permission parent = hashMap.get(permission.getPid());
                //将该节点添加到父节点中
                parent.getChildren().add(permission);
            }
        }
        //返回节点
        return permissions;
    }
}
