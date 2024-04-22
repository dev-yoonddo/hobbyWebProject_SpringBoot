package com.toogether.controller;

import com.toogether.service.BoardService;
import com.toogether.service.UserService;
import com.toogether.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Controller
public class UserController {
    @Autowired
    private final UserService userService;
    private final BoardService boardService;

    //회원가입 페이지
    @RequestMapping("/join")
    public String join(Model model) {
        System.out.println("컨트롤러의 join() 메소드");
        model.addAttribute("emailList", userService.getEmailList());
        return "join";
    }
    //회원가입 실행
    @PostMapping("/join")
    public String joinAction(UserVO vo, Model model){
        System.out.println("시작");
        System.out.println(vo.toString());

        int result = userService.joinAction(vo);
        System.out.println("회원가입 결과 : " + result);
        if(result == 1){
            return "login";
        }else {
            model.addAttribute("joinError", "fail");
            model.addAttribute("vo", vo);
            return "join";
        }
    }
    //아이디 중복 체크
    @GetMapping("/join/checkID")
    public void joinCheckID(@RequestParam (required = true) String userID, HttpServletResponse response) throws IOException {
        PrintWriter script = response.getWriter();

        System.out.println("아이디체크: " + userID);
        int result = userService.joinCheckID(userID);
        System.out.println("아이디체크 결과: " + result);
        if(result == 0) {
            script.print("fail");
            script.close();
        }else {
            script.print("success");
            script.close();
        }
    }
    //로그인 페이지
    @GetMapping("/login")
    public String login() {
        System.out.println("컨트롤러의 login() 메소드");
        return "login";
    }
    //로그인 실행
    @PostMapping("/login")
    public String loginAction(@ModelAttribute UserVO vo, HttpSession session, Model model){
        System.out.println("시작");
//        HttpSession session = request.getSession(true);
//        String userID = request.getParameter("userID");
//        String userPassword = request.getParameter("userPassword");
        String userID = vo.getUserID();
        String userPassword = vo.getUserPassword();
        int result = userService.loginAction(userID, userPassword);
        System.out.println("로그인 요청 : " + result);
        System.out.println("아이디 : " + userID + "비밀번호" + userPassword);

        if(result == 1){
            session.setAttribute("userID", userID);
            return "redirect:/community";
        }else if(result == 0){
            model.addAttribute("loginError", "fail");
            return "login";
        }else {
            return "join";
        }
    }

    //회원 정보 페이지
    @GetMapping("/user/update")
    public String update(HttpSession session, HttpServletResponse response, Model model) throws IOException {
        System.out.println("컨트롤러의 update() 메소드");
        PrintWriter script = response.getWriter();
        String userID = null;
        if(session.getAttribute("userID") != null) {
            userID = (String) session.getAttribute("userID");
            UserVO vo = userService.getUserVO(userID);
            model.addAttribute("vo", vo);
            return "userUpdate";
        }
        return "login";

    }

    //회원 정보 수정 실행
    @PostMapping("/user/update")
    public String userUpdateAction(UserVO vo){
        System.out.println("회원 정보 업데이트");
        userService.userUpdateAction(vo);
        return "/user/update";
    }
    @GetMapping("/user/delete")
    public void userDeleteAction(@RequestParam String userID, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter script = response.getWriter();
        System.out.println("회원 정보 삭제");
        System.out.println(userID + "삭제");
        if(userID != null && session.getAttribute("userID") != null) {
            userService.userDeleteAction(userID);
            session.invalidate();
            script.print("success");
            script.close();
        }
        script.print("fail");
        script.close();
    }
//    @GetMapping(value={"/user/google"})
//    public String googleLogin(Model model, @LoginUser SessionUser user) {
//        if(user != null){
//            model.addAttribute("userID", user.getUserID());
//        }
//        return "community";
//    }
//    @GetMapping("/{id}")
//    public String getUserVO(@PathVariable String id){
//        UserVO user = userRepo.getById(id);
//        //userVO.ifPresent(System.out::println);
//        return user.getUserName();
//    }
    
}
