package com.toogether.controller;

import com.toogether.repo.UserRepo;
import com.toogether.service.UserService;
import com.toogether.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserService userService;
    Model model;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/join")
    public String join() {
        System.out.println("컨트롤러의 join() 메소드");
        model.addAttribute("emailList", userService.getEmailList());
        return "join";
    }
    @RequestMapping("/login")
    public String login() {
        System.out.println("컨트롤러의 login() 메소드");
        return "login";
    }
    @GetMapping("/update")
    public String update(HttpSession session) {
        System.out.println("컨트롤러의 update() 메소드");
        String userID = (String)session.getAttribute("userID");
        UserVO vo = userService.getUserVO(userID);
        model.addAttribute("vo",vo);
        return "update";
    }
    @PostMapping("/join/action")
    public int joinAction(@RequestBody UserVO vo){
        System.out.println("시작");
        return userService.joinAction(vo);
    }
    @GetMapping("/login/action")
    public boolean loginAction(@RequestBody String userID, String userPassword){
        System.out.println("시작");
        return userService.loginAction(userID, userPassword);
    }
    @PostMapping("/update/action")
    public int userUpdateAction(@RequestBody UserVO vo){
        System.out.println("회원 정보 업데이트");
        return userService.userUpdateAction(vo);
    }
    @GetMapping("/delete/action")
    public String userDeleteAction(@RequestBody UserVO vo){
        System.out.println("회원 정보 업데이트");
        userService.userDeleteAction(vo);
        return "redirect::main";
    }
//    @GetMapping("/{id}")
//    public String getUserVO(@PathVariable String id){
//        UserVO user = userRepo.getById(id);
//        //userVO.ifPresent(System.out::println);
//        return user.getUserName();
//    }
    
}
