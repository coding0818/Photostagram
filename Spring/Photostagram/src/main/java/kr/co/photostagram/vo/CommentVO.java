package kr.co.photostagram.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentVO {
    private int no;
    private String comment;
    private int post_no;
    private int user_no;
    private int parent;
    private String rdate;

    private String username;

}
