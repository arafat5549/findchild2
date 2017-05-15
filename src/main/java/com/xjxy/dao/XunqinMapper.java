package com.xjxy.dao;

import com.xjxy.model.Xunqin;
import java.util.List;

public interface XunqinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Xunqin record);

    int insertSelective(Xunqin record);

    Xunqin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Xunqin record);

    int updateByPrimaryKey(Xunqin record);

    List<Xunqin> selectList(String sql);

    Object selectXunqin(String sql);
}