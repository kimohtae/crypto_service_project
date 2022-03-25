package com.ohtae.crypto.Crypto.data;

import java.util.Date;

import lombok.Data;

@Data
public class NewsRecommendVO {
    private Integer nri_seq;
    private Integer nri_mi_seq;
    private Integer nri_npi_seq;
    private Double nri_score;
    private Date nri_reg_dt;
}
