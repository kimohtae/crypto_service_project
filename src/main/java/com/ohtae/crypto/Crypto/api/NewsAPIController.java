package com.ohtae.crypto.Crypto.api;

import com.ohtae.crypto.Crypto.data.NewsInfoVO;
import com.ohtae.crypto.Crypto.mapper.NewsMapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsAPIController {
    @Autowired NewsMapper nMapper;

    @PutMapping("/crawling")
    public String putNewsInfo()throws Exception{
        String FirstPage="https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=100";
        String[] OtherPage={
            "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=101",
            "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=102",
            "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=103",
            "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=104",
            "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=105"
        };
        Document doc = Jsoup.connect(FirstPage).get();
        Elements link = doc.select(".cluster_foot_more");
        for(Element i:link){ 
            insert("https://news.naver.com/"+i.attr("href"),6); 
        }
        int k=0;
        for(String i:OtherPage){
            k++;
            doc = Jsoup.connect(i).get();
            link = doc.select(".cluster_head_topic a");
            for(Element j:link){ 
                insert("https://news.naver.com/"+j.attr("href"),k); 
            }
        }


        return "정보가 입력되었습니다.";
    }

    public void insert(String address,int k)throws Exception{
        Document doc = Jsoup.connect(address).get();
        Elements linkUrl = doc.select("ul.type06_headline dl dt:eq(1) a");
        String Urls="";
        for(Element i:linkUrl){
            Urls += i.attr("href")+",,";
        }
        String[] Url = Urls.split(",,");

        for(String i:Url){
            if(!(i.equals(" ") && i==null)){
                NewsInfoVO news = new NewsInfoVO();
                doc = Jsoup.connect(""+ i +"").get();
                String Title = doc.select("#articleTitle").text();
                String Image = doc.select(".end_photo_org img").attr("src");
                String Content = doc.select("#articleBodyContents").text();
                int start = Content.indexOf(". ");
                Content = Content.substring(start+1);
                String Press = doc.select(".article_footer a:eq(0)").text();
                Press = Press.split(" ")[0];

                news.setNpi_title(Title);
                news.setNpi_content(Content);
                news.setNpi_image(Image);
                news.setNpi_company(Press);
                news.setNpi_link(i);
                news.setNpi_nti_seq(k);
                nMapper.insertNewsInfo(news);
            }
        }
    }
}
