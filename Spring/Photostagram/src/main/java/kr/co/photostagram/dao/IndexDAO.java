package kr.co.photostagram.dao;

import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IndexDAO {


    public int insertComment(CommentVO vo);
    public List<PostVO> selectArticles();
    public List<CommentVO> selectComment();

}
