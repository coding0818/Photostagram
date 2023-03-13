package kr.co.photostagram.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PostVO {

    private int no;
    private String content;
    private int user_no;
    private String rdate;
    private int like;
    private List<MultipartFile> files;

    private int count;

    private List<ImageVO> imageVO;
    private MemberVO memberVO;
    private List<CommentVO> commentVO;
    private List<Comment_likeVO> comment_likeVO;
    private List<Post_likeVO> post_likeVO;
}
