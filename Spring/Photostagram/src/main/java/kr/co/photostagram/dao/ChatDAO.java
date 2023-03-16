package kr.co.photostagram.dao;

import kr.co.photostagram.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatDAO {

    public List<MemberVO> findAllUsers(String keyword);
    public int insertChatRoom(@Param("my_no") int my_no,@Param("user_no") int user_no);
}
