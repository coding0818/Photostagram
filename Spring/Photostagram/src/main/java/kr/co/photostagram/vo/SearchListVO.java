package kr.co.photostagram.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchListVO {
    private int no;
    private int user_no;
    private String searchItem;
    private String rdate;
    private int cate;
    private int searchResult;
    private String result;
    private String img;
    private String text;
}
