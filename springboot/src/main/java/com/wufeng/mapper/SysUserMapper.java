package com.wufeng.mapper;

import com.wufeng.pojo.SysUser;
import com.wufeng.pojo.dto.SysUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Hxh
 * @description 针对表【sys_user】的数据库操作Mapper
 * @createDate 2023-09-16 15:03:10
 * @Entity com.wufeng.pojo.SysUser
 */
@Mapper
public interface SysUserMapper {

    List<SysUser> login(SysUserDto sysUserDto);

    List<SysUser> getUserList();

    int addUser(SysUser sysUser);

    int updateUser(SysUser sysUser);

    int deleteByUserId(int id);

    int deleteUsersByIds(List<Integer> Ids);

    List<SysUser> findPage(@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize, @Param("username") String username, @Param("email") String email, @Param("address") String address);

    int userTotal();

    int userTotalOnCondition(@Param("username") String username, @Param("email") String email, @Param("address") String address);

}




