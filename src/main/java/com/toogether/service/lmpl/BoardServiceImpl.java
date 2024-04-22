package com.toogether.service.lmpl;

import com.toogether.dto.BoardUpdateDTO;
import com.toogether.repo.BoardRepo;
import com.toogether.repo.HeartRepo;
import com.toogether.service.BoardService;
import com.toogether.vo.BoardVO;
import com.toogether.vo.HeartVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BoardServiceImpl extends FileServiceImpl implements BoardService {

    @Autowired
    private final BoardRepo boardRepo;
    private final HeartRepo heartRepo;
    private final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);

    @Value("${file.dir}")
    private String fileDir;

    public BoardServiceImpl(BoardRepo boardRepo, HeartRepo heartRepo) {
        this.boardRepo = boardRepo;
        this.heartRepo = heartRepo;
    }

    @Override
    public List<BoardVO> getBoardList(String category){
        List<BoardVO> allList = boardRepo.findAll();
        List<BoardVO> boardList = new ArrayList<>();
        for(BoardVO i:allList){
            // 검색 카테고리와 동일하고 boardAvailable = 1 (삭제되지 않은) 인 게시글을 새 리스트에 저장하고 반환한다.
            if(category.toUpperCase().equals(i.getBoardCategory()) && i.getBoardAvailable() == 1){
                boardList.add(i);
            }
        }
        log.debug(boardList.toString());
        Collections.sort(boardList, Collections.reverseOrder());
        log.debug("List DESC: " + boardList);
        return boardList;
    }
    @Override
    public void viewCount(int boardID){
        BoardVO vo = boardRepo.getById(boardID);
        boardRepo.findById(boardID).ifPresent( v-> {
            v.setViewCount(vo.getViewCount() + 1);
            boardRepo.save(v);
            log.debug("조회수++" + v);

        });
    }
    @Override
    public BoardVO getBoardVO(int boardID){
        BoardVO vo = boardRepo.getById(boardID);
        return  vo;
    }
    @Override
    public BoardVO writeAction(BoardVO vo) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.debug("글 작성 내용: " + vo);
        int boardID = getBoardIdx();
        vo.setBoardID(boardID + 1); // 새 글 번호
        vo.setBoardDate(date.format(formatter)); // 새 글 작성 시간
        vo.setBoardAvailable(1); // 유효한 값으로 변경

        log.debug("새 글 번호: "+ vo.getBoardID());
        log.debug("새 글 작성시간: "+ vo.getBoardDate());

        return boardRepo.save(vo);
    }
    @Override
    public Optional<BoardVO> updateAction(BoardUpdateDTO vo) {
        Optional<BoardVO> updatevo = boardRepo.findById(vo.getBoardID());
        updatevo.ifPresent( c ->{
            if(vo.getBoardTitle() != null){
                c.setBoardTitle(vo.getBoardTitle());
            }
            if(vo.getBoardContent() != null){
                c.setBoardContent(vo.getBoardContent());
            }
            if(vo.getBoardCategory() != null){
                c.setBoardCategory(vo.getBoardCategory());
            }
            if(vo.getFilename() != null){
                c.setFilename(vo.getFilename());
            }
            boardRepo.save(c);
        });
        System.out.println("========수정완료========");
        return updatevo;
    }
    @Override
    public int deleteCheck(int boardID){
        return boardRepo.getById(boardID).getBoardAvailable();
    }
    @Override
    public void deleteAction(int boardID){
        //boardRepo.deleteById(boardID);  //완전 삭제하기
        //boardAvailable = 0  으로 변경해 보이지 않도록 하기
        boardRepo.findById(boardID).ifPresent( v-> {
            v.setBoardAvailable(0);
            boardRepo.save(v);
            log.debug("삭제: " + v);
        });
    }
    @Override
    public String heartCheck(int boardID, String userID){
        String result = "N";
        //Optional<HeartVO> exist = heartRepo.findById(heartVO.getBoardID());
        if(heartRepo.findByBoardIDAndUserID(boardID,userID) != null){
            result = "Y";
        }
        log.debug("하트 여부: " + result);
        return result;
    }
    @Override
    public int heartAction(int boardID, String userID, String value){
        HeartVO vo = new HeartVO(boardID, userID);
        log.debug("하트: " + vo);
        if(value.equals("none")){
            heartRepo.save(vo);
            //heart 정상적으로 실행시 board의 viewCount + 1
            heartCountAction(boardID,"plus");
            return 1;
        }else if(value.equals("exist")){
            heartRepo.delete(vo);
            //heart 정상적으로 실행시 board의 viewCount - 1
            heartCountAction(boardID,"minus");
            return 1;
        }else{
            return 0;
        }
    }
    @Override
    public int heartCountAction(int boardID, String value){
        BoardVO vo = boardRepo.getById(boardID);
        boardRepo.findById(boardID).ifPresent( v-> {
            if(value.equals("plus")) {
                v.setHeartCount(vo.getHeartCount() + 1);
            }else if(value.equals("minus")){
                v.setHeartCount(vo.getHeartCount() - 1);
            }
            boardRepo.save(v);
            log.debug("하트수: " + v);
        });
        return 1;
    }
    @Override
    public int getBoardIdx(){
        return boardRepo.findByMax();
    }

    @Override
    public int fileDownCount(int boardID){
        BoardVO vo = boardRepo.getById(boardID);
        boardRepo.findById(boardID).ifPresent( v-> {
            v.setFileDownCount(vo.getFileDownCount() + 1);
            boardRepo.save(v);
            log.debug("다운로드횟수++" + v);
        });
        return 1;
    }
}