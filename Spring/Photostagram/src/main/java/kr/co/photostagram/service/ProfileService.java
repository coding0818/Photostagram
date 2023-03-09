package kr.co.photostagram.service;

import kr.co.photostagram.dao.ProfileDAO;
import kr.co.photostagram.vo.ImageVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ProfileService {

    @Autowired
    private ProfileDAO dao;

    public void insertMember(){}
    public MemberVO selectMember(String username){return dao.selectMember(username);}
    public void selectMembers(){}
    public int updateMember(MemberVO vo){return dao.updateMember(vo);}
    public void deleteMember(){}


    /*** 추가 서비스 로직 ***/

    public List<PostVO> selectPosts(int no) {return dao.selectPosts(no);}

    public ImageVO selectThumb(int postNo) {return dao.selectThumb(postNo);}

    /*** 게시물, 팔로워, 팔로잉 카운트 ***/

    public int selectCountPost(int no) {return dao.selectCountPost(no);}
    public int selectCountFollower(int no) {return dao.selectCountFollower(no);}
    public int selectCountFollowing(int no) {return dao.selectCountFollowing(no);}


    /*** 팔로워, 팔로잉 ***/

    public int insertFollowing(int follower, int following) {return dao.insertFollow(follower, following);}
    public int deleteFollowing(int follower, int following) {return dao.deleteFollow(follower, following);}

    public int searchFollowing(int follower, int following) {return dao.searchFollowing(follower, following);}


    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    /*** 프로필 사진 업로드 ***/
    public int uploadProfilePhoto(MultipartFile file, int no){

        // 경로
        String path = new File(uploadPath).getAbsolutePath();
        
        String oriName = file.getOriginalFilename();                    // 업로드된 파일의 original name
        String type = oriName.substring(oriName.lastIndexOf("."));  // 업로드된 파일의 확장자명 찾기
        String newName = UUID.randomUUID().toString() + type;           // 업로드된 파일명 uuid로 암호화

        // 경로 + uuid 암호화된 파일까지 합친 이미지 파일 경로
        File dest = new File(path, newName);
        try {
            file.transferTo(dest);  // 경로 내 저장
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int result = dao.updateProfilePhoto(newName, no);   // DB 업로드

        return result;
    }


    /*** 수정 전 프로필 사진 삭제 ***/

    public void deleteProfilePhoto(String name){
        String path = new File(uploadPath).getAbsolutePath();
        String deletePath = path + "/" + name;

        File file = new File(deletePath);
        if (file.exists() == true){
            file.delete();
        }
    }

}
