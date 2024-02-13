package com.toogether.controller;

import com.toogether.repo.BoardRepo;
import com.toogether.service.BoardService;
import org.aspectj.weaver.ast.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/check")
public class CheckController {
    @Autowired
    private BoardRepo boardRepo;
    @GetMapping("/new")
    public int getBoardID(){
        return boardRepo.findByMax()+1;
    }
    @GetMapping("/login")
    public String logincheck(HttpSession session){
        String userID = (String)session.getAttribute("userID");
        if(userID == null){
            return "none";
        }else{
            return "exist";
        }
    }
}
