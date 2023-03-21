package kr.co.photostagram.DTO;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class ChatRoomDAO {
    private Map<String, RoomDTO> chatRoomVOMap;

    @PostConstruct
    private void init(){
        chatRoomVOMap = new LinkedHashMap<>();
    }
}
