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

    @NotBlank(message = "아이디가 공백이 있거나 Null 입니다.")
    @Pattern(regexp = "^[a-z0-9]{5,19}$", message="아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
    private String username;

    @NotBlank(message = "이름이 공백이 있거나 Null 입니다.")
    @Pattern(regexp = "^[가-힣]{2,15}$", message="이름은 한글만 사용하여 2~15자리여야 합니다.")
    private String name;

    @NotBlank(message = "비밀번호가 공백이 있거나 Null 입니다.")
    @Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$",
                message = "비밀번호는 영문자, 숫자, 특수문자를 하나 이상 입력하여야 하며 8~15자리여야 합니다.")
    private String password;

    @Email
    @NotBlank(message = "이메일에 공백이 있거나 Null 입니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
    private String email;

    private String profileImg;
    private String profileText;
    private String profilePhone;
    private String gender;
    private String birth;
    private String profile_img;
    private String profile_phone;

}
