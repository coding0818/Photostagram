package kr.co.photostagram.service;

import kr.co.photostagram.dao.MainDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    private MainDAO dao;

    public int uploadFiles(){
        return 1;
    }


}
