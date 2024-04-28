package com.example.toy.controller;

import com.example.toy.dto.MemberDTO;
import com.example.toy.entity.MemberEntity;
import com.example.toy.repository.MemberRepository;
import com.example.toy.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    private final Logger LOGGER = LoggerFactory.getLogger(MemberRestController.class);
    public void printLog(String id, String name) {
        LOGGER.info("id: " + id + " name: " + name);
    }

    // [1] 회원 가입
    @PostMapping("/api/user/join")
    public ResponseEntity<String> join(@ModelAttribute MemberDTO memberDTO) {
        // service 계층에서 save 메소드로 저장 (DTO -> Entity 변환)

        //로그 찍기
        // printLog(memberDTO.getMemberID(), memberDTO.getMemberName());

        int isIdDuplicate = memberService.checkIdDuplicate(memberDTO);
        int isEmailDuplicate = memberService.checkEmailDuplicate(memberDTO);

        // 입력한 아이디가 중복되는지 확인 (개수로 판단)
        if (isIdDuplicate >= 1 && isEmailDuplicate >= 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디, 이메일입니다. <a href=\"/\">홈으로 돌아가기</a>");
        }
        else if (isIdDuplicate < 1 && isEmailDuplicate >= 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 이메일입니다. <a href=\"/\">홈으로 돌아가기</a>");
        } else if (isIdDuplicate >= 1 && isEmailDuplicate < 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 아이디입니다. <a href=\"/\">홈으로 돌아가기</a>");
        } else {
            memberService.save(memberDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다. <a href=\"/\">홈으로 돌아가기</a>");
        }
    }

    // [2] 회원 목록 조회
    @GetMapping("/api/user/list")
    public Page<MemberEntity> getMemberList(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        // sort: 가입일 순, 이름순
        // id (PK, Auto Increment)를 내림차순으로 정렬하면 가입일 최신 순으로 정렬
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "id").and(Sort.by(Sort.Direction.DESC, "name")));
        return memberService.getMemberPagination(pageable);
    }


    // [3] 회원 정보 수정 put, post
    @PutMapping("/api/user")
    public ResponseEntity<String> updateUserInfoPut(@RequestParam("id") String id, MemberDTO memberDTO) {
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
            return ResponseEntity.status(HttpStatus.OK).body(response + "<br>" + "<a href=\"/\">홈으로 돌아가기</a>");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("비밀번호가 일치하지 않습니다. <a href=\"/\">홈으로 돌아가기</a>");
        }
    }

    @PostMapping("api/user")
    public ResponseEntity<String> updateUserInfoPost(@RequestParam("id") String id, MemberDTO memberDTO) {
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
            return ResponseEntity.status(HttpStatus.OK).body(response + "<br>" + "<a href=\"/\">홈으로 돌아가기</a>");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("비밀번호가 일치하지 않습니다. <a href=\"/\">홈으로 돌아가기</a>");
        }

    }



}
