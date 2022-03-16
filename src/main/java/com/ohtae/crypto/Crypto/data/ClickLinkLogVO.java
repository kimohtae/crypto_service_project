package com.ohtae.crypto.Crypto.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClickLinkLogVO {
    private Integer cll_seq;
    private Integer cll_mi_seq;
    private Integer cll_npi_seq;
    private Date cll_reg_dt;

    @Override
    public String toString(){
        return cll_seq+","+cll_mi_seq+","+cll_npi_seq+","+cll_reg_dt;
    }
}
