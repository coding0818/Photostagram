package kr.co.photostagram.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {

    private int no;
    private String username;
    private String name;
    private String password;
    private String email;
    private String profileImg;
    private String profileText;
    private String profilePhone;
    private String gender;
    private String birth;
    private String profile_img;
    private String profile_phone;

}
