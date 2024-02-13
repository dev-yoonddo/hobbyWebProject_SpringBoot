package com.toogether.service;

import com.toogether.vo.UserVO;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
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
