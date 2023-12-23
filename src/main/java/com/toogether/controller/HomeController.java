package com.toogether.controller;

import com.toogether.repo.UserRepo;
import com.toogether.service.UserService;
import com.toogether.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class HomeController{

    @Autowired
    private final UserService service;

    public HomeController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String home() {
        System.out.println("컨트롤러의 home() 메소드");
        return "main";
    }
    @GetMapping("/get")
    public String Accessmain(Model model){
        Map<String, Object> result = service.getMain();
        String text = (String)result.get("시작2");
        model.addAttribute("mainText",text);
        return "main";
    }

}