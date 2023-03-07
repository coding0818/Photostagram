package kr.co.photostagram.dao;

import kr.co.photostagram.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberDAO {
    public int insertMember(MemberVO vo);
}
