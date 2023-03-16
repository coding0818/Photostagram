package kr.co.photostagram.dao;

import kr.co.photostagram.vo.ImageVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
@Repository
public interface ProfileDAO {

    public void insertMember();
    public MemberVO selectMember(String username);
    public void selectMembers();
    public int updateMember(MemberVO vo);
    public void deleteMember();

    public int updatePassword(@Param("newPass") String newPass, @Param("no") int no);

    public List<PostVO> selectPosts(int no);

    public PostVO selectThumb(@Param("pageNo") int pageNo, @Param("postNo") int postNo);

    public List<MemberVO> selectFollowers (int no);
    public List<MemberVO> selectFollowings (int no);

    /*** 게시물, 팔로워, 팔로잉 수 count ***/

    public int selectCountPost(int no);
    public int selectCountFollower(int no);
    public int selectCountFollowing(int no);


    /*** 팔로워, 팔로잉 ***/

    public int insertFollow (@Param("follower") int follower, @Param("following") int following);
    public int deleteFollow (@Param("follower") int follower, @Param("following") int following);

    public int searchFollowing (@Param("follower") int follower, @Param("following") int following);


    /*** 프로필 사진 업로드 ***/
    public int updateProfilePhoto(@Param("newName") String newName, @Param("no") int no);

    public int deleteProfilePhoto(int no);

}
