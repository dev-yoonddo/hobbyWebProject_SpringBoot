package com.toogether.controller;

import com.toogether.repo.UserRepo;
import com.toogether.service.UserService;
import com.toogether.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class HomeController{

    @Autowired
    private final UserService userService;

    public HomeController(UserService service) {
        this.userService = service;
    }
    @RequestMapping(value={"/community"})
    public String community() {
        System.out.println("컨트롤러의 community() 메소드");
        return "community";
    }
    @GetMapping(value={"/", "/user/community"})
    public String home() {
        System.out.println("컨트롤러의 home() 메소드");
        return "redirect:/community";
    }
    @RequestMapping("/logout")
    public String logoutAction(HttpServletRequest request){
        System.out.println("로그아웃");
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:community";
    }
//    @GetMapping("/get")
//    public String Accessmain(Model model){
//        Map<String, Object> result = service.getMain();
//        String text = (String)result.get("시작2");
//        model.addAttribute("mainText",text);
//        return "main";
//    }

}