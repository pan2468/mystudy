package com.myspring.lim101;

import com.myspring.lim101.utils.PubMap;

import java.util.List;

public interface BoardMapper2 {
    List<PubMap> selectBoard();

    int insertBoard(PubMap vo);

    int updateBoard(PubMap vo);

    int deleteBoard(int num);

    PubMap selectBoardByNum(String num);

}
