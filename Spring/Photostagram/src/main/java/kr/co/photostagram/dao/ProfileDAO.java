package kr.co.photostagram.dao;

import kr.co.photostagram.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProfileDAO {

    public void insertMember();
    public MemberVO selectMember(String username);
    public void selectMembers();
    public int updateMember(MemberVO vo);
    public void deleteMember();

}
