package kr.co.photostagram.service;

import kr.co.photostagram.dao.MemberDAO;
import kr.co.photostagram.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class MemberService {

    @Autowired
    private MemberDAO dao;

    @Autowired
    private PasswordEncoder encoder;

    public int insertMember(MemberVO vo){
        // 패스워드 암호화
        vo.setPassword(encoder.encode(vo.getPassword()));
        return dao.insertMember(vo);
    }

    public int chkUserName(String userName){
        return dao.chkUserName(userName);
    }
    public int chkEmail(String email) {
        return dao.chkEmail(email);
    }


}
