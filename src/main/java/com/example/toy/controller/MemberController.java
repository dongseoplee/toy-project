package com.example.toy.controller;

import com.example.toy.dto.MemberDTO;
import com.example.toy.entity.MemberEntity;
import com.example.toy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.print.Pageable;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // [1] 회원 가입
    @GetMapping("/join")
    public String joinPage() {
        // 회원 가입 페이지
        return "join";
    }
    @PostMapping("/api/user/join")
    public ResponseEntity<String> join(@ModelAttribute MemberDTO memberDTO) {
        // service 계층에서 save 메소드로 저장 (DTO -> Entity 변환)
        memberService.save(memberDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("join!");
    }

    // [2] 회원 목록 조회
//    @GetMapping("/api/user/list")
//    public Page<MemberEntity> getMemberList(Pageable pageable) {
//
//    }

    // [3] 회원 정보 수정

}
