package com.mbti.MbtiProJect.Controller;

import com.mbti.MbtiProJect.entity.Mbtilist;
import com.mbti.MbtiProJect.entity.Member;
import com.mbti.MbtiProJect.service.MbtiService;
import com.mbti.MbtiProJect.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MbtiController {
    @Autowired
    private MbtiService mbtiService;
    @Autowired
    private MemberService memberService;
    @GetMapping("/mbti/mainpage")
    public String mainPage(HttpSession httpSession,Model model){
        String userSession=null;

        if (httpSession.getAttribute("memberid")!= null){
            userSession=(String) httpSession.getAttribute("memberid");
        }
        model.addAttribute("userSession",userSession);
        return "mainPage";
    }

    @GetMapping("/mbti/secondmainpage")
    public String secondmainPage(Model model, @PageableDefault(page = 1,size = 1,sort = "id",direction= Sort.Direction.ASC ) Pageable pageable ,String value,HttpSession httpSession) throws Exception {
        String userSession=null;
        int pagenum= pageable.getPageNumber();

        if (httpSession.getAttribute("memberid")!= null){
            userSession=(String) httpSession.getAttribute("memberid");
        }
        System.out.println("메인페이지에서:"+userSession);
        model.addAttribute("userSession",userSession);

        Mbtilist mbtiValue=mbtiService.mbtiview(pageable.getPageNumber());
        model.addAttribute("list",mbtiService.mbtilists(pageable));
        System.out.println(pagenum);
        //이부분으로 순서대로 값 받기 if00<4 and
        System.out.println(mbtiValue.getId());
        //처음값이 넒값이 나오므로 1로 처리


        ArrayList<String> mbtiValueArray=new ArrayList<String>();

        if(value==null){
            value="1";
        }
        // I랑 E부분 처리
        else if (value.equals("1")&&mbtiValue.getId()<=6){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("E");
            mbtiValueArray.add("E");
        }
        else if(value.equals("2")&&mbtiValue.getId()<=6){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("I");
            mbtiValueArray.add("I");
        }
        //S랑 N값 처리
        else if (value.equals("1")&&mbtiValue.getId()<=11&&mbtiValue.getId()>6){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("N");
            mbtiValueArray.add("N");
        }
        else if(value.equals("2")&&mbtiValue.getId()<=11&&mbtiValue.getId()>6){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("S");
            mbtiValueArray.add("S");
        }
        //T랑 F값 처리
        else if (value.equals("1")&&mbtiValue.getId()<=16&&mbtiValue.getId()>11){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("F");
            mbtiValueArray.add("F");
        }
        else if(value.equals("2")&&mbtiValue.getId()<=16&&mbtiValue.getId()>11){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("T");
            mbtiValueArray.add("T");
        }
        else if (value.equals("1")&&mbtiValue.getId()<=21&&mbtiValue.getId()>16){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("J");
            mbtiValueArray.add("J");
        }
        else if(value.equals("2")&&mbtiValue.getId()<=21 && mbtiValue.getId()>16){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("P");
            mbtiValueArray.add("P");
        } else{
            return "redirect:/mbti/resultpage";
        }
        mbtiService.MbtiResultadd(mbtiValue);
        return "secondMainPage";
    }
}