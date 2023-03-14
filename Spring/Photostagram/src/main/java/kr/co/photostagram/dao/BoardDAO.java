package kr.co.photostagram.dao;

import kr.co.photostagram.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BoardDAO {

   //클릭한 게시물 사진 불러오기
   public ImageVO selectImg(int postNo);
   public MemberVO selectMember(String username);
   public List<PostVO> selectPosts(int no);
   public ImageVO selectThumb(int postNo);

}
