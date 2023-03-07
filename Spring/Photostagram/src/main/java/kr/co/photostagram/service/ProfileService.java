package kr.co.photostagram.service;

import kr.co.photostagram.dao.ProfileDAO;
import kr.co.photostagram.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProfileService {

    @Autowired
    private ProfileDAO dao;

    public void insertMember(){}
    public MemberVO selectMember(String username){return dao.selectMember(username);}
    public void selectMembers(){}
    public int updateMember(MemberVO vo){return dao.updateMember(vo);}
    public void deleteMember(){}

}
