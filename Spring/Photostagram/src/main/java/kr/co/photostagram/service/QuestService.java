package kr.co.photostagram.service;

import kr.co.photostagram.dao.QuestDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class QuestService {
    @Autowired
    private QuestDAO dao;
}
