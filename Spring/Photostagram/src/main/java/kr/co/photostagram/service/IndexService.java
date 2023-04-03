package kr.co.photostagram.service;

import kr.co.photostagram.dao.IndexDAO;
import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.Comment_likeVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
    public void insertComment(CommentVO vo){
        dao.insertComment(vo);
    }
    // 답글 작성
    public void insertRespComment(CommentVO vo) {dao.insertRespComment(vo);}

    public List<MemberVO> selectUser(){
        return dao.selectUser();
    }

    public List<MemberVO> selectFollowing(int no){
        return dao.selectFollowing(no);

    }

    @Transactional
    public List<PostVO> selectArticles(List<Integer> usersNo){

        List<PostVO> posts = dao.selectArticles(usersNo);

        for(PostVO vo : posts){
            int count = dao.selectCommentCountNum(vo.getNo());
            vo.setCommentCount(count);
        }

        return posts;
    }

    @Transactional
    public List<CommentVO> selectComment(){
        List<CommentVO> comments = dao.selectComment();

        for(CommentVO vo : comments){
            int likecount = dao.selectModalCommentlikeCount(vo.getNo());
            vo.setModal_likeCount(likecount);
        }
        return comments;

    }


}
