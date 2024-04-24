package com.example.toy.controller;

import com.example.toy.dto.MemberDTO;
import com.example.toy.entity.MemberEntity;
import com.example.toy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    // [2] 회원 목록 조회
    @GetMapping("/api/user/list")
    public Page<MemberEntity> getMemberList(@PageableDefault(page = 0, size = 20) @SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.DESC), @SortDefault(sort = "name", direction = Sort.Direction.DESC)
    }) Pageable pageable) {
        // sort: 가입일 순, 이름순
        // id (PK, Auto Increment)를 내림차순으로 정렬하면 가입일 최신 순으로 정렬
        return memberService.getMemberPagination(pageable);
    }

    @GetMapping("/api/user")
    public MemberEntity userInfo(@RequestParam("id") String memberId) {
        // memberId 의 정보
        return memberService.getMemberById(memberId);
    }

    @PutMapping("/api/user")
    public ResponseEntity<String> updateUserInfo(@RequestParam("id") String id, MemberDTO memberDTO) {
        memberService.updateMemberInfo(id, memberDTO);
        System.out.println(memberDTO.getMemberID());
        System.out.println(memberDTO.getMemberPassword());
        System.out.println(memberDTO.getMemberName());
        System.out.println(memberDTO.getMemberNickname());
        System.out.println(memberDTO.getMemberPhoneNumber());
        System.out.println(memberDTO.getMemberEmail());
        return ResponseEntity.status(HttpStatus.OK).body("Update Success!");

    }
}
