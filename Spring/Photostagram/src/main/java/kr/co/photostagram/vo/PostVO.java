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
    private int like;
    private String rdate;
    private List<MultipartFile> files;
    // 추가필드
    private int commentCount;

    private String firstThumb;

    private int thumbs;

    private Post_likeVO post_likeVO;
    private List<ImageVO> imageVO;
    private MemberVO memberVO;
    private List<HashTagVO> hashTagVO;

    private String thumb;
    private List<Integer> tags;

}
