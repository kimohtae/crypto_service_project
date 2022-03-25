package com.ohtae.crypto.Crypto.mapper;

import java.util.Date;
import java.util.List;

import com.ohtae.crypto.Crypto.data.MemberInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    List<MemberInfoVO> selectMemberInfo(Date start, Date end);

    Integer selectMemberFirst();
    Integer selectMemberLast();
}
