package com.toogether.service.lmpl;

import com.toogether.mapper.BoardUpdateMapping;
import com.toogether.repo.BoardRepo;
import com.toogether.service.BoardService;
import com.toogether.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private final BoardRepo boardRepo;

    public BoardServiceImpl(BoardRepo boardRepo) {
        this.boardRepo = boardRepo;
    }

    @Override
    public List<BoardVO> getBoardList(String category){
        List<BoardVO> allList = boardRepo.findAll();
        List<BoardVO> boardList = new ArrayList<>();
        for(BoardVO i:allList){
            if(category.toUpperCase().equals(i.getBoardCategory())){
                boardList.add(i);
            }
        }
        System.out.println(boardList);
        return boardList;
    }
    @Override
    public BoardVO getBoardVO(int boardID){
        BoardVO vo = boardRepo.getById(boardID);
        return  vo;
    }
    @Override
    public int updateAction(BoardUpdateMapping vo){

        int result = boardRepo.save(vo);
        return 1;
    }
}
