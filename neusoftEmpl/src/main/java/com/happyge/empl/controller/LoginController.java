package com.happyge.empl.controller;


import com.happyge.empl.model.HllcUser;
import com.happyge.empl.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")

public class LoginController {
    @Autowired
    private UserService userService;

    //正常访问login页面
    @RequestMapping("/index")
    public String index(){
        return "/index.jsp";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "/login.jsp";
    }

    @RequestMapping("/refuse")
    public String refuse(){
        return "/login.jsp";
    }


    @RequestMapping(value = "/dologin")
    @ResponseBody
    public String doLogin(String username, String password) {
        //得到Subject,通过SecurityUtils得到Subject，其会自动绑定到当前线程；如果在web环境在请求结束时需要解除绑定
        Subject subject = SecurityUtils.getSubject();
        //创建用户名/密码身份验证Token（即用户身份/凭证）
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
            try {
            /*
             * 身份验证，调用subject.login方法进行登录，其会自动委托给SecurityManager.login方法进行登录
             * 通过login登录，如果登录失败将抛出相应的AuthenticationException，
             * 如果登录成功调用subject.isAuthenticated就会返回true，即已经通过身份验证
             * 如果isRemembered返回true，表示是通过记住我功能登录的而不是调用login方法登录的
             * isAuthenticated/isRemembered是互斥的，即如果其中一个返回true，另一个返回false
             */
            System.out.println("登录认证成功");
            subject.login(token);
        } catch (AuthenticationException e) {
            /*
             *  如果身份验证失败请捕获AuthenticationException或其子类，常见的如：
             *  DisabledAccountException（禁用的帐号）、
             *  LockedAccountException（锁定的帐号）、
             *  UnknownAccountException（错误的帐号）、
             *  ExcessiveAttemptsException（登录失败次数过多）、
             *  IncorrectCredentialsException （错误的凭证）、
             *  ExpiredCredentialsException（过期的凭证）等，具体请查看其继承关系
             *  对于页面的错误消息展示，最好使用如“用户名/密码错误”而不是“用户名错误”/“密码错误”，防止一些恶意用户非法扫描帐号库
             */
                System.out.println("登录认证失败");
            return "false";
        }
        return "ture";
    }

    @RequestMapping("/doindexLogout")
    public String doLogout() {
        //退出操作后一定要重定向页面
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "/login.jsp";
    }

    @RequestMapping("/regist")
    public String regist(){
        return "regist";
    }

    @RequestMapping("/doRegist")
    public String doRegist(HllcUser user, Model model){
        System.out.println(user.getUname());
        userService.register(user);
        return "success";
    }
    @RequestMapping("/error")
    public  String error(){
        return "/error";
    }
}
