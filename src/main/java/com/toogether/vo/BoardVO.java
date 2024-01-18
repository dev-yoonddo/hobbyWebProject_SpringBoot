package com.toogether.vo;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@DynamicUpdate
@Data
@Table(name="board")
public class BoardVO {

    @Id
    @Column(name = "boardID")
    private int boardID;

    @Column(name = "boardTitle")
    private String boardTitle;

    @Column(name = "userID")
    private String userID;

    @Column(name = "boardDate")
    private String boardDate;

    @Column(name = "boardContent")
    private String boardContent;

    @Column(name = "boardAvailable")
    private int boardAvailable;

    @Column(name = "boardCategory")
    private String boardCategory;

    @Column(name = "viewCount")
    private int viewCount;

    @Column(name = "heartCount")
    private int heartCount;

    @Column(name = "filename")
    private String filename;

    @Column(name = "fileRealname")
    private String fileRealname;

    @Column(name = "fileDownCount")
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

    public void update(int boardID, String boardTitle, String boardContent, String boardCategory, String filename, String fileRealname, int fileDownCount){
        this.boardID = boardID;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardCategory = boardCategory;
        this.filename = filename;
        this.fileRealname = fileRealname;
        this.fileDownCount = fileDownCount;
    }
    @Override
    public String toString() {
        return "BoardVO [boardID=" + boardID + ", boardTitle=" + boardTitle + ", userID=" + userID + ", boardDate="
                + boardDate + ", boardContent=" + boardContent + ", boardAvailable=" + boardAvailable + ", boardCategory="
                + boardCategory + ", viewCount=" + viewCount + ", heartCount=" + heartCount + ", filename=" + filename
                + ", fileRealname=" + fileRealname + ", fileDownCount=" + fileDownCount + "]";
    }
}
