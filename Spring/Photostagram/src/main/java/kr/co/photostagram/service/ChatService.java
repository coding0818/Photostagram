package kr.co.photostagram.service;

import kr.co.photostagram.dao.ChatDAO;
import kr.co.photostagram.vo.ChattingVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatDAO dao;

    public List<MemberVO> findAllUsers(String keyword){
        return dao.findAllUsers(keyword);
    }

    @Transactional
    public int insertChatRoom(ArrayList<Integer> user_no, int my_no){
        RoomVO vo = new RoomVO();
        vo.setMe(my_no);
        dao.insertChatRoom(vo);
        for(int user:user_no){
            dao.insertChatRoomMember(vo.getNo(), user);
        }
        return 1;
    }

    public List<RoomVO> selectChatRoomList(int me){
        return dao.selectChatRoomList(me);
    }

    public List<RoomVO> selectNowRoom(int room_no){
        return dao.selectNowRoom(room_no);
    }

    public int insertMessages(ChattingVO vo){
        return dao.insertMessages(vo);
    }

    public List<ChattingVO> selectMessages(int room){
        return dao.selectMessages(room);
    }
}
