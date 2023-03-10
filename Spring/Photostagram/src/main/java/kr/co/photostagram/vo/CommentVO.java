package kr.co.photostagram.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentVO {
    private int no;
    private String comment;
    private int post_no;
    private int user_no;
    private int parent;
    private String rdate;

    // μΆκ°νλ
    private String username;

}
