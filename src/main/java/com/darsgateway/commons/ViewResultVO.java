package com.darsgateway.commons;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ViewResultVO {
    private Long id;
    private Long direction;
    private String intt;
    private String ars;
    private String ivr_lrdv_cd;
    private String ivr_lrdv_nm;
    private String ivr_mddv_cd;
    private String ivr_mddv_nm;
    private String date_ymd;
    private Long weekday;
    private Long business_day;
    private Long count;
    private Long complete_count;
    private Long transfer_count;
    private Long dars_transfer_count;
    private Long hangup_count;
    private Date created_at;
}
