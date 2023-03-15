package kr.co.photostagram.service;

import kr.co.photostagram.dao.ChatDAO;
import kr.co.photostagram.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatDAO dao;

    public List<MemberVO> findAllUsers(String keyword){
        return dao.findAllUsers(keyword);
    }
}
