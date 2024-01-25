package com.toogether.dto;

import lombok.Data;

@Data
public class BoardUpdateDTO {
    private int boardID;
    private String boardTitle;
    private String boardContent;
    private String boardCategory;
    private String filename;
    private String fileRealname;
    private int fileDownCount;
}
