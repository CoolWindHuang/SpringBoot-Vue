package com.wufeng.service;

import com.wufeng.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wufeng.pojo.dto.SysUserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Hxh
 * @description 针对表【sys_user】的数据库操作Service
 * @createDate 2023-09-16 15:03:10
 */
public interface SysUserService {

    List<SysUser> login(SysUserDto sysUserDto);

    List<SysUser> getUserList();

    int addUser(SysUser sysUser);

    int updateUser(SysUser sysUser);

    int deleteByUserId(int id);

    int deleteUsersByIds(List<Integer> Ids);

    List<SysUser> findPage(int pageIndex, int pageSize, String username, String email, String address);

    int userTotal();

    int userTotalOnCondition(@Param("username") String username, @Param("email") String email, @Param("address") String address);
}
