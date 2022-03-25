package com.ohtae.crypto.Crypto.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoVO {
    private Integer mi_seq;
    private String mi_id;
    private String mi_email;
    private String mi_phone;
    private String mi_name;
    private String mi_birth;
    private Date mi_reg_dt;
    private String mi_image;
    private Integer mi_status;

    @Override
    public String toString(){
        return mi_seq+","+mi_id+","+mi_email+","+mi_phone+","+mi_name+","+mi_birth+","+mi_reg_dt+","+mi_image+","+mi_status;
    }
}

