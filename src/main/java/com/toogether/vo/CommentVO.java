package com.toogether.vo;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name="comment")
public class CommentVO {

    @Id
    private int cmtID;
    private int boardID;
    private String userID;
    private String cmtContent;
    private int cmtAvailable;
    private String cmtDate;

    public CommentVO() {}
    public CommentVO(int boardID, String userID, String cmtContent) {
        this.boardID = boardID;
        this.userID = userID;
        this.cmtContent = cmtContent;
    }
    @Override
    public String toString() {
        return "CommentVO [cmtID=" + cmtID + ", boardID=" + boardID + ", userID=" + userID + ", cmtContent="
                + cmtContent + ", cmtAvailable=" + cmtAvailable + ", cmtDate=" + cmtDate + "]";
    }
}
