package com.toogether.vo;

import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data //getter, setter를 선언하지 않고 사용할 수 있게 해주는 lombok 기능
@Table(name="user")
public class UserVO {

    @Id
    @Column(name = "userID")
    private String userID;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "userBirth")
    private String userBirth;

    @Column(name = "userPhone")
    private String userPhone;

    @Column(name = "userPassword")
    private String userPassword;

    @Column(name = "userAvailable")
    private int userAvailable;

    @Column(name = "userEmailHash")
    private String userEmailHash;

    @Column(name = "userEmailChecked")
    private int userEmailChecked;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public UserVO() {}

    public UserVO(String userID, String userPassword){
        this.userID = userID;
        this.userPassword = userPassword;
    }

    @Builder
    public UserVO(String userID, String userName, String userEmail, String userBirth, String userPhone,String userPassword, Role role) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userBirth = userBirth;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.role = role;
    }
    public UserVO update(String name, String picture) {
        this.userID = userID;
        this.userEmail = userEmail;

        return this;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }
    @Override
    public String toString() {
        return "UserVO [userID=" + userID + ", userName=" + userName + ", userEmail=" + userEmail + ", userBirth="
                + userBirth + ", userPhone=" + userPhone + ", userPassword=" + userPassword + ", userAvailable="
                + userAvailable + ", userEmailHash=" + userEmailHash + ", userEmailChecked=" + userEmailChecked + "]";
    }
}
