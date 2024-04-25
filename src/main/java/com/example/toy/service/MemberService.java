package com.example.toy.service;

import com.example.toy.dto.MemberDTO;
import com.example.toy.entity.MemberEntity;
import com.example.toy.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberEntity save(MemberDTO memberDTO) {
        // DTO -> Entitiy 변환
        MemberEntity memberEntity = MemberEntity.convertToEntity(memberDTO);
        return memberRepository.save(memberEntity);
    }

    public Page<MemberEntity> getMemberPagination(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    public MemberEntity getMemberById(String memberId) {
        return memberRepository.findAllByMemberId(memberId);
    }

    public MemberEntity updateMemberInfo(String memberId, MemberDTO memberDTO) {

        Optional<MemberEntity> optionalMemberEntity = Optional.ofNullable(memberRepository.findAllByMemberId(memberId));

        MemberEntity memberEntity = optionalMemberEntity.get();
        memberEntity.setNickname(memberDTO.getMemberNickname());
        memberEntity.setName(memberDTO.getMemberName());
        memberEntity.setPhone_number(memberDTO.getMemberPhoneNumber());
        memberEntity.setEmail(memberDTO.getMemberEmail());
        return memberRepository.save(memberEntity);

    }

    public int checkIdDuplicate(MemberDTO memberDTO) {
        return memberRepository.existsByMemberId(memberDTO.getMemberID());
    }

    public int checkEmailDuplicate(MemberDTO memberDTO) {
        return memberRepository.existsByEmailId(memberDTO.getMemberEmail());
    }

    public boolean isNotNull(MemberDTO memberDTO) {

        if (memberDTO.getMemberID() == null || memberDTO.getMemberPassword() == null || memberDTO.getMemberNickname() == null || memberDTO.getMemberName() == "" || memberDTO.getMemberPhoneNumber() == null || memberDTO.getMemberEmail() == null) {
            // 1개라도 null인 경우
            return false;
        } else {
            return true;
        }

    }

}
