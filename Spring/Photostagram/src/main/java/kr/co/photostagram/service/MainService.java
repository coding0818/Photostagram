package kr.co.photostagram.service;

import kr.co.photostagram.dao.MainDAO;
import kr.co.photostagram.vo.ImageVO;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MainService {

    @Autowired
    private MainDAO dao;

    @Transactional
    public int uploadFiles(PostVO vo){

        log.info("MainService...0");

        List<MultipartFile> files = vo.getFiles();
        log.info("uploadFiles...0");
        log.info("files : "+files.size());

        int result1 = dao.insertPost(vo);
        log.info("result1 :"+result1);

        List<ImageVO> images = fileUpload(files);
        log.info("images : "+images.get(0).getThumb());

        for(int i=0; i< images.size(); i++){
            images.get(i).setPost_no(vo.getNo());
            dao.insertImage(images.get(i));
        }
        return result1;
    }

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    public List<ImageVO> fileUpload(List<MultipartFile> files){

        List<ImageVO> file = new ArrayList<>();

        // 시스템 경로 지정
        String path = new File(uploadPath).getAbsolutePath();

        for(int i=0; i<files.size(); i++){
            // 파일 원래 이름 구하기
            String oriThumb = files.get(i).getOriginalFilename();

            // 파일명 새로 생성
            String extThumb = oriThumb.substring(oriThumb.lastIndexOf("."));
            String newThumb = UUID.randomUUID().toString() + extThumb;

            // 파일 저장
            try{
                files.get(i).transferTo(new File(path, newThumb));
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            ImageVO image = new ImageVO();
            image.setThumb(newThumb);
            file.add(image);
        }
        return file;
    }


}
