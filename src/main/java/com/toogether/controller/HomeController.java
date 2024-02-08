package com.toogether.controller;

import com.toogether.service.BoardService;
import com.toogether.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class HomeController{

    @Autowired
    private final UserService userService;
    private final BoardService boardService;
    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    public HomeController(UserService service, BoardService boardService) {
        this.userService = service;
        this.boardService = boardService;
    }
    @RequestMapping(value={"/community"})
    public String community() {
        log.debug("컨트롤러의 community() 메소드");
        return "community";
    }
    @GetMapping(value={"/", "/user/community"})
    public String home() {
        log.debug("컨트롤러의 home() 메소드");
        return "redirect:/community";
    }
    @RequestMapping("/logout")
    public String logoutAction(HttpServletRequest request){
        log.debug("로그아웃");
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:community";
    }
    @RequestMapping("/logincheck")
    public String check(HttpSession session, Model model){
        String userID = (String)session.getAttribute("userID");
        if(userID == null){
            model.addAttribute("msg","로그인이 필요합니다");
            model.addAttribute("url","login");
        }else{
            model.addAttribute("url","userUpdate");
        }
        return "community";
    }
//    @GetMapping("/get")
//    public String Accessmain(Model model){
//        Map<String, Object> result = service.getMain();
//        String text = (String)result.get("시작2");
//        model.addAttribute("mainText",text);
//        return "main";
//    }

}