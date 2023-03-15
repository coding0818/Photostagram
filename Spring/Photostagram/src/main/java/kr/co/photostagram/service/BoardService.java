package kr.co.photostagram.service;

import kr.co.photostagram.dao.BoardDAO;
import kr.co.photostagram.dao.ProfileDAO;
import kr.co.photostagram.vo.ImageVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BoardService {

    @Autowired
    private BoardDAO dao;

    public MemberVO selectMember(String username){return dao.selectMember(username);}
}
