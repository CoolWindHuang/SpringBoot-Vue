package com.wufeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wufeng.pojo.SysUser;
import com.wufeng.pojo.dto.SysUserDto;
import com.wufeng.service.SysUserService;
import com.wufeng.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Hxh
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2023-09-16 15:03:10
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    public SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> login(SysUserDto sysUserDto) {
        return sysUserMapper.login(sysUserDto);
    }

    @Override
    public int addUser(SysUser sysUser) {
        return sysUserMapper.addUser(sysUser);
    }

    @Override
    public int updateUser(SysUser sysUser) {
        return sysUserMapper.updateUser(sysUser);
    }

    @Override
    public int deleteByUserId(int id) {
        return sysUserMapper.deleteByUserId(id);
    }

    @Override
    public int deleteUsersByIds(List<Integer> Ids) {
        return sysUserMapper.deleteUsersByIds(Ids);
    }
    @Override
    public List<SysUser> findPage(int pageIndex, int pageSize, String username, String email, String address) {
        //判断条件
        if(username!=null){
            username="%"+username+"%";
        }
        if(email!=null){
            email="%"+email+"%";
        }
        if(address!=null){
            address="%"+address+"%";
        }
        return sysUserMapper.findPage(pageIndex, pageSize, username, email, address);
    }

    @Override
    public List<SysUser> getUserList() {
        return sysUserMapper.getUserList();
    }

    @Override
    public int userTotal() {
        return sysUserMapper.userTotal();
    }

    @Override
    public int userTotalOnCondition(String username, String email, String address) {
        if(username!=null){
            username="%"+username+"%";
        }
        if(email!=null){
            email="%"+email+"%";
        }
        if(address!=null){
            address="%"+address+"%";
        }
        return sysUserMapper.userTotalOnCondition(username, email, address);
    }
}




