package kr.co.photostagram.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MainDAO {

    public int insertPost();
    public int insertImage();
}
