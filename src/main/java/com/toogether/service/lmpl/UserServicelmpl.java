package com.toogether.service.lmpl;

import com.toogether.repo.UserRepo;
import com.toogether.service.UserService;
import com.toogether.vo.BoardVO;
import com.toogether.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicelmpl implements UserService {
    private UserRepo userRepo;

    @Autowired
    public void UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<String> getEmailList(){
        List<UserVO> list = userRepo.findAll();
        List<String> emailList = new ArrayList<>();
        for(UserVO i : list){
            emailList.add(i.getUserEmail());
        }
        return emailList;
    }
    //아이디 중복 검사
    @Override
    public int joinCheckID(String userID) {
        UserVO user = userRepo.getById(userID);
        int exist = (user.getUserID().equals(userID)) ? 1 : 0;
        return exist;
    }
    //회원가입
    @Override
    public int joinAction(UserVO vo){
        int exist = joinCheckID(vo.getUserID()); //아이디가 이미 존재하는지 다시 검사
        if(exist == 0) { //존재하지 않으면 회원가입 실행
            userRepo.save(vo);
        }
//		}else {
//			List<String> emailList = userDAO.getEmailList();
//			request.setAttribute("emailList", emailList);
//			request.setAttribute("vo", vo);
//		}
        return exist;
    }

    //로그인 실행
    @Override
    public boolean loginAction(String userID, String userPassword) {
        UserVO user = userRepo.getById(userID);
        return user.getUserPassword().equals(userPassword);
    }
    //회원 정보 가져오기
    @Override
    public UserVO getUserVO(String userID) {
        System.out.println("UserService: "+userID);
        UserVO vo = userRepo.getById(userID);
        System.out.println("회원 정보: "+vo);
        return vo;
    }
    //회원 정보 업데이트
    @Override
    public int userUpdateAction(UserVO vo) {
        return userRepo.save(vo).getUserID().equals(vo.getUserID()) ? 1 : 0;
    }
    //회원 탈퇴
    @Override
    public void userDeleteAction(UserVO vo) {
        System.out.println("탈퇴유저: " +vo.getUserID());
        userRepo.delete(userRepo.getById(vo.getUserID()));
    }
}
