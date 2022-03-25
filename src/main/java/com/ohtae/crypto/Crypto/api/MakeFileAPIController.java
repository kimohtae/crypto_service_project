package com.ohtae.crypto.Crypto.api;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MakeFileAPIController {
    @Autowired LogMapper lMapper;
    @Autowired NewsMapper nMapper;
    @Autowired MemberMapper mMapper;
    @Value("${history.link.click.path}")String link_click_path;
    @Value("${history.news.path}")String news_path;
    @Value("${history.member.path}")String member_path;

    @GetMapping("/link/click")
    public String makeLinkClickInfoFile(@RequestParam String start, @RequestParam String end) throws Exception{
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
        String src = link_click_path+"/log_data/linkClick"+c.getTimeInMillis()+".txt";
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

        String dest = link_click_path+"/batch_data/linkClick"+c.getTimeInMillis()+".txt";
        File srcFile = new File(src);
        File destFile = new File(dest);
        srcFile.renameTo(destFile);

        return "링크 클릭 로그파일 생성 완료";
    }

    @GetMapping("/news/click")
    public String makeNewsInfoFile(@RequestParam String start, @RequestParam String end) throws Exception{
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


        List<NewsInfoVO> list = nMapper.selectNewsInfo(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = news_path+"/log_data/news"+c.getTimeInMillis()+".txt";
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

        String dest = news_path+"/batch_data/news"+c.getTimeInMillis()+".txt";
        File srcFile = new File(src);
        File destFile = new File(dest);
        srcFile.renameTo(destFile);

        return "뉴스 로그파일 생성 완료";
    }

    @GetMapping("/member/click")
    public String makeMemberInfoFile(@RequestParam String start, @RequestParam String end) throws Exception{
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


        List<MemberInfoVO> list = mMapper.selectMemberInfo(startDt, endDt);
        Calendar c = Calendar.getInstance();
        String src = member_path+"/log_data/member"+c.getTimeInMillis()+".txt";
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

        String dest = member_path+"/batch_data/member"+c.getTimeInMillis()+".txt";
        File srcFile = new File(src);
        File destFile = new File(dest);
        srcFile.renameTo(destFile);

        return "멤버 로그파일 생성 완료";
    }
}
