package kr.co.photostagram.dao;

import kr.co.photostagram.vo.HashTagVO;
import kr.co.photostagram.vo.ImageVO;
import kr.co.photostagram.vo.PostVO;
import kr.co.photostagram.vo.Post_hashtagVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MainDAO {

    public int insertPost(PostVO vo);
    public int insertImage(ImageVO vo);

    public int findTagByContent(String hashtag);
    public int saveTag(HashTagVO vo);
    public int saveTagAndPost(Post_hashtagVO vo);
    public int selectHashTagNo(String hashtag);
}
