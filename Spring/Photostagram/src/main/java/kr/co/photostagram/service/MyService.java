package kr.co.photostagram.service;

import kr.co.photostagram.dao.MyDAO;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MyService {

    @Autowired
    private MyDAO dao;

    public List<PostVO> selectPosts (int no, int index) {return dao.selectPosts(no, index);}
}
