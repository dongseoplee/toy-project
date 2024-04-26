package com.example.toy.entity;

import com.example.toy.dto.MemberDTO;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "toy_member") // 테이블 이름 대소문자 구분
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String member_id;
    private String password;
    private String nickname;
    private String name;
    private String phone_number;
    private String email;



    public static MemberEntity convertToEntity (MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMember_id(memberDTO.getMemberID());
        memberEntity.setPassword(memberDTO.getMemberPassword());
        memberEntity.setNickname(memberDTO.getMemberNickname());
        memberEntity.setName(memberDTO.getMemberName());
        memberEntity.setPhone_number(memberDTO.getMemberPhoneNumber());
        memberEntity.setEmail(memberDTO.getMemberEmail());

        return memberEntity;
    }


}