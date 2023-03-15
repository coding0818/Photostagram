package kr.co.photostagram.dao;

import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.Comment_likeVO;
import kr.co.photostagram.vo.PostVO;
import kr.co.photostagram.vo.Post_likeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IndexDAO {

    // 게시글 좋아요, 좋아요 삭제
    public int insertArticleLikeAdd(PostVO vo);
    public int deleteArticleLike(PostVO vo);
    // `post` like Update
    public void postLikeAddUpdate(PostVO vo);
    public void postLikeDelUpdate(PostVO vo);

    // 댓글 좋아요, 좋아요 삭제
    public int insertCommentLikeAdd(Comment_likeVO vo);
    public int deleteCommentLike(Comment_likeVO vo);

    // 댓글 등록
    public void insertComment(CommentVO vo);

    public List<PostVO> selectArticles();
    public List<CommentVO> selectComment();

}
