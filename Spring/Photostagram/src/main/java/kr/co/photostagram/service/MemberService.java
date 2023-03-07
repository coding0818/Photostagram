package kr.co.photostagram.service;

import kr.co.photostagram.dao.MemberDAO;
import kr.co.photostagram.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class MemberService {

    @Autowired
    private MemberDAO dao;

    public int insertMember(MemberVO vo){
        return dao.insertMember(vo);
    }

    public void removeSessMember(HttpSession sess){
        sess.removeAttribute("sessMember");
    }
}
