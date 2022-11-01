package com.mbti.MbtiProJect.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
//이거해주면 게터세터의 효과를 해줌
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String memberName;
    private String memberid;
    private String memberPassword;
    private String PhoneNumber;
    private String memberEmail;
    private String memberGender;

}
