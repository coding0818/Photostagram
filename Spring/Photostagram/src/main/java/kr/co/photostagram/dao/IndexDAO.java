package kr.co.photostagram.dao;

import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.PostVO;
import kr.co.photostagram.vo.Post_likeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IndexDAO {

    public int insertArticleLikeAdd(PostVO vo);
    public int deleteArticleLike(PostVO vo);

    public int insertComment(CommentVO vo);
    public List<Post_likeVO> selectLikeConfirm();
    public List<PostVO> selectArticles();
    public List<CommentVO> selectComment();

}
