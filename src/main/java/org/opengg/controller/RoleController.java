package org.opengg.controller;

import org.opengg.bean.AJAXResult;
import org.opengg.bean.Page;
import org.opengg.bean.Role;
import org.opengg.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public String index(){
        return "/role/index";
    }

    @ResponseBody
    @RequestMapping("/pageQuery")
    public Object pageQuery(String queryText, Integer pageNo, Integer pageSize){
        AJAXResult result  = new AJAXResult();
        try {
            Map<String,Object> map  = new HashMap<String,Object>();
            map.put("start",(pageNo-1) * pageSize);
            map.put("size",pageSize);
            map.put("queryText",queryText);
            //获取分页数据
            List<Role> roles = roleService.pageQuery(map);
            //获取总条数
            int totalSize = roleService.pageQueryCount(map);
            //获取总页数
            int pageCount = 0;
            if(totalSize % pageSize == 0){
                pageCount = totalSize/pageSize;
            }else{
                pageCount = totalSize/pageSize + 1;
            }
            //封装数据
            Page<Role> page  = new Page<Role>();
            page.setDatas(roles);
            page.setPageNo(pageNo);
            page.setPageSize(pageSize);
            page.setPageCount(pageCount);
            page.setTotalSize(totalSize);

            result.setData(page);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            logger.info("分页查询失败");
        }
        return result;
    }

    @RequestMapping("/assign")
    public String assign(Integer roleId, Model model) {
        Role role = roleService.queryRoleById(roleId);
        model.addAttribute("role",role);
        return "role/assign";
    }

    @ResponseBody
    @RequestMapping("doAssign")
    public AJAXResult doAssign(Integer roleId, Integer[] permissionIds){

        AJAXResult result = new AJAXResult();
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("roleId",roleId);
            map.put("permissionIds",permissionIds);
            //将给角色添加的权限保存到数据库里
            roleService.insertRolePermission(map);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
        }
        return result;
    }
}
