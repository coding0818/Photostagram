package kr.co.photostagram.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HashTagVO {
    private int no;
    private String hashtag;

    // 해시태그_포스트 개수
    private int countPost;
}
