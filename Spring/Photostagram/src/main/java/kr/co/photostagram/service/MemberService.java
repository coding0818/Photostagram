package kr.co.photostagram.service;

import kr.co.photostagram.dao.MemberDAO;
import kr.co.photostagram.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberService {

    @Autowired
    private MemberDAO dao;

    public void insertMember(MemberVO vo){
        dao.insertMember(vo);
    }
}
