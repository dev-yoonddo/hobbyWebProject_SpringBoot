package com.toogether.service;

import com.toogether.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface UserService {

    List<String> getEmailList();
    int joinCheckID(String userID);
    int joinAction(UserVO vo);
    //로그인 실행
    int loginAction(String userID, String userPassword);
    UserVO getUserVO(String userID);

    //회원 정보 업데이트
    int userUpdateAction(UserVO vo);

    //회원 탈퇴
    void userDeleteAction(String userID);
}
