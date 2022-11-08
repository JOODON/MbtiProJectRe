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
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpSession;

@Controller
public class MbtiController {
    @Autowired
    private MbtiService mbtiService;
    @GetMapping("/mbti/mainpage")
    public String mainPage(HttpSession httpSession,Model model){
        String userSession=null;

        if (httpSession.getAttribute("memberid")!= null){
            userSession=(String) httpSession.getAttribute("memberid");
        }
        System.out.println("메인페이지에서:"+userSession);
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

        if(value==null){
            value="1";
        }
        // I랑 E부분 처리
        else if (value.equals("1")&&mbtiValue.getId()<=6){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("E");
        }
        else if(value.equals("2")&&mbtiValue.getId()<=6){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("I");
        }
        //S랑 N값 처리
        else if (value.equals("1")&&mbtiValue.getId()<=11&&mbtiValue.getId()>6){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("N");
        }
        else if(value.equals("2")&&mbtiValue.getId()<=11&&mbtiValue.getId()>6){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("S");
        }
        //T랑 F값 처리
        else if (value.equals("1")&&mbtiValue.getId()<=16&&mbtiValue.getId()>11){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("F");
        }
        else if(value.equals("2")&&mbtiValue.getId()<=16&&mbtiValue.getId()>11){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("T");
        }
        else if (value.equals("1")&&mbtiValue.getId()<=21&&mbtiValue.getId()>16){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("J");
        }
        else if(value.equals("2")&&mbtiValue.getId()<=21&&mbtiValue.getId()>16){
            mbtiValue.setId(mbtiValue.getId());
            mbtiValue.setMbtitext(mbtiValue.getMbtitext());
            mbtiValue.setMbtivalue(mbtiValue.getMbtivalue());
            mbtiValue.setMbtitestvalue("P");
        } else{
            return "redirect:/mbti/resultpage";
        }
        mbtiService.MbtiResultadd(mbtiValue);
        return "secondMainPage";
    }

    @GetMapping("/mbti/resultpage")
    public String resultPage(Model model){
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
