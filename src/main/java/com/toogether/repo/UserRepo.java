package com.toogether.repo;

import com.toogether.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <UserVO, String> {
    //UserVO getUserVO(String userID);

    //findById는 Jpa에 기본적으로 존재하는 조회 기능으로 인터페이스에 작성하지 않고
    //직접 사용할 수 있지만 예를들어 userEmail값으로 조회하는 것 처럼 해당 엔티티에 특정된 조회 기능은
    //인터페이스에 작성해야 사용할 수 있다.
}
