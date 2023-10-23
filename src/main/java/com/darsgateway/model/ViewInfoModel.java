package com.darsgateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewInfoModel {

    private int index;
    private String callId;
    private String serviceId;
    private String serviceName;
    private String openedAt;
}
