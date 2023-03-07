package kr.co.photostagram.dao;

import kr.co.photostagram.vo.ImageVO;
import kr.co.photostagram.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MainDAO {

    public int insertPost(PostVO vo);
    public int insertImage(ImageVO vo);
}
