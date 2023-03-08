package kr.co.photostagram.dao;

import kr.co.photostagram.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Mapper
@Repository
public interface ProfileDAO {

    public void insertMember();
    public MemberVO selectMember(String username);
    public void selectMembers();
    public int updateMember(MemberVO vo);
    public void deleteMember();

    /*** 게시물, 팔로워, 팔로잉 수 count ***/

    public int selectCountPost(int no);
    public int selectCountFollower(int no);
    public int selectCountFollowing(int no);


    /*** 프로필 사진 업로드 ***/
    public int updateProfilePhoto(@Param("newName") String newName, @Param("no") int no);

}
