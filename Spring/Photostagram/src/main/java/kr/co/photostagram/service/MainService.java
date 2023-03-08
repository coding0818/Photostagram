package kr.co.photostagram.service;

import kr.co.photostagram.dao.MainDAO;
import kr.co.photostagram.vo.HashTagVO;
import kr.co.photostagram.vo.ImageVO;
import kr.co.photostagram.vo.PostVO;
import kr.co.photostagram.vo.Post_hashtagVO;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        // 태그 리스트 선별
        List<String> tagList = findHashtag(vo.getContent());
        log.info("tagList : "+tagList);

        if(!tagList.isEmpty()){
            // 게시글 내용 수정(태그 제외한 글)
            String newContent = replaceContent(vo.getContent());
            log.info("content : "+newContent);
            vo.setContent(newContent);
        }

        // 게시글 DB 넣기
        int result1 = dao.insertPost(vo);
        log.info("result1 :"+result1);

        if(!tagList.isEmpty()){
            // 해시태그 DB 넣기
            saveTag(tagList, vo.getNo());
        }

        // 이미지 DB 넣기
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

    public List<String> findHashtag(String content){
        Pattern mypattern = Pattern.compile("#(\\S+)");
        Matcher mat = mypattern.matcher(content);
        List<String> tagList = new ArrayList<>();

        while(mat.find()){
            tagList.add(mat.group(1));
        }
        return tagList;
    }

    public String replaceContent(String content){
        int contentI = content.indexOf("#");
        log.info("contentIndex : "+contentI);
        String newContent = content.substring(0, contentI);
        log.info(newContent);
        return newContent;
    }

    @Transactional
    public void saveTag(List<String> tagList, int post_no){

        for(String tag : tagList){
            int findResult = dao.findTagByContent(tag);

            HashTagVO vos = new HashTagVO();
            vos.setHashtag(tag);

            Post_hashtagVO vo = new Post_hashtagVO();

            if(findResult == 0){
                // 등록된 태그가 아니라면 태그부터 추가
                dao.saveTag(vos);
                vo.setPost_no(post_no);
                vo.setHashtag_no(vos.getNo());
            }else{
                // 등록된 태그라면 태그번호 조회
                int hashtag_no = dao.selectHashTagNo(tag);
                vo.setPost_no(post_no);
                vo.setHashtag_no(hashtag_no);
            }

            // 태그-포스트 매핑 테이블에 데이터 추가
            dao.saveTagAndPost(vo);
        }
    }

    public List<HashTagVO> selectHashTag(String searchItem){
        return dao.selectHashTag(searchItem);
    }

}
