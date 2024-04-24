package com.example.toy.repository;

import com.example.toy.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // findAllByMemberId
    @Query(value = "SELECT * FROM toy_member WHERE MEMBER_ID = :memberId", nativeQuery = true)
    public MemberEntity findAllByMemberId(@Param("memberId") String memberId);
}
