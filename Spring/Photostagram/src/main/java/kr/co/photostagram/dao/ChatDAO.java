package kr.co.photostagram.dao;

import kr.co.photostagram.vo.ChattingVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.RecommendVO;
import kr.co.photostagram.vo.RoomVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChatDAO {

    public List<MemberVO> findAllUsers(String keyword);
    public int insertChatRoom(RoomVO vo);
    public int insertChatRoomMember(@Param("room") int room, @Param("user_no") int user_no);
    public List<RoomVO> selectChatRoomList(int me);
    public RoomVO selectNowRoom(@Param("room_no") int room_no);
    public int insertMessages(ChattingVO vo);
    public List<ChattingVO> selectMessages(int room);
    public int[] selectChatRoomNotMine(int user);
    public RoomVO selectChatRoomsNotMine(int room_no);
    public List<RecommendVO> selectRecommends(int user_no);
}
