package com.toogether.repo;

import com.toogether.dto.BoardUpdateDTO;
import com.toogether.vo.BoardVO;
import com.toogether.vo.HeartVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepo extends JpaRepository<HeartVO,Integer> {
    HeartVO findByBoardIDAndUserID(int boardID, String userID);
}
