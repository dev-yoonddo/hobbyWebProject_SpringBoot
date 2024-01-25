package com.toogether.controller;

import com.toogether.dto.BoardUpdateDTO;
import com.toogether.service.BoardService;
import com.toogether.vo.BoardVO;
import com.toogether.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
        //이미 삭제된 글이면 avlb = 0 을 view에 넘겨주기 (view.jsp 페이지에서 avlb = 0이면 접근불가 처리)
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
        model.addAttribute("exist", exist); // 하트 클릭 여부 전달
        model.addAttribute("vo", vo); // 한 개의 글 내용 전달
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
    public String writeAction(BoardVO vo,
                              Model model,
                              @RequestParam MultipartFile file) throws IOException {
        System.out.println("글 작성 실행: " + vo);
        System.out.println("파일: " + file);
        if(!file.isEmpty()) { //첨부파일 존재시 파일 업로드 실행 후 파일 이름, 파일 유니크 이름 가져오기
            BoardVO fileupload = boardService.fileupload(file);
            vo.setFilename(fileupload.getFilename());
            vo.setFileRealname(fileupload.getFileRealname());
        }
        BoardVO result = boardService.writeAction(vo); //board 테이블에 저장 (boardID 생성)
        //file 테이블에 저장
        FileVO filevo = new FileVO(vo.getBoardID(),vo.getFilename(),vo.getFileRealname());
        boardService.filesave(filevo);

        //글 작성 완료시 해당 글 보기로 이동
        if(result != null) {
            return "redirect:/community/post/" + result.getBoardID();
        }
        //글 작성 실패시 입력했던 값 return
        model.addAttribute("vo", vo);
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

    //파일 다운로드
    @RequestMapping("/download/{id}")
    public void fileDownload(@PathVariable("id") int boardID, HttpServletRequest request, HttpServletResponse response){
        int result = boardService.fileDownload(boardID, request, response);
        if(result == 0){
            System.out.println("다운로드실패");
        }else{
            System.out.println("다운로드성공");
            boardService.fileDownCount(boardID); //다운로드 횟수 + 1
        }
    }
}
