package com.toogether.controller;

import com.toogether.dto.BoardUpdateDTO;
import com.toogether.mapper.BoardUpdateMapping;
import com.toogether.service.BoardService;
import com.toogether.vo.BoardVO;
import com.toogether.vo.HeartVO;
import com.toogether.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
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
    public String search(@PathVariable("category") String category,
                         Model model) {
        System.out.println("컨트롤러의 search() 메소드");
        List<BoardVO> bdlist = boardService.getBoardList(category);
        System.out.println("검색 결과: " + bdlist);
        model.addAttribute("category", category);
        model.addAttribute("bdlist", bdlist);
        return "search";
    }

    //1개 글 보기
    @GetMapping("/post/{id}")
    public String view(@PathVariable("id") int boardID,
                       Model model,
                       HttpSession session){
        String exist = "N";
        //삭제 여부 확인
        if(boardService.deleteCheck(boardID) == 0) {
            model.addAttribute("avlb", "0");
            return "view";
        }

        //하트 클릭 여부 확인 : 비로그인시 무조건 하트 클릭하지 않은걸로 처리
        String userID = (String) session.getAttribute("userID");
        if (userID != null && boardService.heartCheck(boardID, userID).equals("Y")) {
            exist = "Y";
        }
        //조회수 증가
        boardService.viewCount(boardID);
        //한 개의 글 가져오기
        BoardVO vo = boardService.getBoardVO(boardID);
        System.out.println("글 보기: " + vo);
        System.out.println("하트여부: " + exist);
        model.addAttribute("exist", exist);
        model.addAttribute("vo", vo);
        return "view";
    }
    //글 작성 페이지
    @GetMapping("/newpost")
    public String write(@RequestParam("category") String category, Model model){
        System.out.println("글 작성 페이지");
        System.out.println(category);
        model.addAttribute("category",category);
        return "write";
    }
    //글 작성 실행
    @PostMapping("/newpost")
    public String writeAction(BoardVO vo, Model model){
        System.out.println("글 작성 실행: " + vo);
        BoardVO result = boardService.writeAction(vo);
        if(result != null) {
            return "redirect:/post/" + result.getBoardID();
        }
        model.addAttribute("vo", vo); //글 작성 실패시 입력했던 값 return
        return "/newpost";
    }
    //글 수정 페이지
    @GetMapping("/newpost/{id}")
    public String update(@PathVariable("id") int boardID, Model model){
        BoardVO vo = boardService.getBoardVO(boardID);
        System.out.println("글 수정: " + vo);
        model.addAttribute("vo",vo);
        return "update";
    }

    //글 수정 실행
    @PostMapping("/newpost/{id}")
    public String updateAction(@PathVariable("id")int boardID,
                               BoardUpdateDTO vo,
                               Model model) throws UnsupportedEncodingException {
        System.out.println("글 수정 실행: " + vo);
        Optional<BoardVO> updatevo = boardService.updateAction(vo);
        //int result = boardService.updateAction(vo);
        System.out.println("글 수정 결과: " + updatevo);
         if(updatevo.isPresent()){
             /*/한글 깨짐
             String title = updatevo.get().getBoardTitle();
             title = URLEncoder.encode(title,"UTF-8");
             */
             model.addAttribute("vo",vo);
            return "redirect:/community/post/"+boardID;
        }
        return "/newpost/"+boardID;
    }
    //글 삭제
    @GetMapping("/delpost/{id}")
    public String deleteAction(@PathVariable("id") int id,
                               @ModelAttribute("category") String category){
        boardService.deleteAction(id);
        return "/community/"+category.toLowerCase();
    }
    //하트 클릭 또는 취소
    @PostMapping("/post/heart")
    public void heartAction(  @RequestParam("boardID") int boardID,
                              @RequestParam("userID") String userID,
                              @RequestParam("value") String value,
                              HttpServletResponse response) throws IOException {
        System.out.println("하트실행:" + boardID + userID + value);
        PrintWriter script = response.getWriter();
        int result = boardService.heartAction(boardID, userID, value);
        System.out.println("하트실행결과:" + result);
        if(result > 0){
            script.print("success");
            script.close();
        }else{
            script.print("error");
            script.close();
        }
    }
}
