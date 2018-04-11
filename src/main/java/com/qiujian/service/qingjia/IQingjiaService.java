package com.qiujian.service.qingjia;

import org.activiti.engine.task.Task;

import java.util.List;

public interface IQingjiaService {
    /**
     * 启动流程
     */
    void startActiviti(String username);

    /**
     * 查询当前用户下的待审批任务
     * @return
     */
    List<Task> queryTaskByAssignee(String username);
}
