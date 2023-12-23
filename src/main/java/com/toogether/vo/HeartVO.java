package com.toogether.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="heart")
public class HeartVO {

    @Id
    private int boardID;
    private String userID;


    public HeartVO() {
    }

    public HeartVO(int boardID, String userID) {
        this.boardID = boardID;
        this.userID = userID;
    }
}
