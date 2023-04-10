package kr.co.photostagram.service;

import kr.co.photostagram.dao.MyDAO;
import kr.co.photostagram.vo.CommentVO;
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

    public PostVO selectPost (int no) {return dao.selectPost(no);}
    public List<PostVO> selectPosts (int no) {return dao.selectPosts(no);}
    public int[] selectLikePostNo (int no) {return dao.selectLikePostNo(no);}
    public int[] selectCommentNo (int no) {return dao.selectCommentNo(no);}
    public List<PostVO> selectMyCommentPosts (int no) {return dao.selectMyCommentPosts(no);}
    public List<CommentVO> selectMyComments (int postNo, int userNo) {return dao.selectMyComments(postNo, userNo);}
    public int updateRemoveLike (int no) {return dao.updateRemoveLike(no);}
    public int deleteCommentLike (int no) {return dao.deleteCommentLike(no);}
    public int deleteLike (int postNo, int userNo) {return dao.deleteLike(postNo, userNo);}
    public int deletePost (int no) {return dao.deletePost(no);}

}
