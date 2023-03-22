package kr.co.photostagram.service;

import kr.co.photostagram.DTO.ChatRoom;
import kr.co.photostagram.DTO.MessageDTO;
import kr.co.photostagram.dao.ChatDAO;
import kr.co.photostagram.vo.ChattingVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.RecommendVO;
import kr.co.photostagram.vo.RoomVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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

        List<ChatRoom> rooms = dao.selectChatRoomHave(my_no);
        log.info("roomsIhave : "+rooms);
        boolean isNewRoom = false;
        for(int i=0; i<rooms.size(); i++){
            if(rooms.get(i).getMes().get(i) == my_no){
                log.info("i  : "+i);
                isNewRoom = rooms.get(i).getUsers().containsAll(user_no);
                log.info("isNewRoom"+ isNewRoom);
                if(isNewRoom == true){
                    return rooms.get(i).getNo();
                }
            }else if(rooms.get(i).getUsers().get(i) == my_no){
                log.info("i  : "+i);
                isNewRoom = rooms.get(i).getMes().containsAll(user_no);
                log.info("isNewRoom"+isNewRoom);
                if(isNewRoom){
                    return rooms.get(i).getNo();
                }
            }
        }

        dao.insertChatRoom(vo);

        for(int user:user_no){
            dao.insertChatRoomMember(vo.getNo(), user);
        }

        return vo.getNo();
    }

    public List<RoomVO> selectChatRoomList(int me){
        return dao.selectChatRoomList(me);
    }

    public RoomVO selectNowRoom(int room_no){
        return dao.selectNowRoom(room_no);
    }

    public int insertMessages(MessageDTO vo){
        return dao.insertMessages(vo);
    }

    public List<ChattingVO> selectMessages(int room){
        return dao.selectMessages(room);
    }

    public List<RoomVO> selectChatRoomNotMine(int user){
        int[] rooms_no = dao.selectChatRoomNotMine(user);
        List<RoomVO> rooms = new ArrayList<>();
        for(int no : rooms_no){
            RoomVO vo = dao.selectChatRoomsNotMine(no);
            rooms.add(vo);
        }
        return rooms;
    }

    public List<RecommendVO> selectRecommends(int user_no){
        return dao.selectRecommends(user_no);
    }
}
