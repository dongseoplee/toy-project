package com.example.toy.service;

import com.example.toy.dto.MemberDTO;
import com.example.toy.entity.MemberEntity;
import com.example.toy.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

}
