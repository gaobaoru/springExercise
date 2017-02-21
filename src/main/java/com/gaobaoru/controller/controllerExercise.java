package com.gaobaoru.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("value3","你好");
        return "home";
    }

}
