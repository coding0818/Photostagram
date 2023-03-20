package kr.co.photostagram.dao;

import kr.co.photostagram.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardDAO {
   public MemberVO selectMember(String username);
   public BoardVO selectPost(int no);
   public BoardVO selectContent(int no);
   public List<Board1VO> selectPostHashTag(int no);
   public List<CommentVO> selectcomments(int no);
   public List<ImageVO> selectimages(int no);
   public List<Board3VO> selectcommentlist(int no);

}
