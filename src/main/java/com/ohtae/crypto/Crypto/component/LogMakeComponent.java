package com.ohtae.crypto.Crypto.component;

import com.ohtae.crypto.Crypto.data.ClickLinkLogVO;
import com.ohtae.crypto.Crypto.mapper.LogMapper;
import com.ohtae.crypto.Crypto.mapper.MemberMapper;
import com.ohtae.crypto.Crypto.mapper.NewsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogMakeComponent {
    @Autowired LogMapper lMapper;
    @Autowired NewsMapper nMapper;
    @Autowired MemberMapper mMapper;
    final int cnt = 1000;
    
    
    @Scheduled(cron = "0 * * * * *")
    public void makePageClickLog(){
        int mi_start = mMapper.selectMemberFirst();
        int mi_end = mMapper.selectMemberLast();
        int npi_start = nMapper.selectNewsFirst();
        int npi_end = nMapper.selectNewsLast();

        for(int i=0; i<cnt; i++){
            int mi_seq = (int)(Math.random()*(mi_end-mi_start) + mi_start);
            int npi_seq = (int)(Math.random()*(npi_end-npi_start) + npi_start);
            ClickLinkLogVO log = new ClickLinkLogVO();
            log.setCll_mi_seq(mi_seq);
            log.setCll_npi_seq(npi_seq);
            lMapper.insertClickLog(log);
        }
    }
}
