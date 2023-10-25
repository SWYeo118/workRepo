package com.darsgateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewInfoModel {

    private int index;
    private String callId;
    private String serviceId;
    private String serviceName;
    private String openedAt;
}
