package org.opengg.controller;

import org.opengg.bean.AJAXResult;
import org.opengg.bean.Page;
import org.opengg.bean.Role;
import org.opengg.bean.User;
import org.opengg.service.RoleService;
import org.opengg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "/user/index";
    }


    @ResponseBody
    @RequestMapping(value = "/findAll", method = {RequestMethod.POST,RequestMethod.GET})
    public List<User> findAll(){
        List<User> all = userService.findAll();
        return all;
    }

    @ResponseBody
    @RequestMapping(value = "queryById", method = {RequestMethod.POST,RequestMethod.GET})
    public User queryById(Integer userId){
         User user = userService.queryById(userId);
         return user;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public AJAXResult delete(Integer userId){
        AJAXResult result = new AJAXResult();
        try {
            userService.deleteUserById(userId);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/update")
    public AJAXResult update(User user){
        AJAXResult result = new AJAXResult();
        try {
            userService.update(user);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }
}
