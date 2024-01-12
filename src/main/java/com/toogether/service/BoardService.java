package com.toogether.service;

import com.toogether.mapper.BoardUpdateMapping;
import com.toogether.vo.BoardVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoardService{
    List<BoardVO> getBoardList(String category);
    BoardVO getBoardVO(int boardID);
    int updateAction(BoardUpdateMapping vo);
}
