package com.mbti.MbtiProJect.repository;

import com.mbti.MbtiProJect.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    List<Member> findBymemberid(String name);
}
