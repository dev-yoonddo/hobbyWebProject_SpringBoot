package com.toogether.controller;

import com.toogether.dto.BoardUpdateDTO;
import com.toogether.mapper.BoardUpdateMapping;
import com.toogether.service.BoardService;
import com.toogether.vo.BoardVO;
import com.toogether.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/community")
public class BoardController {
    @Autowired
    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    //카테고리 글 리스트 보기
    @GetMapping("/{category}")
    public String search(@PathVariable("category") String category, Model model) {
        System.out.println("컨트롤러의 search() 메소드");
        List<BoardVO> bdlist = boardService.getBoardList(category);
        System.out.println("검색 결과: " + bdlist);
        model.addAttribute("category", category);
        model.addAttribute("bdlist", bdlist);
        return "search";
    }

    //1개 글 보기
    @GetMapping("/{category}/{title}/{id}")
    public String view(@PathVariable("category") String category,
                       @PathVariable("title") String boardTitle,
                       @PathVariable("id") int boardID,
                       Model model){

        BoardVO vo = boardService.getBoardVO(boardID);
        System.out.println("글 보기: " + vo);
        model.addAttribute("vo",vo);
        return "view";
    }

    //글 수정 페이지
    @GetMapping("/{title}/{id}")
    public String update(@PathVariable("title") String boardTitle,@PathVariable("id") int boardID, Model model){
        BoardVO vo = boardService.getBoardVO(boardID);
        System.out.println("글 수정: " + vo);
        model.addAttribute("vo",vo);
        return "update";
    }

    //글 수정 실행
    @PostMapping("/{title}/{id}")
    public String updateAction(@PathVariable("id")int boardID,
                               BoardUpdateDTO vo,
                               Model model) throws UnsupportedEncodingException {
        System.out.println("글 수정 실행: " + vo);
        Optional<BoardVO> updatevo = boardService.updateAction(vo);
        //int result = boardService.updateAction(vo);
        System.out.println("글 수정 결과: " + updatevo);
         if(updatevo.isPresent()){
             //한글 깨짐
             String title = updatevo.get().getBoardTitle();
             title = URLEncoder.encode(title,"UTF-8");

             model.addAttribute("vo",vo);
            return "redirect:/community/"+vo.getBoardCategory().toLowerCase()+"/"+title+"/"+boardID;
        }
        return "/"+vo.getBoardTitle()+"/"+boardID;
    }
}
