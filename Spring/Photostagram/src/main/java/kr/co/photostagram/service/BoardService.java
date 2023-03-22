package kr.co.photostagram.service;

import kr.co.photostagram.dao.BoardDAO;
import kr.co.photostagram.dao.ProfileDAO;
import kr.co.photostagram.vo.*;
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

    public BoardVO selectPost(int no) {return dao.selectPost(no);}
    public BoardVO selectContent(int no) {return dao.selectContent(no);}
    public List<Board1VO> selectPostHashTag(int no) {return dao.selectPostHashTag(no);}
    public List<CommentVO> selectcomments(int no) {return dao.selectcomments(no);}
    public List<ImageVO> selectimages(int no) {return dao.selectimages(no);}
    public List<Board2VO> selectcommentlist(int no) {return dao.selectcommentlist(no);}
    public List<NoticeVO> selectNoticesTime(int no) {return dao.selectNoticesTime(no);}

    public PostVO selectContentLikeTime(int no) {return dao.selectContentLikeTime(no);}




}
