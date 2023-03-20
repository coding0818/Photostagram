package kr.co.photostagram.DTO;

import lombok.Data;

import java.util.HashMap;

@Data
public class ChatRoom {
    private String roomId;
    private HashMap<String, String> userlist = new HashMap<String, String>();

    public ChatRoom create(String roomId){
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = roomId;

        return chatRoom;
    }
}
