package com.example.toy.controller;

import com.example.toy.dto.MemberDTO;
import com.example.toy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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


    // [2] 회원 목록 조회
    @GetMapping("/member/search")
    public String searchPage() {
        return "search";
    }

    // [3] 회원 정보 수정
    @GetMapping("/member/edit")
    public String editPage(@RequestParam("id") String memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "edit";
    }




}
