package com.toogether.service;

import com.toogether.dto.BoardUpdateDTO;
import com.toogether.mapper.BoardUpdateMapping;
import com.toogether.vo.BoardVO;
import com.toogether.vo.HeartVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BoardService{
    List<BoardVO> getBoardList(String category);
    void viewCount(int boardID);
    BoardVO getBoardVO(int boardID);
    BoardVO writeAction(BoardVO vo);
    Optional<BoardVO> updateAction(BoardUpdateDTO vo);
    int deleteCheck(int boardID);
    void deleteAction(int boardID);
    String heartCheck(int boardID, String userID);
    int heartAction(int boardID, String userID, String value);
    int heartCountAction(int boardID, String value);
}
