package com.toogether.vo;

import com.querydsl.core.types.dsl.EntityPathBase;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Data
@Table(name="board")
public class BoardVO implements Comparable<BoardVO>{

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

    public BoardVO(String filename, String fileRealname) {
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

    // boardlist 내림차순 정렬을 위해 implement Comparable<BoardVO> interface 를 implement하고
    // compareTo() 메서드를 Override 한다.
    // 이 외에 새로운 클래스를 만들어 Comparator<BoardVO> interface를 implement하고
    //         Collections.sort(list, new 생성한클래스().reversed()); 를 사용해도 된다. 이 방법은 정렬 값을 여러개 사용할 때 유용하다.
    @Override
    public int compareTo(BoardVO vo){
        if(vo.boardID < boardID){
            return 1;
        }else if(vo.boardID > boardID){
            return -1;
        }
        return 0;
    }
    @Override
    public String toString() {
        return "BoardVO [boardID=" + boardID + ", boardTitle=" + boardTitle + ", userID=" + userID + ", boardDate="
                + boardDate + ", boardContent=" + boardContent + ", boardAvailable=" + boardAvailable + ", boardCategory="
                + boardCategory + ", viewCount=" + viewCount + ", heartCount=" + heartCount + ", filename=" + filename
                + ", fileRealname=" + fileRealname + ", fileDownCount=" + fileDownCount + "]";
    }
}
