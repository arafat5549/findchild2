package com.xjxy.dao;

import com.xjxy.model.Announce;
import java.util.List;

public interface AnnounceMapper {
    int deleteByPrimaryKey(Integer id);//按主键删除

    int insert(Announce record);       //插入数据库

    int insertSelective(Announce record); //插入数据库-可选择

    Announce selectByPrimaryKey(Integer id); //按主键查找

    int updateByPrimaryKeySelective(Announce record); //更新-可选择

    int updateByPrimaryKeyWithBLOBs(Announce record);

    int updateByPrimaryKey(Announce record);  //

    List<Announce> selectList(String sql); //接口

    Object selectAnnounce(String sql);
}