package com.example.toy.controller;

import com.example.toy.dto.MemberDTO;
import com.example.toy.entity.MemberEntity;
import com.example.toy.repository.MemberRepository;
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

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    // [1] 회원 가입
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



    // [3] 회원 정보 수정 put, post
    @PutMapping("/api/user")
    public ResponseEntity<String> updateUserInfo(@RequestParam("id") String id, MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = Optional.ofNullable(memberRepository.findAllByMemberId(id));
        MemberEntity memberEntity = optionalMemberEntity.get();
        //비밀번호가 일치해야 회원정보 수정 가능
        if (memberEntity.getPassword().equals(memberDTO.getMemberPassword())) {
            memberService.updateMemberInfo(id, memberDTO);
            String response = "아래와 같이 회원 정보가 수정되었습니다." + "<br>" + "<br>" +
                    "Nickname: " + memberDTO.getMemberNickname() + "<br>" +
                    "Name: " + memberDTO.getMemberName() + "<br>" +
                    "Phone Number: " + memberDTO.getMemberPhoneNumber() + "<br>" +
                    "Email: " + memberDTO.getMemberEmail();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("비밀번호가 일치하지 않습니다.");
        }
    }

    @PostMapping("api/user")
    public ResponseEntity<String> updateUserInfo2(@RequestParam("id") String id, MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = Optional.ofNullable(memberRepository.findAllByMemberId(id));
        MemberEntity memberEntity = optionalMemberEntity.get();
        //비밀번호가 일치해야 회원정보 수정 가능
        if (memberEntity.getPassword().equals(memberDTO.getMemberPassword())) {
            memberService.updateMemberInfo(id, memberDTO);
            String response = "아래와 같이 회원 정보가 수정되었습니다." + "<br>" + "<br>" +
                    "Nickname: " + memberDTO.getMemberNickname() + "<br>" +
                    "Name: " + memberDTO.getMemberName() + "<br>" +
                    "Phone Number: " + memberDTO.getMemberPhoneNumber() + "<br>" +
                    "Email: " + memberDTO.getMemberEmail();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("비밀번호가 일치하지 않습니다.");
        }

    }

}
