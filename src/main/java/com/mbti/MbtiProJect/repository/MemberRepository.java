package com.mbti.MbtiProJect.repository;

import com.mbti.MbtiProJect.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {

}
