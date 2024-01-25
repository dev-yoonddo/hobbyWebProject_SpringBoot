package com.toogether.service;

import com.toogether.dto.BoardUpdateDTO;
import com.toogether.vo.BoardVO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public interface BoardService extends FileService {
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
    int getBoardIdx();
    int fileDownCount(int boardID);
}
