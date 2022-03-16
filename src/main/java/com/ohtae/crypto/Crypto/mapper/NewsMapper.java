package com.ohtae.crypto.Crypto.mapper;

import com.ohtae.crypto.Crypto.data.NewsInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {
    void insertNewsInfo(NewsInfoVO data);
}
