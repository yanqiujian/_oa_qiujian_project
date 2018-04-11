package com.qiujian.controller.qingjiacontroller;

import com.qiujian.dao.qingjiadao.IQingjiaDAO;
import com.qiujian.dto.QingjiaDto;
import com.qiujian.dto.UserDto;
import com.qiujian.service.qingjia.IQingjiaService;
import com.qiujian.service.userservice.IUserService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/qingjia")
public class QingjiaController {

    @Autowired
    private IQingjiaDAO qingjiaDAO;

    @Autowired
    private IQingjiaService qingjiaService;
    @Autowired
    private IUserService userService;
    @Autowired
    private TaskService taskService;
    @RequestMapping("/add")
    public String addQingjia(String qjLx,String days, String qjShichang, String qjBeizhu, HttpSession session){
        String df= Calendar.getInstance().getTime().toString();
        String username = (String) session.getAttribute("username");
        //根据用户姓名查询用户信息
        UserDto userDto1 = userService.queryUserByName(username);
        String userGanwei = userDto1.getUserGanwei();
        //或取该用户的主管信息
        String s1 = userService.queryBMByName(username);//部门信息
        UserDto userDto = userService.queryUserNameByBumenName(s1);//主管信息
        String userName = userDto.getUserName();//主管名字
        UserDto userDto2 = userService.queryUserNameCEO();//CEO信息
        String userName1 = userDto2.getUserName();//CEO名字
        int i = Integer.parseInt(days);
        String user = null;
        if (i<3){
            user=userName;
        }
        if (i>=3){
            user=userName1;
        }
        //启动流程
        qingjiaService.startActiviti(username);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("user",user);//受理人名字
        map.put("username",username);//提交人名字
        map.put("days",days);//请假天数
        //获取当前用户待审批的任务
        List<Task> tasks = qingjiaService.queryTaskByAssignee(username);
        for (Task task : tasks){
            String id = task.getId();
            taskService.complete(id,map);//提交请假单
            QingjiaDto qingjiaDto = new QingjiaDto();
            qingjiaDto.setQjPrid(id);
            qingjiaDto.setQjLx(qjLx);
            qingjiaDto.setQjGw(userGanwei);
            qingjiaDto.setQjApplyname(username);
            qingjiaDto.setQjTime(df);
            qingjiaDto.setQjBm(s1);
            qingjiaDto.setQjBeizhu(qjBeizhu);
            qingjiaDto.setQjShichang(qjShichang);
            qingjiaDto.setQjSpr(user);
            qingjiaDAO.insertHistory(qingjiaDto);
        }
        return "/index.jsp";
    }

    @RequestMapping("/shenpi")
    public String shenpi(HttpSession session,Model model){
        String username = (String) session.getAttribute("username");
        List<QingjiaDto> shenPiInfoList = qingjiaDAO.queryZhuguanList(username);
        model.addAttribute("list",shenPiInfoList);
        return "/page_qingjia.jsp";
    }

    @RequestMapping("/my/qingjia")
    public String myKaoqing(HttpSession session,Model model){
        String username = (String) session.getAttribute("username");
        List<QingjiaDto> histories = qingjiaDAO.queryApllyList(username);
        model.addAttribute("list",histories);
        return "/my_qingjia.jsp";
    }

    @RequestMapping("/approve")
    public String approve(String qjPrid){
        qingjiaDAO.updateZhuangtai(qjPrid);
        return "/qingjia/shenpi";
    }
}
