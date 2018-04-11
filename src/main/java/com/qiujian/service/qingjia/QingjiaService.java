package com.qiujian.service.qingjia;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class QingjiaService implements IQingjiaService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;


    /**
     * 启动流程
     *
     * @param username
     */
    public void startActiviti(String username) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("qjApplyname",username);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myQingjia", map);
        String id = processInstance.getId();
    }

    /**
     * 查询当前用户下的待审批任务
     *
     * @param username
     * @return
     */
    public List<Task> queryTaskByAssignee(String username) {
        List<Task> taskList = taskService.createTaskQuery()
                .taskAssignee(username)
                .list();
        return taskList;
    }
}
