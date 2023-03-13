package kr.co.photostagram.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ImageVO {

    private int no;
    private String thumb;
    private int post_no;
}
