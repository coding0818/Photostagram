package kr.co.photostagram.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeVO {
    private int no;
    private int myuser_no;
    private int user_no;
    private int table_no;
    private int cate;
    private String rdate;
    private String thumb;
    private int post_no;
    private int comment_no;
    private String profileImg;
    private String username;
}
