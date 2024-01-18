package com.toogether.service;

import com.toogether.dto.BoardUpdateDTO;
import com.toogether.mapper.BoardUpdateMapping;
import com.toogether.vo.BoardVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BoardService{
    List<BoardVO> getBoardList(String category);
    BoardVO getBoardVO(int boardID);
    Optional<BoardVO> updateAction(BoardUpdateDTO vo);
}
