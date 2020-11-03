package com.myspring.lim101;

import com.myspring.lim101.utils.PubMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardRestController {

    @Autowired
    BoardMapper2 boardMapper2;

    /**
     * 공지사항 목록
     */
    @GetMapping(value = "/notice.json")
    public List<PubMap> list() {
        return boardMapper2.selectBoard();
    }

    @PostMapping(value = "/notice.json")
    public PubMap create(PubMap vo) {
        int pk = boardMapper2.insertBoard(vo);
        return vo;
    }

    @PutMapping(value = "/notice/{num}.json")
    public PubMap update(@PathVariable("num") int num, PubMap vo) {
        int afftecd = boardMapper2.updateBoard(vo);
        return vo;
    }

    @DeleteMapping(value = "/notice/{num}")
    public ResultVO delete(@PathVariable("num") int num) {
        int cnt = boardMapper2.deleteBoard(num);
        return new ResultVO("삭제되었습니다");
    }
}
