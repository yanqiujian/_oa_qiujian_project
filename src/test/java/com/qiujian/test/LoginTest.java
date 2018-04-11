package com.qiujian.test;

import com.qiujian.dao.historydao.IHistoryDao;
import com.qiujian.dao.userdao.IUserDAO;
import com.qiujian.dto.History;
import com.qiujian.dto.PemissionDto;
import com.qiujian.dto.RolesDto;
import com.qiujian.dto.UserDto;
import com.qiujian.service.userservice.IUserService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.task.Task;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class LoginTest {
    @Autowired
    private IUserService userService;
    @Autowired
    private IHistoryDao historyDao;
    @Autowired
    private IUserDAO userDAO;
    @Test
    public void MD5Test(){
        SimpleHash simpleHash = new SimpleHash("MD5", "123", "wangwu");
        System.out.println(simpleHash);
    }
    @Test
    public void testCase1(){
        UserDto userDto = userService.queryUserByName("zhao");
        System.out.println(userDto.getUserGanwei());
    }
    @Test
    public void testCase2(){
        List<String> list = userService.queryJsByName("zhao");
        for (String str : list){
            System.out.println(str);
        }
    }
    @Test
    public void testCase3(){
        List<String> list = userService.queryQXByName("zhao");
        for (String str : list){
            System.out.println(str.toString());
        }
//        List<PemissionDto> zhao = userDAO.queryQXByName("zhao");
//        for (PemissionDto pemissionDto :zhao){
//            System.out.println(pemissionDto.getPmName());
//        }
    }

    @Test
    public void testCase4(){

        String zhao = userService.queryBMByName("zhao");
        System.out.println(zhao);
    }

    @Test
    public void testCase5(){
        UserDto userDto = userService.queryUserNameByBumenName("diaoyanbu");
        System.out.println(userDto.getUserName());
    }

    @Test
    public void testCase6(){
        History history = new History();
        history.setHsApplyname("1");
        history.setHsDepartment("2");
        history.setHsDidian("3");
        history.setHsFangshi("4");
        history.setHsJihao("5");
        history.setHsGanwei("6");
        history.setHsZhuguanname("7");
        history.setHsTime("8");
        history.setHsPrid("9");
        historyDao.insertHistory(history);
    }

    @Test
    public void testCase7(){
        List<History> histories = historyDao.queryZhuguanList("7");
            System.out.println(histories.size());
    }
    @Test
    public void testCase8(){
        List<History> histories = historyDao.queryApllyList("1");
        System.out.println(histories.size());
    }
    @Test
    public void testCase9(){
        userService.selectUserActiviti("zhangsan");
    }
    @Test
    public void testCase10(){
        List<Task> zhangsan = userService.selectUserActiviti("zhangsan");
        for (Task task:zhangsan){
            System.out.println(task.getId());
        }
    }

}
