package com.qiujian.dao.userdao;

import com.qiujian.dto.BumenDto;
import com.qiujian.dto.PemissionDto;
import com.qiujian.dto.RolesDto;
import com.qiujian.dto.UserDto;

import java.util.List;

public interface IUserDAO {
    /**
     * 判断用户登陆名称是否正确
     * @param userName
     * @return
     */
    UserDto queryUserByName(String userName);

    /**
     * 通过用户名查询对应的角色信息
     */
    List<RolesDto> queryJsByName(String userName);
    /**
     * 通过用户名查询对应角色的权限
     */
    List<PemissionDto> queryQXByName(String userName);
    /**
     * 根据用户名。查询所在的部门
     */
    BumenDto queryBMByName(String userName);
    /**
     * 根据部门信息查询该部门主管姓名
     */
    UserDto queryUserNameByBumenName(String bmName);
    /**
     * 查询公司CEO姓名
     */
    UserDto queryUserNameCEO();
}
