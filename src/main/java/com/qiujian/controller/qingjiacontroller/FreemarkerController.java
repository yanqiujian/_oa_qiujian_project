package com.qiujian.controller.qingjiacontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gua")
public class FreemarkerController {

    @RequestMapping("/pi")
    public String guapi(ModelMap map){
        map.put("hi","灰机中的战斗机");
        return "/index.html";
    }
}
