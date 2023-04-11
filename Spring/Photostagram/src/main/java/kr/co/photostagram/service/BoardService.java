package kr.co.photostagram.service;

import kr.co.photostagram.dao.BoardDAO;
import kr.co.photostagram.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class BoardService {

    @Autowired
    private BoardDAO dao;

    public int detailPostLikeAdd(PostVO vo) { return dao.detailPostLikeAdd(vo); }
    public int detailPostDelAdd(PostVO vo) { return dao.detailPostDelAdd(vo); }

    public int detailPostCommentLikeAdd(Comment_likeVO vo){
        return dao.detailPostCommentLikeAdd(vo);
    };
    public int detailPostCommentLikeDel(Comment_likeVO vo){
        return dao.detailPostCommentLikeDel(vo);
    }
    // Post-like Update
    public void detailPostLikeUpdate(PostVO vo) { dao.detailPostLikeUpdate(vo); }
    public void detailPostDelLikeUpdate(PostVO vo) { dao.detailPostDelLikeUpdate(vo);}

    public BoardVO selectMember(String username){return dao.selectMember(username);}

    public BoardVO selectPost(int no) {return dao.selectPost(no);}
    public BoardVO selectContent(int no) {return dao.selectContent(no);}
//    public Post_likeVO selectPostLike(int no , int user_no) {return dao.selectPostLike(no, user_no);}

    public List<Board1VO> selectPostHashTag(int no) {return dao.selectPostHashTag(no);}
    public List<CommentVO> selectcomments(int no) {return dao.selectcomments(no);}
    public List<ImageVO> selectimages(int no) {return dao.selectimages(no);}
    public List<Board2VO> selectcommentlist(int no) {return dao.selectcommentlist(no);}
    public List<NoticeVO> selectNoticesTime(int no) {return dao.selectNoticesTime(no);}

    public PostVO selectContentLikeTime(int no) {return dao.selectContentLikeTime(no);}
    public List<PostVO> selectPlusImg(int no) {return dao.selectPlusImg(no);}

    public int insertComment(CommentVO vo) {
        return dao.insertComment(vo);
    }

    @Transactional
    public int deletePost(int post_no){
        List<CommentVO> comments = dao.selectComment(post_no);
        for(CommentVO comment: comments){
            dao.deleteCommentLike(comment.getNo());
        }
        dao.deletePosts(post_no);
        int result = dao.deletePost(post_no);

        return result;
    }

    public List<UserTagVO> selectUserTags(int post_no){
        return dao.selectUserTags(post_no);
    }





}
