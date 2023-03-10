package kr.co.photostagram.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {

    private int no;

    @NotBlank(message = "{required.user.userName}")
    @Size(min = 5, max = 19, message = "{size.user.userName}")
    private String username;

    @NotBlank(message = "{required.user.name}")
    @Size(min = 2, max = 15, message = "{size.user.name}")
    private String name;

    @NotBlank(message = "{required.user.pass}")
    private String password;

    @Email
    @NotBlank(message = "{required.user.email}")
    private String email;

    private String profileImg;
    private String profileText;
    private String profilePhone;
    private String gender;
    private String birth;
    private String profile_img;
    private String profile_phone;

}
