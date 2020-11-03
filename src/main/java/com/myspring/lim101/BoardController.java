package com.myspring.lim101;

import com.myspring.lim101.utils.PubMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BoardController {

    @Autowired
    BoardMapper2 boardMapper2;

    @GetMapping(value = "/notice.do")
    public String notice_list(Model model) {
        return "notice";
    }

    @GetMapping(value = "/noticeDetail.do")
    public String notice_detail(HttpServletRequest request, Model model) {
        String num = request.getParameter("num");
        PubMap vo = boardMapper2.selectBoardByNum(num);
        model.addAttribute("vo", vo);
        return "noticeDetail";
    }
}
