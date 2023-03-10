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

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 5, max = 19, message = "아이디는 5 ~ 19자 사이여야 합니다.")
    private String username;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(min = 2, max = 15, message = "이름은 2 ~ 15자 사이여야 합니다.")
    private String name;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    @Email
    @NotBlank(message = "이메일는 필수 입력 값입니다.")
    private String email;

    private String profileImg;
    private String profileText;
    private String profilePhone;
    private String gender;
    private String birth;
    private String profile_img;
    private String profile_phone;

}
