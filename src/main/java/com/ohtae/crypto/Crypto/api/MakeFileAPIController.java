package com.ohtae.crypto.Crypto.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ohtae.crypto.Crypto.data.ClickLinkLogVO;
import com.ohtae.crypto.Crypto.mapper.LogMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MakeFileAPIController {
    @Autowired LogMapper lMapper;
    @Value("${history.link.click.path}")String link_click_path;

    @GetMapping("/link/click")
    public String userConnDataSchedule(@RequestParam String start, @RequestParam String end) throws Exception{
        Calendar cal = Calendar.getInstance();
        cal.set(
            Integer.parseInt(start.split("-")[0]), 
            Integer.parseInt(start.split("-")[1])-1, 
            Integer.parseInt(start.split("-")[2]),
            0,0,0
        );
        Date startDt = cal.getTime();

        cal.set(
            Integer.parseInt(end.split("-")[0]), 
            Integer.parseInt(end.split("-")[1])-1, 
            Integer.parseInt(end.split("-")[2]),
            23,59,59
        );
        Date endDt = cal.getTime();


        List<ClickLinkLogVO> list = lMapper.selectClickLinkLogs(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = link_click_path+"/batch_data/linkClick"+c.getTimeInMillis()+".txt";
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(ClickLinkLogVO data:list){
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();

        String dest = link_click_path+"/log_data/linkClick"+c.getTimeInMillis()+".txt";
        File srcFile = new File(src);
        File destFile = new File(dest);
        srcFile.renameTo(destFile);

        return "로그파일 생성 완료";
    }
}
