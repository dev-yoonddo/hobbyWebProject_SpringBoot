package com.toogether.controller;

import com.toogether.mapper.BoardUpdateMapping;
import com.toogether.service.BoardService;
import com.toogether.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    @GetMapping("/{category}/{id}")
    public String view(@PathVariable("category") String category, @PathVariable("id") int boardID, Model model){

        BoardVO vo = boardService.getBoardVO(boardID);
        System.out.println("글 보기: " + vo);
        model.addAttribute("vo",vo);
        return "view";
    }

    //글 수정 페이지
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int boardID, Model model){
        BoardVO vo = boardService.getBoardVO(boardID);
        System.out.println("글 수정: " + vo);
        model.addAttribute("vo",vo);
        return "update";
    }
    //글 수정 실행
    @PostMapping("/update/{id}")
    public String updateAction(@PathVariable("id") int boardID, BoardUpdateMapping vo, Model model){
        System.out.println("글 수정: " + vo);
        int result = boardService.updateAction(vo);
        if(result > 0 ){
            model.addAttribute("vo",vo);
            return "redirect:/community/"+vo.getBoardCategory().toLowerCase()+"/"+boardID;
        }
        return "/update/"+boardID;
    }
}
