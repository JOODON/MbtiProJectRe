package com.mbti.MbtiProJect.service;

import com.mbti.MbtiProJect.entity.Member;
import com.mbti.MbtiProJect.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void singup(Member member){
        memberRepository.save(member);
    }
}
