package com.wufeng.controller;

import com.wufeng.pojo.SysUser;
import com.wufeng.pojo.dto.SysUserDto;
import com.wufeng.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserDtoController {
    @Autowired
    public SysUserService sysUserService;


    @RequestMapping("login")
    public Map<String,Object> Login(@RequestBody SysUserDto sysUserDto){
        List<SysUser> list = sysUserService.login(sysUserDto);
        Map<String,Object> map = new HashMap();
        String msg = "success";
        if(list.isEmpty()||list.size()>=2){
            msg="fail";
            map.put("currentUser",null);
        }else{
            map.put("currentUser",list.get(0));
        }
        map.put("msg",msg);


        System.out.println(map);

        return map;
    }
}
