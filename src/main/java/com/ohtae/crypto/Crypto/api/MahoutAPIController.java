package com.ohtae.crypto.Crypto.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.ohtae.crypto.Crypto.data.NewsRecommendVO;
import com.ohtae.crypto.Crypto.mapper.MahoutMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MahoutAPIController {
    @Value("${recommend.output.path}")String recommend_output_path;
    @Autowired MahoutMapper mhMapper;
    
    @GetMapping("/api/news/recommend")
    public String getRecommendData()throws Exception{
        String[] fileName = {"part-r-00000"};
        for(String i:fileName){
            File recommend_file = new File(recommend_output_path+i);
            List<NewsRecommendVO> member_list = new ArrayList<NewsRecommendVO>();
            BufferedReader reader = new BufferedReader(new FileReader(recommend_file));
            String line = null;
            while((line = reader.readLine()) != null){
                int mi_seq = Integer.parseInt(line.split("\\[")[0].trim());
                String ori_others = line.split("\\[")[1].split("\\]")[0];
                String[] ori_other = ori_others.split(",");
                for(String j:ori_other){
                    NewsRecommendVO data = new NewsRecommendVO();
                    String npi_seq = j.split(":")[0];
                    String score = j.split(":")[1];
                    data.setNri_mi_seq(mi_seq);
                    data.setNri_npi_seq(Integer.parseInt(npi_seq));
                    data.setNri_score(Double.parseDouble(score));
                    member_list.add(data);
                }
            }
            reader.close();
            mhMapper.insertNewsRecommendList(member_list);
        }
        return "추천 정보를 가져왔습니다.";
    }
}
