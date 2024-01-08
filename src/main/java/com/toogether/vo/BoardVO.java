package com.toogether.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="board")
public class BoardVO {

    @Id
    private int boardID;
    private String boardTitle;
    private String userID;
    private String boardDate;
    private String boardContent;
    private int boardAvailable;
    private String boardCategory;
    private int viewCount;
    private int heartCount;
    private String filename;
    private String fileRealname;
    private int fileDownCount;


    public BoardVO() {
    }
    public BoardVO(int boardID, String boardTitle, String userID, String boardDate, String boardContent,
                   String boardCategory, String filename, String fileRealname) {
        this.boardID = boardID;
        this.boardTitle = boardTitle;
        this.userID = userID;
        this.boardDate = boardDate;
        this.boardContent = boardContent;
        this.boardCategory = boardCategory;
        this.filename = filename;
        this.fileRealname = fileRealname;
    }


    @Override
    public String toString() {
        return "BoardVO [boardID=" + boardID + ", boardTitle=" + boardTitle + ", userID=" + userID + ", boardDate="
                + boardDate + ", boardContent=" + boardContent + ", boardAvailable=" + boardAvailable + ", boardCategory="
                + boardCategory + ", viewCount=" + viewCount + ", heartCount=" + heartCount + ", filename=" + filename
                + ", fileRealname=" + fileRealname + ", fileDownCount=" + fileDownCount + "]";
    }
}
