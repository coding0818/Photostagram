package kr.co.photostagram.service;

import kr.co.photostagram.dao.MyDAO;
import kr.co.photostagram.vo.CommentVO;
import kr.co.photostagram.vo.HistoryVO;
import kr.co.photostagram.vo.MemberVO;
import kr.co.photostagram.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MyService {

    @Autowired
    private MyDAO dao;

    public int insertType (int no, String type) {return dao.insertType(no, type);}
    public int insertDetail (int no, String type, String detail) {return dao.insertDetail(no, type, detail);}
    public int selectUserRecent () {return dao.selectUserRecent();}
    public PostVO selectPost (int no) {return dao.selectPost(no);}
    public List<PostVO> selectPosts (int no) {return dao.selectPosts(no);}
    public int[] selectLikePostNo (int no) {return dao.selectLikePostNo(no);}
    public int[] selectCommentNo (int no) {return dao.selectCommentNo(no);}
    public List<PostVO> selectMyCommentPosts (int no) {return dao.selectMyCommentPosts(no);}
    public List<CommentVO> selectMyComments (int postNo, int userNo) {return dao.selectMyComments(postNo, userNo);}
    public List<HistoryVO> selectHistory (int no) {return dao.selectHistory(no);}
    public String selectJoinDate (int no) {return dao.selectJoinDate(no);}
    public int updateRemoveLike (int no) {return dao.updateRemoveLike(no);}
    public int deleteComment(int no) {return dao.deleteComment(no);}
    public int deleteCommentLike (int no) {return dao.deleteCommentLike(no);}
    public int deleteLike (int postNo, int userNo) {return dao.deleteLike(postNo, userNo);}
    public int deletePost (int no) {return dao.deletePost(no);}


    public void updateHistories (MemberVO preUser, MemberVO newUser) {

        log.info("here1");

        if (!(preUser.getProfileText()).equals(newUser.getProfileText())) {
            log.info("here2");
            insertDetail(preUser.getNo(), "intro", newUser.getProfileText());
        }

        if (!(preUser.getProfilePhone()).equals(newUser.getProfilePhone())) {
            log.info("here3");
            insertDetail(preUser.getNo(), "phone", newUser.getProfilePhone());
        }

        if (!(preUser.getName()).equals(newUser.getName())){
            log.info("here4");
            insertDetail(preUser.getNo(), "name", newUser.getName());
        }
    }
}
