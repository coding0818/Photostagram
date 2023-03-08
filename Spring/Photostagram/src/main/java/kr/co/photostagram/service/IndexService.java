package kr.co.photostagram.service;

import kr.co.photostagram.dao.IndexDAO;
import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {

    @Autowired
    private IndexDAO dao;

    public int insertComment(CommentVO vo){
        return dao.insertComment(vo);
    }

    public List<PostVO> selectArticles(){
        return dao.selectArticles();
    }

    public List<CommentVO> selectComment(){ return dao.selectComment();}
}
