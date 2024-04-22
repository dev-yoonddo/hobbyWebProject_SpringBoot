package com.toogether.vo;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name="heart")
public class HeartVO {

    @Id
    @Column
    private int boardID;
    @Column
    private String userID;


    public HeartVO() {
    }

    public HeartVO(int boardID, String userID) {
        this.boardID = boardID;
        this.userID = userID;
    }
}
