package com.qiujian.controller.usercontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CodeController {
    @RequestMapping("/code")
    public void genarateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ValidateCode validateCode = new ValidateCode(180, 40, 4, 80);

        String code = validateCode.getCode();//得到验证码的具体字符

        System.out.println(code);

        request.getSession().setAttribute("code", code);// 将验证码字符存到session中

        response.setContentType("image/jpeg"); //设置响应类型为图片



        validateCode.write(response.getOutputStream());// 将生成的验证码写到页面中

    }
}
