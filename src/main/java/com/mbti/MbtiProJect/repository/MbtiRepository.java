package com.mbti.MbtiProJect.repository;

import com.mbti.MbtiProJect.entity.Mbtilist;
import com.mbti.MbtiProJect.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MbtiRepository extends JpaRepository<Mbtilist,Integer> {
    Long countBymbtitestvalue(String value);

}