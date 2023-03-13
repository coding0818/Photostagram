package kr.co.photostagram.service;

import kr.co.photostagram.dao.IndexDAO;
import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.Comment_likeVO;
import kr.co.photostagram.vo.PostVO;
import kr.co.photostagram.vo.Post_likeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {

    @Autowired
    private IndexDAO dao;

    public int insertArticleLikeAdd(PostVO vo) { return dao.insertArticleLikeAdd(vo); }
    public void postLikeAddUpdate(PostVO vo) { dao.postLikeAddUpdate(vo); }
    public int deleteArticleLike(PostVO vo) { return dao.deleteArticleLike(vo); }

    public int insertCommentLikeAdd(Comment_likeVO vo){
        return dao.insertCommentLikeAdd(vo);
    };

    public int deleteCommentLike(Comment_likeVO vo){
        return dao.deleteCommentLike(vo);
    }

    public int insertComment(CommentVO vo){
        return dao.insertComment(vo);
    }

    public List<PostVO> selectArticles(){
        return dao.selectArticles();
    }

    public List<CommentVO> selectComment(){ return dao.selectComment();}
}
