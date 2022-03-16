package com.ohtae.crypto.Crypto.data;

import java.util.Date;

import lombok.Data;

@Data
public class NewsInfoVO {
    private Integer npi_seq;
    private String npi_title;
    private Integer npi_view;
    private Date npi_reg_dt;
    private String npi_link;
    private String npi_image;
    private String npi_company;
    private String npi_content;
    private Integer npi_nti_seq;
    private Integer npi_like;
}
