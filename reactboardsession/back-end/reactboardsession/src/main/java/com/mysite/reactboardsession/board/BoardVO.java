package com.mysite.reactboardsession.board;

import lombok.Data;

@Data
public class BoardVO {
    private int board_num;
    private String board_writer;
    private String board_title;
    private String board_content;
    private String board_date;


}