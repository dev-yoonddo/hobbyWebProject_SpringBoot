package com.toogether.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능
@Table(name="file")
public class FileVO {

    @Id
    @Column
    int boardID;

    @Column
    private String fileName;

    @Column
    private String fileRealName;

    public FileVO(){}
    public FileVO(int boardID, String fileName, String fileRealName){
        this.boardID = boardID;
        this.fileName = fileName;
        this.fileRealName = fileRealName;
    }


}
