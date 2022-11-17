package com.mbti.MbtiProJect.Controller;

import com.mbti.MbtiProJect.entity.Member;
import com.mbti.MbtiProJect.service.MbtiService;
import com.mbti.MbtiProJect.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MbtiService mbtiService;

    @GetMapping("/mbti/Introduction")
    public String IntroductionPage(HttpSession httpSession,Model model){
        String userSession=null;

        if (httpSession.getAttribute("memberid")!= null){
            userSession=(String) httpSession.getAttribute("memberid");
        }
        model.addAttribute("userSession",userSession);
        return "introducePage";
    }
    @GetMapping("/mbti/resultpage")
    public String resultPage(Model model,HttpSession httpSession){
        String userSession=null;

        if (httpSession.getAttribute("memberid")!= null){
            userSession=(String) httpSession.getAttribute("memberid");
        }
        System.out.println("메인페이지에서:"+userSession);
        model.addAttribute("userSession",userSession);

        String mbtiResult[]=new String[4];
        Long Enum=mbtiService.mbtivalue("E");
        Long Inum=mbtiService.mbtivalue("I");
        if (Enum >Inum) {
            mbtiResult[0] = "E";
        }
        else {
            mbtiResult[0] = "I";
        }
        Long Nnum=mbtiService.mbtivalue("N");
        Long Snum=mbtiService.mbtivalue("S");
        if (Nnum >Snum) {
            mbtiResult[1] = "N";
        }
        else {
            mbtiResult[1] = "S";
        }
        Long Tnum=mbtiService.mbtivalue("T");
        Long Fnum=mbtiService.mbtivalue("F");
        if (Tnum >Fnum) {
            mbtiResult[2] = "T";
        }
        else {
            mbtiResult[2] = "F";
        }
        Long Pnum=mbtiService.mbtivalue("P");
        Long Jnum=mbtiService.mbtivalue("J");
        if (Pnum >Jnum) {
            mbtiResult[3] = "P";
        }
        else {
            mbtiResult[3] = "J";
        }
        System.out.println(mbtiResult[0]+mbtiResult[1]+mbtiResult[2]+mbtiResult[3]);
        String mbtivalue=mbtiResult[0]+mbtiResult[1]+mbtiResult[2]+mbtiResult[3];
        model.addAttribute("mbtivalue",mbtivalue);
        return "resultMainPage";
    }
}
