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
    public String secondmainPage(Model model, @PageableDefault(page = 1,size = 1,sort = "id",direction= Sort.Direction.ASC ) Pageable pageable ,String value) {
        Mbtilist mbtiValue=mbtiService.mbtiview(pageable.getPageNumber());
        model.addAttribute("list",mbtiService.mbtilists(pageable));
        System.out.println(value);
        //이부분으로 순서대로 값 받기 if00<4 and
        System.out.println(mbtiValue.getId());
        //처음값이 넒값이 나오므로 1로 처리
        if(value==null){
            value="1";
        }
        else if (value.equals("1")){
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
