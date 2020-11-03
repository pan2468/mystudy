package com.myspring.lim101;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"readcount"})
public class BoardVO {
    private int num;
    private String pass;
    private String name;
    private String email;
    private String title;
    private String content;
    private int readcount;
    private Date writedate;

    public String getWritedate() {
        String format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(writedate);
        return format;
    }
}
