package com.qiujian.service.userservice;

import com.qiujian.dto.UserDto;
import com.sun.javafx.tk.Toolkit;
import org.activiti.engine.task.Task;

import java.util.List;

public interface IUserService {

    /**
     * 判断用户登陆名称是否正确
     * @param userName
     * @return
     */
    UserDto queryUserByName(String userName);

    /**
     * 通过用户名查询对应的角色信息
     */
    List<String> queryJsByName(String userName);
    /**
     * 通过用户名查询对应角色的权限
     */
    List<String> queryQXByName(String userName);
    /**
     * 根据用户名查询所在部门
     */
    String queryBMByName(String userName);
    /**
     * 根据部门信息查询该部门主管姓名
     */
    UserDto queryUserNameByBumenName(String bmName);

    /**
     * 查询公司CEO姓名
     */
    UserDto queryUserNameCEO();

    /**
     *启动流程
     */
    void startActiviti(String userName);
    /**
     * 查询当前用户下的流程节点
     */
    List<Task> selectUserActiviti(String userName);

}
