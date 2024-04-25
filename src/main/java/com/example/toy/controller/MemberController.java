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
        int isIdDuplicate = memberService.checkIdDuplicate(memberDTO);
        int isEmailDuplicate = memberService.checkEmailDuplicate(memberDTO);

        // 입력한 아이디가 있다면 (개수로 판단)
        if (isIdDuplicate >= 1 && isEmailDuplicate >= 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디, 이메일입니다.");
        }
        else if (isIdDuplicate < 1 && isEmailDuplicate >= 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 이메일입니다.");
        } else if (isIdDuplicate >= 1 && isEmailDuplicate < 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다.");
        } else {
            memberService.save(memberDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다.");
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
