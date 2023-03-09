package kr.co.photostagram.dao;

import kr.co.photostagram.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MainDAO {

    public int insertPost(PostVO vo);
    public int insertImage(ImageVO vo);

    public int findTagByContent(String hashtag);
    public int saveTag(HashTagVO vo);
    public int saveTagAndPost(Post_hashtagVO vo);
    public int selectHashTagNo(String hashtag);
    public List<HashTagVO> selectHashTag(String searchItem);
    public List<MemberVO> selectUser(String searchItem);
    public int insertSearchItem(SearchListVO vo);
    public List<SearchListVO> selectSearchItemRecent(int user_no);
}
