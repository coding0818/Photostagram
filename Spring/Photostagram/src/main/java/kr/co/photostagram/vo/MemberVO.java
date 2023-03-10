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

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]{5,19}$")
    private String username;

    @NotBlank
    @Pattern(regexp = "^[가-힣]{2,15}$")
    private String name;

    @NotBlank
    @Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")
    private String password;

    @Email
    @NotBlank
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
