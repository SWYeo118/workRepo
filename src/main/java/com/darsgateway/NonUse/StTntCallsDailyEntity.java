package com.darsgateway.NonUse;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "st_tnt_calls_daily")
public class StTntCallsDailyEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Long direction;

    @Column
    private String intt;

    @Column
    private String ars;

    @Column
    private String ivr_lrdv_cd;

    @Column
    private String ivr_lrdv_nm;

    @Column
    private String ivr_mddv_cd;

    @Column
    private String ivr_mddv_nm;

    @Column
    private String date_ymd;

    @Column
    private Long weekday;

    @Column
    private Long business_day;

    @Column
    private Long count;

    @Column
    private Long complete_count;

    @Column
    private Long transfer_count;

    @Column
    private Long dars_transfer_count;

    @Column
    private Long hangup_count;

    @Column
    private Date created_at;


}
