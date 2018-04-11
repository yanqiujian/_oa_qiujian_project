package com.qiujian.service.userservice;

import com.qiujian.dao.userdao.IUserDAO;
import com.qiujian.dto.BumenDto;
import com.qiujian.dto.PemissionDto;
import com.qiujian.dto.RolesDto;
import com.qiujian.dto.UserDto;
import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserDAO userDAO;
    /**
     * 判断用户登陆名称是否正确
     *
     * @param userName
     * @return
     */
    public UserDto queryUserByName(String userName) {
        UserDto userDto = userDAO.queryUserByName(userName);
        return userDto;
    }

    /**
     * 通过用户名查询对应的角色信息
     *
     * @param userName
     */
    public List<String> queryJsByName(String userName) {
        List<RolesDto> strings = userDAO.queryJsByName(userName);
        List<String> rolesListName = new ArrayList<String>();
        for (RolesDto rolesDto : strings){
            rolesListName.add(rolesDto.getRolesName());
        }
        return rolesListName;
    }

    /**
     * 通过用户名查询对应角色的权限
     *
     * @param userName
     */
    public List<String> queryQXByName(String userName) {
        List<PemissionDto> strings = userDAO.queryQXByName(userName);
        List<String> pemissionListName = new ArrayList<String>();
        for (PemissionDto pemissionDto : strings){
            pemissionListName.add(pemissionDto.getPmName());
        }
        return pemissionListName;
    }

    /**
     * 根据用户名查询所在部门
     *
     * @param userName
     */
    public String queryBMByName(String userName) {
        BumenDto bumenDtos = userDAO.queryBMByName(userName);
        String bmName = bumenDtos.getBmName();
        return bmName;
    }

    /**
     * 根据部门信息查询该部门主管姓名
     *
     * @param bmName
     */
    public UserDto queryUserNameByBumenName(String bmName) {
        UserDto userDto = userDAO.queryUserNameByBumenName(bmName);
        return userDto;
    }

    /**
     * 查询公司CEO姓名
     */
    public UserDto queryUserNameCEO() {
        UserDto userDto = userDAO.queryUserNameCEO();
        return userDto;
    }


    /**
     * 启动流程
     *
     * @param userName
     */
    public void startActiviti(String userName) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //RuntimeService是用来启动流程的服务。
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //启动流程
        //创建Map用来存放流程所需要的变量
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("user",userName);
        //参数1：流程的key
        //参数2：流程所需的变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1",map);
        String id = processInstance.getId();
        String businessKey = processInstance.getBusinessKey();
        String name = processInstance.getName();
    }

    /**
     * 查询当前用户下的流程节点
     */
    public List<Task> selectUserActiviti(String userName) {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        //TaskService是一个用来操作流程节点的服务（任务服务），比如流程启动之后的所有流程操作，都是TaskService完成
        TaskService taskService = defaultProcessEngine.getTaskService();
        List<Task> lisi = taskService.createTaskQuery()
                .taskAssignee(userName)
                .list();

        return lisi;
    }
}
