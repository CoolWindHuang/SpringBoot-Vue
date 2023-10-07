package com.wufeng.controller;

import com.wufeng.pojo.SysUser;
import com.wufeng.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    public SysUserService sysUserService;

    @RequestMapping("all")
    public Map<String,Object> getUserList(){
        Map<String,Object> map =new HashMap<>();
        List<SysUser> userList = sysUserService.getUserList();
        map.put("users",userList);
        map.put("total",userList.size());
        return map;
    }

    @RequestMapping("add")
    public int addUser(SysUser sysUser){
        System.out.println(sysUser);
        return sysUserService.addUser(sysUser);
    }

    @RequestMapping("update")
    public int updateUser(SysUser sysUser){
        return sysUserService.updateUser(sysUser);
    }

    @RequestMapping("delete/{id}")
    public int deleteUserById(@PathVariable int id){
        return sysUserService.deleteByUserId(id);
    }

    @RequestMapping("deleteBatch")
    public int deleteBatchByIds(@RequestBody Map<String, Object> requestMap){
        List<Integer> Ids = (List<Integer>) requestMap.get("Ids");
        System.out.println(Ids);
        return  sysUserService.deleteUsersByIds(Ids);
    }

    @RequestMapping("findPage")
    public Map<String,Object> findPage(@RequestParam int pageIndex, @RequestParam int pageSize,
            String username,String email,String address){

        Map<String,Object> map =new HashMap<>();
        pageIndex = (pageIndex-1) * pageSize;
        List<SysUser> users = sysUserService.findPage(pageIndex, pageSize,username,email,address);
        map.put("users",users);
        if((username==null||username=="")&&(email==null||email=="")&&(address==null||address=="")){
            map.put("total",userTotal());
        }else{
            map.put("total",userTotalOnCondition(username,email,address));
        }

        return map;
    }

    private int userTotal(){
        return sysUserService.userTotal();
    }

    private int userTotalOnCondition(String username,String email,String address){
        return sysUserService.userTotalOnCondition(username,email,address);
    }


    @RequestMapping("conditionSearch")
    public Map<String,Object> conditionSearch(@RequestParam String username,@RequestParam String email,@RequestParam String address){
        Map<String, Object> map = new HashMap<>();

        System.out.println(username+" "+ email+ " " +address);


        return map;
    }
}
