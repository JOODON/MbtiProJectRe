package com.mbti.MbtiProJect.Controller;

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
    public String mainPage(Model model, @PageableDefault(page = 0,size = 1,sort = "id",direction= Sort.Direction.ASC ) Pageable pageable) {

        model.addAttribute("list",mbtiService.mbtilists(pageable));

        return "mainPage";
    }
}
