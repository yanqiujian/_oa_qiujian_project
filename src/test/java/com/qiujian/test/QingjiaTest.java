package com.qiujian.test;

import com.qiujian.dao.qingjiadao.IQingjiaDAO;
import com.qiujian.dto.QingjiaDto;
import com.qiujian.service.qingjia.IQingjiaService;
import org.activiti.engine.task.Task;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class QingjiaTest {

    @Autowired
    private IQingjiaDAO qingjiaDAO;
    @Autowired
    private IQingjiaService qingjiaService;
    @Test
    public void Test1(){
        QingjiaDto qingjiaDto = new QingjiaDto();
        qingjiaDto.setQjBm("1");
        qingjiaDto.setQjBeizhu("2");
        qingjiaDto.setQjShichang("3");
        qingjiaDto.setQjTime("4");
        qingjiaDto.setQjApplyname("5");
        qingjiaDto.setQjGw("6");
        qingjiaDto.setQjLx("7");
        qingjiaDto.setQjPrid("8");
        qingjiaDto.setQjSpr("9");
        qingjiaDAO.insertHistory(qingjiaDto);
    }
    @Test
    public void test2(){
        List<QingjiaDto> qingjiaDtos = qingjiaDAO.queryApllyList("333");
        System.out.println(qingjiaDtos.size());
    }
    @Test
    public void test3(){
        List<QingjiaDto> qingjiaDtos = qingjiaDAO.queryZhuguanList("44");
        System.out.println(qingjiaDtos.size());
    }
    @Test
    public void test4(){
        qingjiaDAO.updateZhuangtai("8");
    }
    @Test
    public void test5(){
        qingjiaService.startActiviti("zhangsan");
    }
    @Test
    public void test6(){
        List<Task> tasks = qingjiaService.queryTaskByAssignee("zhangsan");
        for (Task task:tasks){
            System.out.println(task.getId());
        }
    }
}
