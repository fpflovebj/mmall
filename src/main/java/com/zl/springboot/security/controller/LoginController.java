package com.zl.springboot.security.controller;

import com.zl.springboot.security.bean.Users;
import com.zl.springboot.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService service;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, String username, String password, HttpSession session, HttpServletRequest request) {
        //封装前台传入参数
        Users param = new Users(username, password);
        Users user = service.queryUser(param);
        if (user == null) {
            logger.info("登录失败-帐号:" + username + "-密码:" + password + ",ip1:" + request.getRemoteAddr() + ",ip2:" + request.getLocalAddr() + ",ip3:" + request.getRemoteHost());
            model.addAttribute("msg", "<script>alert('帐号信息错误！');</script>");
            return "login";
        } else {
            logger.info("查询到了用户:" + user);
            //将用户保存一登陆列表
            ArrayList<String> loginList =  session.getServletContext().getAttribute("loginlist") == null ? new ArrayList<String>() : (ArrayList<String>)session.getServletContext().getAttribute("loginlist");
            //判断用户是否登录

            if (loginList.contains(user.getName())) {
                //该帐号已登陆
                logger.info("查询到了用户:" + user.getName() + "已登陆");
                model.addAttribute("msg", "<script>alert('该帐号已登陆！');</script>");
                return "login";
            } else {
                session.setAttribute("currentUser", user);//保存用户
                loginList.add(user.getName());//保存到登录用户列表
                session.getServletContext().setAttribute("loginlist", loginList);// 添加到loginlist
                return "index";
            }
        }
    }

    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String loginout(HttpSession session) {
        Users user = (Users) session.getAttribute("currentUser");
        String userName = user.getName();
        //一登陆用户列表
        ArrayList<String> loginList =  session.getServletContext().getAttribute("loginlist") == null ? new ArrayList<String>() : (ArrayList<String>)session.getServletContext().getAttribute("loginlist");
        loginList.remove(userName);
        session.getServletContext().setAttribute("loginlist", loginList);
        logger.info("loginout后的用户列表" + loginList);
        return "login";
    }

   /* private String removeUser(String loginList, String userName) {
        String[] userArray = loginList.split("-");
        for (int i = 0; i < userArray.length; i++) {
            if (userArray[i].equals(userName)) {
                userArray[i] = "";
                break;
            }
        }
        if (userArray.length > 0) {
            return join(userArray, "-");
        }else{
            return "";
        }
    }

    private String join(String[] array, String spli) {
        StringBuffer sb = new StringBuffer();
        for (String item : array) {
            if (item.equals("")) {
                sb.append(item);
                sb.append(spli);
            }
        }
        String str = sb.toString();
        int index = str.lastIndexOf("-");
        if (index == str.length() - 1) {
            str = str.substring(0, index);
        }
        return str;
    }*/
}
