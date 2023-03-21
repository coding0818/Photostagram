package kr.co.photostagram.DTO;

import lombok.Data;

@Data
public class MessageDTO {

	private String roomId;
	private String writer;
	private String message;
}
