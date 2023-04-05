package kr.co.photostagram.dao;

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

    public List<PostVO> selectPosts (@Param("no") int no, @Param("index") int index);

}
