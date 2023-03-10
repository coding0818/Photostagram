package kr.co.photostagram.dao;

import kr.co.photostagram.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SearchDAO {
    public int[] selectPostNums(int hashtag_no);
    public PostVO selectPostsByHashTag(int no);
    public String selectHashTagName(int no);
}
