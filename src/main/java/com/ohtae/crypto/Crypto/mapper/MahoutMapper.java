package com.ohtae.crypto.Crypto.mapper;

import java.util.List;

import com.ohtae.crypto.Crypto.data.NewsRecommendVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MahoutMapper {
    public void insertNewsRecommendList(List<NewsRecommendVO> list);
}
