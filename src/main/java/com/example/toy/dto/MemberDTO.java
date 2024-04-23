package com.example.toy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    // 회원id, 비밀번호, 닉네임, 이름, 전화번호, 이메일주소
    private String memberID;
    private String memberPassword;
    private String memberNickname;
    private String memberName;
    private String memberPhoneNumber;
    private String memberEmail;

}
