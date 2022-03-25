package com.ohtae.crypto.Crypto.component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ohtae.crypto.Crypto.data.ClickLinkLogVO;
import com.ohtae.crypto.Crypto.data.MemberInfoVO;
import com.ohtae.crypto.Crypto.data.NewsInfoVO;
import com.ohtae.crypto.Crypto.mapper.LogMapper;
import com.ohtae.crypto.Crypto.mapper.MemberMapper;
import com.ohtae.crypto.Crypto.mapper.NewsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MakeFileComponent {
    @Autowired LogMapper lMapper;
    @Autowired NewsMapper nMapper;
    @Autowired MemberMapper mMapper;
    @Value("${history.link.click.path}")String link_click_path;
    @Value("${history.news.path}")String news_path;
    @Value("${history.member.path}")String member_path;

    @Scheduled(cron = "0 0 2 * * *")
    public void userConnDataSchedule() throws Exception{
        Calendar search_dt = Calendar.getInstance();
        search_dt.add(Calendar.DATE, 1);
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);
        
        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();


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
    }

    @Scheduled(cron = "0 20 1 */7 * *")
    public void makeNewsInfoFile() throws Exception{
        Calendar search_dt = Calendar.getInstance();
        search_dt.add(Calendar.DATE, 1);
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);
        
        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();


        List<NewsInfoVO> list = nMapper.selectNewsInfo(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = news_path+"/log_data/linkClick"+c.getTimeInMillis()+".txt";
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(NewsInfoVO data:list){
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();

        String dest = news_path+"/batch_data/linkClick"+c.getTimeInMillis()+".txt";
        File srcFile = new File(src);
        File destFile = new File(dest);
        srcFile.renameTo(destFile);
    }

    @Scheduled(cron = "0 20 2  * * *")
    public void makeMemberInfoFile() throws Exception{
        Calendar search_dt = Calendar.getInstance();
        search_dt.add(Calendar.DATE, 1);
        search_dt.set(Calendar.HOUR, 0);
        search_dt.set(Calendar.MINUTE, 0);
        search_dt.set(Calendar.SECOND, 0);
        
        Date endDt = search_dt.getTime();
        search_dt.add(Calendar.DATE, -1);
        Date startDt = search_dt.getTime();


        List<MemberInfoVO> list = mMapper.selectMemberInfo(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = member_path+"/log_data/linkClick"+c.getTimeInMillis()+".txt";
        BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                new File(src)
            )
        );
        for(MemberInfoVO data:list){
            writer.write(data.toString());
            writer.newLine();
        }
        writer.close();

        String dest = member_path+"/batch_data/linkClick"+c.getTimeInMillis()+".txt";
        File srcFile = new File(src);
        File destFile = new File(dest);
        srcFile.renameTo(destFile);
    }
}
