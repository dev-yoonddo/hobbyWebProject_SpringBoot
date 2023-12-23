package com.toogether.controller;

import com.toogether.repo.UserRepo;
import com.toogether.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/insert")
    public UserVO create(@RequestBody UserVO user){
        System.out.println("시작");
        return userRepo.save(user);
    }
    @GetMapping("/{id}")
    public String getUserVO(@PathVariable String id){
        UserVO user = userRepo.getById(id);
        //userVO.ifPresent(System.out::println);
        return user.getUserName();
    }
    
}
