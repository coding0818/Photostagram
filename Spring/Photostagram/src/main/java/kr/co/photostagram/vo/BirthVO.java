package kr.co.photostagram.vo;

import lombok.Data;

/*
    날짜 : 2023/03/07
    이름 : 김진우
    내용 : 회원가입에 필요한 BirthVO
 */
@Data
public class BirthVO {
    private int year;
    private int month;
    private int day;
}
