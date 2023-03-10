package kr.co.photostagram.dao;

import kr.co.photostagram.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    public int selectCountHashTag(int hashtag_no);
    public int selectSearchResult(@Param("cate") int cate, @Param("searchResult") int searchResult);
    public List<SearchListVO> selectSearchItemRecentUser(int user_no);
    public int deleteSearch(int no);
    public int deleteSearchAll(int user_no);

    public List<PostVO> selectWhoLikeMe(int user_no);
}
