package com.ohtae.crypto.Crypto.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsInfoVO{

    private Integer npi_seq;
    private String npi_title;
    private Integer npi_view;
    private Date npi_reg_dt;
    private String npi_link;
    private String npi_image;
    private String npi_company;
    private String npi_content;
    private Integer npi_nti_seq;

    
    @Override
    public String toString(){
        return npi_seq+"|"+npi_title+"|"+npi_view+"|"+npi_reg_dt+"|"+npi_link+"|"+npi_image+"|"+npi_company+"|"+npi_nti_seq+"|"+npi_content;
    }
}
