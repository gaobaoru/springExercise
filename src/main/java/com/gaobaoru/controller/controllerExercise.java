package com.gaobaoru.controller;

import com.gaobaoru.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by gaobaoru on 2017/2/21.
 */
@Controller
public class controllerExercise {
    //开始实验测试，验证成功
    @RequestMapping(path = {"/", "/exercise"})
    @ResponseBody
    public String index() {
        return "Hello gaobaoru";
    }

    //练习PathVariable RequestParam等注释的使用,测试不同默认值和是否有required值等方式
    @RequestMapping(path = {"/profile/{userName}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userName") String userName,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1") int type,
                          @RequestParam(value = "key", required = false) String key) {
        return String.format("Profile Page of %s / %d,   type:%d    key:%s",userName, userId, type, key);
    }

    //练习Velocity的基础语法，包括变量显示，数组遍历，注释等
    @RequestMapping(path = {"/vm"}, method = {RequestMethod.GET})
    //@ResponseBody  //使用模板默认调用templates文件，不需要ResponseBody
    public String template(Model model) {
        model.addAttribute("value1","gaobaoru");
        //model.addAttribute("value3","你好");
        //复杂数据结构List测试
        List<String> colors = Arrays.asList(new String[]{"RED","GREEN","BLUE","WRITE"});
        model.addAttribute("colors", colors);
        //Map测试
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            map.put(String.valueOf(i), String.valueOf(i*i));
        }
        model.addAttribute("map", map);
        model.addAttribute("user", new User("gaoabaoru"));
        return "home";
    }
    //request和response练习
    @RequestMapping(path = {"/request"}, method = {RequestMethod.GET})
    @ResponseBody
    public String request(Model model, HttpServletResponse response,
                          HttpServletRequest request,
                          HttpSession httpSession,
                          @CookieValue("JSESSIONID") String sessionId) {
        StringBuilder sb = new StringBuilder();
        sb.append("COOKIEVALUE:" + sessionId + "<br>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                sb.append("Cookie:" + cookie.getName() + " value:" + cookie.getValue());
            }
        }
        sb.append(request.getMethod() + "<br>");
        sb.append(request.getQueryString() + "<br>");
        sb.append(request.getPathInfo() + "<br>");
        sb.append(request.getRequestURI() + "<br>");

        response.addHeader("gaobaoruId", "hello");
        response.addCookie(new Cookie("username", "gaobaoru"));

        return sb.toString();
    }
}
