package com.mysite.reactboardsession.board;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pageing {
    private int page_num;
    private int limit;
}
