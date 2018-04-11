package com.qiujian.controller.usercontroller;

import com.qiujian.dao.historydao.IHistoryDao;
import com.qiujian.dto.History;
import com.qiujian.dto.UserDto;
import com.qiujian.service.userservice.IUserService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/login")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IHistoryDao historyDao;

    @RequestMapping("/oa")
    public String login(String username, String password, HttpSession session){
        System.out.println(username + "....." + password);
        //Shiro进行登录
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SecurityUtils.getSubject().login(token);
            session.setAttribute("password",password);
            session.setAttribute("username",username);
//            List<String> list = userService.queryJsByName(username);
//            System.out.println(list.get(0));
//            if (list.get(0).equals("zhuguan")||list.get(0).equals("CEO")){
//                return "/main.html";
//            }
            return "/index.jsp";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("用户明或密码有误，请先注册或者输入正确的用户名和密码");
        }
        return "/login.jsp";
    }
    @RequestMapping("/activiti")
    public String buqian(String jihao, String fangshi, String didian, HttpSession session){
       String df= Calendar.getInstance().getTime().toString();
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String username = (String) session.getAttribute("username");
        System.out.println(username + jihao + fangshi + didian);
        //根据用户姓名查询用户信息
        UserDto userDto1 = userService.queryUserByName(username);
        //或取该用户的主管信息
       String s1 = userService.queryBMByName(username);//部门信息
        UserDto userDto = userService.queryUserNameByBumenName(s1);//主管信息
        String userName = userDto.getUserName();
        //启动流程
        userService.startActiviti(username);
        List<Task> tasks = userService.selectUserActiviti(username);
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("user",userName);//指定受理人（提交给哪位主管）
        map.put("time",df);//指定补签时间
        map.put("ganwei",userDto1.getUserGanwei());//工作岗位
        map.put("jihao",jihao);//考勤机号
        map.put("fangshi",fangshi);//考勤方式
        map.put("didian",didian);//考勤地点
        map.put("applyuser",username);
        map.put("department",s1);
        //参数1：任务的ID
        //参数2：当前任务需要携带的数据（补签数据）
        for (Task task : tasks) {
            String id = task.getId();
            taskService.complete(id, map);//提交补签申请
            History history = new History();
            history.setHsTime(df);
            history.setHsZhuguanname(userName);
            history.setHsGanwei(userDto1.getUserGanwei());
            history.setHsJihao(jihao);
            history.setHsFangshi(fangshi);
            history.setHsDidian(didian);
            history.setHsDepartment(s1);
            history.setHsApplyname(username);
            history.setHsPrid(id);
            historyDao.insertHistory(history);
        }

        return "/index.jsp";
    }
    @RequestMapping("/shenpi")
    public String shenpi(HttpSession session,Model model){
        String username = (String) session.getAttribute("username");
        List<History> shenPiInfoList = historyDao.queryZhuguanList(username);
        model.addAttribute("list",shenPiInfoList);
        return "/page_kaoqian.jsp";
    }
    @RequestMapping("/my/kaoqing")
    public String myKaoqing(HttpSession session,Model model){
        String username = (String) session.getAttribute("username");
        List<History> histories = historyDao.queryApllyList(username);
        model.addAttribute("list",histories);
        return "/my_kaoqian.jsp";
    }
    @RequestMapping("/approve")
    public String approve(String hsPrid){
        historyDao.updateZhuangtai(hsPrid);
        return "/login/shenpi";
    }
}
