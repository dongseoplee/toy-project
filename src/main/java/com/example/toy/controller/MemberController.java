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

    @PostMapping("/api/user/join")
    public ResponseEntity<String> join(@ModelAttribute MemberDTO memberDTO) {
        // service 계층에서 save 메소드로 저장 (DTO -> Entity 변환)

        // 같은 아이디가 있을 경우 오류 return
        boolean duplicateId = false;
        duplicateId = memberService.isDuplicate(memberDTO);
        if (duplicateId) {
            // 같이 아이디 있는 경우
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Id");
        } else {
            memberService.save(memberDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("join!");
        }


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
