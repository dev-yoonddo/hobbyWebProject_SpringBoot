package com.toogether.service.lmpl;

import com.toogether.repo.UserRepo;
import com.toogether.service.UserService;
import com.toogether.vo.BoardVO;
import com.toogether.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicelmpl implements UserService {
    private UserRepo userRepo;

    @Autowired
    public void UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<String> getEmailList(){
        System.out.println("getEmailList() 메서드");
        List<UserVO> list = userRepo.findAll();
        List<String> emailList = new ArrayList<>();
        for(UserVO i : list){
            emailList.add(i.getUserEmail());
        }
        System.out.println("이메일: "+ emailList);
        return emailList;
    }
    //아이디 중복 검사
    @Override
    public int joinCheckID(String userID) {
        boolean user = userRepo.existsById(userID);
        System.out.println("아이디 일치 결과  : " + user);
        return (user) ? 0 : 1;
    }
    //회원가입
    @Override
    public int joinAction(UserVO vo){
        int exist = joinCheckID(vo.getUserID()); //아이디가 이미 존재하는지 다시 검사
        if(exist == 1) { //존재하지 않으면 회원가입 실행
            userRepo.save(vo);
            return 1;
        }else{
            return 0;
        }
//		}else {
//			List<String> emailList = userDAO.getEmailList();
//			request.setAttribute("emailList", emailList);
//			request.setAttribute("vo", vo);
//		}

    }

    //로그인 실행
    @Override
    public int loginAction(String userID, String userPassword) {
        System.out.println("userService loginAction 실행");
        int result;
        try {
            UserVO user = userRepo.getById(userID);
            if(userID.equals(user.getUserID())){
                if (userPassword.equals(user.getUserPassword())) { //userID는 존재하지만 userPqssword가 일치하지 않는 경우
                    return 1;
                } else{
                    return 0;
                }
            }else{
                return -1;
            }
        }catch (EntityNotFoundException e){
            e.printStackTrace();
        }
        return -1;
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
    public void userDeleteAction(String userID) {
        System.out.println("탈퇴유저: " +userID);
        userRepo.delete(userRepo.getById(userID));
    }
}
