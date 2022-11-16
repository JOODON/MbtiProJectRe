package com.mbti.MbtiProJect.Controller;

import com.mbti.MbtiProJect.entity.Member;
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

    @GetMapping("/mbti/singup")
    public String SingUpPage() {

        return "SingUpPage";
    }

    @PostMapping("/board/singupAction")
    public String SingUpPro(Member member){

        memberService.singup(member);

        return "loginPage";
    }

    @GetMapping("/mbti/login")
    public String loginPage() {

        return "loginPage";
    }
    @PostMapping("/mbti/loginPro")
    public String boardlogin(Member member, Model model, HttpSession httpSession){
        int result=memberService.login(member.getMemberid(), member.getMemberpassword());
        String userSession=null;

        if (httpSession.getAttribute("memberid")!= null){
            userSession=(String) httpSession.getAttribute("memberid");
        }
        if(userSession != null) {
            model.addAttribute("message", "이미 로그인에 되어있습니다.");
            model.addAttribute("searchUrl", "/mbti/mainpage");
        }
        if (result==1) {
            httpSession.setAttribute("memberid",member.getMemberid());
            model.addAttribute("message", "로그인에 성공하셨습니다");
            model.addAttribute("searchUrl", "/mbti/mainpage");
        }
        else if (result==0) {
            model.addAttribute("message", "비밀번호가 틀립니다");
            model.addAttribute("searchUrl", "/mbti/login");
        }
        else if (result==-1) {
            model.addAttribute("message", "존재하지않는 아이디입니다");
            model.addAttribute("searchUrl", "/mbti/login");
        }
        else if (result==-2) {
            model.addAttribute("message", "데이터 베이스의 오류가 발견되었습니다");
            model.addAttribute("searchUrl", "/mbti/login");
        }
        model.addAttribute("userSession",userSession);

        return "message";
        }
    @GetMapping("/mbti/logout/pro")
    public String mbtilogout(HttpSession httpSession,Model model){
        httpSession.removeAttribute("memberid");
        model.addAttribute("message", "로그아웃 되었습니다.");
        model.addAttribute("searchUrl", "/mbti/mainpage");
        return "message";
    }
    @GetMapping("/mbti/Introduction")
    public String IntroductionPage(HttpSession httpSession,Model model){
        String userSession=null;

        if (httpSession.getAttribute("memberid")!= null){
            userSession=(String) httpSession.getAttribute("memberid");
        }
        model.addAttribute("userSession",userSession);
        return "introducePage";
    }
}
