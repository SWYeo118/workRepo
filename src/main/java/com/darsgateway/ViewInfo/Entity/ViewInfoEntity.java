package com.darsgateway.ViewInfo.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "tnt_dars_calls")
public class ViewInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int count;

    @Column
    private String call_uuid;

    @Column
    private String serviceId;

    @Column
    private String serviceName;

    @Column
    private String createTime;
}
