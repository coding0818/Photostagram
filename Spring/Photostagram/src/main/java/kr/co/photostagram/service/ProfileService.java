package kr.co.photostagram.service;

import kr.co.photostagram.dao.ProfileDAO;
import kr.co.photostagram.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    /*** 게시물, 팔로워, 팔로잉 카운트 ***/

    public int selectCountPost(int no) {return dao.selectCountPost(no);}
    public int selectCountFollower(int no) {return dao.selectCountFollower(no);}
    public int selectCountFollowing(int no) {return dao.selectCountFollowing(no);}


    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    /*** 프로필 사진 업로드 ***/
    public int uploadProfilePhoto(MultipartFile file, int no){

        String path = new File(uploadPath).getAbsolutePath();

        String oriName = file.getOriginalFilename();
        String type = oriName.substring(oriName.lastIndexOf("."));
        String newName = UUID.randomUUID().toString() + type;

        File dest = new File(path, newName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int result = dao.updateProfilePhoto(newName, no);

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
