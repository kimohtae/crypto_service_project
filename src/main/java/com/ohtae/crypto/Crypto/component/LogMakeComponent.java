package com.ohtae.crypto.Crypto.component;

import com.ohtae.crypto.Crypto.data.ClickLinkLogVO;
import com.ohtae.crypto.Crypto.mapper.LogMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogMakeComponent {
    @Autowired LogMapper lMapper;
    final int cnt = 10000;
    final int mi_start = 9;
    final int mi_end = 148;
    final int npi_start = 2294;
    final int npi_end = 2906;

    
    @Scheduled(cron = "* 10 * * * *")
    public void makePageClickLog(){
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
