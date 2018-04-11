package com.qiujian.tool;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;

public class ActivitiInni {

    /**
     * 完成Activiti初始化
     */
    public static void init(){
        ProcessEngineConfiguration configuration =  ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        configuration.buildProcessEngine();
    }

    /**
     * 部署流程
     */
    public static void bushuActiviti() {
        //activiti是工作流引擎。所以activiti的核心API都是通过ProcessEngine类来进行操作。
        //整个项目中建议ProcessEngine对象是唯一的。
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //repositoryService仓库服务用来部署流程
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //部署流程的操作者（流程部署的操作工）
        DeploymentBuilder deployment = repositoryService.createDeployment();
        deployment.addClasspathResource("bpmn/buqian.bpmn");//流程图
        deployment.addClasspathResource("bpmn/buqian.png");//图片
        deployment.name("补签");
        deployment.key("buqian");
        deployment.category("A");
        deployment.deploy();
    }

    /**
     * 部署流程
     */
    public static void publishProcess(){
        ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService()
                .createDeployment()
                .name("请假流程")
                .addClasspathResource("bpmn/qingjia.bpmn")
                .deploy();
    }

    public static void main(String[] args) {
        //init();
        bushuActiviti();
        //publishProcess();
    }
}
