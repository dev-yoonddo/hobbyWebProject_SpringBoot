package com.toogether.service;

import com.toogether.vo.BoardVO;
import com.toogether.vo.FileVO;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public interface FileService {
    BoardVO fileupload(MultipartFile file) throws IOException;
    void filesave(FileVO vo);
    int fileDownload(int boardID, HttpServletRequest request, HttpServletResponse response);
}
