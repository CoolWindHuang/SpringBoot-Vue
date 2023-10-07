package com.wufeng.controller;

import com.wufeng.pojo.SysUser;
import com.wufeng.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    public SysUserService sysUserService;

    @RequestMapping("test")
    public String Test(){
        List<SysUser> userList = sysUserService.getUserList();
        return userList.toString();
    }
}
