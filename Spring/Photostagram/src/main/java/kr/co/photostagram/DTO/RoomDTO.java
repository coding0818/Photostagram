package kr.co.photostagram.DTO;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoomDTO {

	private String roomId;
	private Set<WebSocketSession> sessions = new HashSet<>();
}
