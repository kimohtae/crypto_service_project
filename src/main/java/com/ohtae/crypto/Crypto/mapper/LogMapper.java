package com.ohtae.crypto.Crypto.mapper;

import java.util.Date;
import java.util.List;

import com.ohtae.crypto.Crypto.data.ClickLinkLogVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    List<ClickLinkLogVO> selectClickLinkLogs(Date start, Date end);
    void insertClickLog(ClickLinkLogVO data);
}
