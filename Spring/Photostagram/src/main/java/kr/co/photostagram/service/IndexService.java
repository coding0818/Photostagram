package kr.co.photostagram.service;

import kr.co.photostagram.dao.IndexDAO;
import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.Comment_likeVO;
import kr.co.photostagram.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class IndexService {

    @Autowired
    private IndexDAO dao;

    // 게시글 좋아요 , 좋아요 삭제
    public int insertArticleLikeAdd(PostVO vo) { return dao.insertArticleLikeAdd(vo); }
    public int deleteArticleLike(PostVO vo) { return dao.deleteArticleLike(vo); }

    // Post-like Update
    public void postLikeAddUpdate(PostVO vo) { dao.postLikeAddUpdate(vo); }
    public void postLikeDelUpdate(PostVO vo) { dao.postLikeDelUpdate(vo);}

    // 댓글 좋아요 , 좋아요 삭제
    public int insertCommentLikeAdd(Comment_likeVO vo){
        return dao.insertCommentLikeAdd(vo);
    };
    public int deleteCommentLike(Comment_likeVO vo){
        return dao.deleteCommentLike(vo);
    }

    // 댓글 작성
    public int insertComment(CommentVO vo){
        return dao.insertComment(vo);
    }

    @Transactional
    public List<PostVO> selectArticles(){
        List<PostVO> posts = dao.selectArticles();
        for(PostVO vo : posts){
            int count = dao.selectCommentCountNum(vo.getNo());
            vo.setCommentCount(count);
        }
        return posts;
    }

    public List<CommentVO> selectComment(){ return dao.selectComment();}
}
