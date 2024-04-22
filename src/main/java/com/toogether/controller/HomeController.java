package com.toogether.controller;

import com.toogether.service.BoardService;
import com.toogether.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController{

    @Autowired
    private final UserService userService;
    private final BoardService boardService;
    private final HttpSession httpSession;

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value={"/community"})
    public String community(Model model) {
        log.debug("컨트롤러의 community() 메소드");

        return "community";
    }
    @GetMapping("/spot")
    public String spot(HttpSession session, Model model){
        return "spot";
    }
    @GetMapping(value={"/", "/user/community"})
    public String home(Model model) {
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

//    @GetMapping("/get")
//    public String Accessmain(Model model){
//        Map<String, Object> result = service.getMain();
//        String text = (String)result.get("시작2");
//        model.addAttribute("mainText",text);
//        return "main";
//    }

}