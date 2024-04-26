package com.example.toy.controller;

import com.example.toy.dto.MemberDTO;
import com.example.toy.entity.MemberEntity;
import com.example.toy.repository.MemberRepository;
import com.example.toy.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberRestController.class)
public class MemberRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @MockBean
    MemberRepository memberRepository;


    @Test
    @DisplayName("회원 가입 성공 테스트")
    public void testJoin_Success() throws Exception {
        // Given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberID("test_member");
        memberDTO.setMemberPassword("12341234");
        memberDTO.setMemberPhoneNumber("010-1234-1234");
        memberDTO.setMemberNickname("nickname");
        memberDTO.setMemberName("test_name");
        memberDTO.setMemberEmail("test@example.com");

        String json = new ObjectMapper().writeValueAsString(memberDTO);

        mockMvc
                .perform(post("/api/user/join").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("회원가입이 완료되었습니다."))
                .andDo(print());


    }

    @Test
    @DisplayName("회원 가입 아이디 중복 테스트")
    public void testJoin_IdDuplicate() throws Exception {
        // Given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberID("test_member");
        memberDTO.setMemberPassword("12341234");
        memberDTO.setMemberPhoneNumber("010-1234-1234");
        memberDTO.setMemberNickname("nickname");
        memberDTO.setMemberName("test_name");
        memberDTO.setMemberEmail("test@example.com");

        String json = new ObjectMapper().writeValueAsString(memberDTO);

        when(memberService.checkIdDuplicate(any(MemberDTO.class))).thenReturn(1);
        when(memberService.checkEmailDuplicate(any(MemberDTO.class))).thenReturn(0);

        // When/Then
        mockMvc
                .perform(post("/api/user/join").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string("이미 존재하는 아이디입니다."))
                .andDo(print());
    }
    @Test
    @DisplayName("회원 가입 이메일 중복 테스트")
    public void testJoin_EmailDuplicate() throws Exception {
        // Given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberID("test_member");
        memberDTO.setMemberPassword("12341234");
        memberDTO.setMemberPhoneNumber("010-1234-1234");
        memberDTO.setMemberNickname("nickname");
        memberDTO.setMemberName("test_name");
        memberDTO.setMemberEmail("test@example.com");

        String json = new ObjectMapper().writeValueAsString(memberDTO);

        when(memberService.checkIdDuplicate(any(MemberDTO.class))).thenReturn(0);
        when(memberService.checkEmailDuplicate(any(MemberDTO.class))).thenReturn(1);

        // When/Then
        mockMvc
                .perform(post("/api/user/join").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string("이미 존재하는 이메일입니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("회원 가입 아이디 중복, 이메일 중복 테스트")
    public void testJoin_IdAndEmailDuplicate() throws Exception {
        // Given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberID("test_member");
        memberDTO.setMemberPassword("12341234");
        memberDTO.setMemberPhoneNumber("010-1234-1234");
        memberDTO.setMemberNickname("nickname");
        memberDTO.setMemberName("test_name");
        memberDTO.setMemberEmail("test@example.com");

        String json = new ObjectMapper().writeValueAsString(memberDTO);

        when(memberService.checkIdDuplicate(any(MemberDTO.class))).thenReturn(1);
        when(memberService.checkEmailDuplicate(any(MemberDTO.class))).thenReturn(1);

        // When/Then
        mockMvc
                .perform(post("/api/user/join").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andExpect(content().string("이미 존재하는 아이디, 이메일입니다."))
                .andDo(print());
    }

    @Test
    @DisplayName("회원 목록 조회 테스트")
    public void testGetMemberList() throws Exception {
        List<MemberEntity> memberEntities = new ArrayList<>();

        Page<MemberEntity> page = new PageImpl<>(memberEntities);

        when(memberService.getMemberPagination(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/user/list"))
                // Then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }


}
