package kr.co.photostagram.service;

import kr.co.photostagram.dao.SearchDAO;
import kr.co.photostagram.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    private SearchDAO dao;

}
