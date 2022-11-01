package com.mbti.MbtiProJect.Controller;

import com.mbti.MbtiProJect.entity.Member;
import com.mbti.MbtiProJect.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private MemberService memberService;

    @GetMapping("/mbti/login")
    public String loginPage() {

        return "loginPage";
    }
    @GetMapping("/mbti/singup")
    public String SingUpPage() {

        return "SingUpPage";
    }

    @PostMapping("/board/singupAction")
    public String SingUpPro(Member member){

        memberService.singup(member);

        return "loginPage";
    }
}
