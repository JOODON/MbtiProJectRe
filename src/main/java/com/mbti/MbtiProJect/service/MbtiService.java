package com.mbti.MbtiProJect.service;

import com.mbti.MbtiProJect.entity.Mbtilist;
import com.mbti.MbtiProJect.repository.MbtiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MbtiService {
    @Autowired
    private MbtiRepository mbtiRepository;
    public void MbtiResultadd(Mbtilist mbtilist)throws Exception{
        mbtiRepository.save(mbtilist);
    }
    public Mbtilist mbtiview(Integer id){
        return mbtiRepository.findById(id).get();
    }
    public Page<Mbtilist> mbtilists(Pageable pageable){
        return mbtiRepository.findAll(pageable);
    }

    public Long mbtivalue(String Keyword){
        return mbtiRepository.countBymbtitestvalue(Keyword);
    }
    //레디스 카프카 인프라적인 장기기능 aws Github jenkins (docker)
}
