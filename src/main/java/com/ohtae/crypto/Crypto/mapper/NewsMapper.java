package com.ohtae.crypto.Crypto.mapper;

import java.util.Date;
import java.util.List;

import com.ohtae.crypto.Crypto.data.NewsInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {
    void insertNewsInfo(NewsInfoVO data);

    Integer selectNewsFirst();
    Integer selectNewsLast();


    List<NewsInfoVO> selectNewsInfo(Date start, Date end);
}
