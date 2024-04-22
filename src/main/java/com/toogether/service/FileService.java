package com.toogether.service;

import com.toogether.vo.BoardVO;
import com.toogether.vo.FileVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public interface FileService {
    BoardVO fileupload(MultipartFile file) throws IOException;
    void filesave(FileVO vo);
    int fileDownload(int boardID, HttpServletRequest request, HttpServletResponse response);
}
