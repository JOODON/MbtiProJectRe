package com.mbti.MbtiProJect.Controller;

import com.mbti.MbtiProJect.entity.Mbtilist;
import com.mbti.MbtiProJect.service.MbtiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MbtiController {
    @Autowired
    private MbtiService mbtiService;
    @GetMapping("/mbti/mainpage")
    public String mainPage(){
        return "mainPage";
    }

    @GetMapping("/mbti/secondmainpage")
    public String secondmainPage(Model model, @PageableDefault(page = 1,size = 1,sort = "id",direction= Sort.Direction.ASC ) Pageable pageable,String mbtivalue) {
        Mbtilist mbtiValue=mbtiService.mbtiview(pageable.getPageNumber());
        int mbtiValueint=0;

        model.addAttribute("list",mbtiService.mbtilists(pageable));
        model.addAttribute("mbtiValueint",mbtiValueint);

        if (mbtiValue.equals("E")) {
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("E");
        }else {
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("I");
        }
        System.out.println(mbtiValue);



        return "secondMainPage";
    }
}
