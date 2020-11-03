package com.myspring.lim101;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping(value = "/notice.do")
    public String notice_list(Model model) {
        return "notice";
    }
}
