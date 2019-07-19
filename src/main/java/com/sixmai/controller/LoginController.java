package com.sixmai.controller;

import com.sixmai.domain.UserInfo;
import com.sixmai.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
@SessionAttributes({"username","role"})
public class LoginController {
    private LoginService loginService;
    @Autowired
    public void setLoginService(@Qualifier("loginServiceImpl") LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login( @RequestParam("username") String username,@RequestParam("password") String password,
                        @RequestParam("role") int role) {

        String loginname = new String(username);
        Map<String, Object> map = loginService.login(loginname, password, role);

        if (map.get("status").equals("200")) {
            return "login_success";
        }
        else {
            String msg = new String(map.get("msg").toString());
//            if(msg.equals("noUser")){
//
//            }else if(msg.equals("passwordError")){
//
//            }else if(msg.equals("noRoot"))

            System.out.println("msg :" + msg);
            return msg;
        }
    }

    @RequestMapping("/login_success")
    public String gotoLoginSuccessPage(){
        return "login_success";
    }

    @RequestMapping(value="/logout")
    public String gotoLogout(HttpServletRequest request, SessionStatus sessionStatus)
    {
        //清除session
        sessionStatus.setComplete();
        Enumeration<String> em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            String attrName = em.nextElement().toString();
            request.getSession().setAttribute(attrName, null);
            request.getSession().removeAttribute(attrName);
        }
        request.getSession().invalidate();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return "redirect:" + basePath;
        //return basePath;

    }

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        List<UserInfo> userInfos=loginService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("ui",userInfos);
        mv.setViewName("allUser");
        return mv;
    }

    @RequestMapping("/addUser")
    public ModelAndView addUser(UserInfo userInfo){
        loginService.addUser(userInfo);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("addUser");
        return mv;
    }
}
