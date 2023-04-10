package kr.co.photostagram.dao;

import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 날짜 : 2023/04/05
 * 이름 : 조주영
 * 내용 : myDAO
 */

@Mapper
@Repository
public interface MyDAO {

    public PostVO selectPost (int no);
    public List<PostVO> selectPosts (int no);
    public int[] selectLikePostNo (int no);
    public int[] selectCommentNo (int no);
    public List<PostVO> selectMyCommentPosts (int no);
    public List<CommentVO> selectMyComments (@Param("postNo") int postNo, @Param("userNo") int userNo);
    public int updateRemoveLike (int no);
    public int deleteCommentLike (int no);
    public int deleteLike (@Param("postNo") int postNo, @Param("userNo") int userNo);
    public int deletePost (int no);

}
