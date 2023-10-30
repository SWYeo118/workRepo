package com.darsgateway.NonUse;

import lombok.Data;
import java.util.List;

@Data
public class RestResultListVO<T> {

    private String resultStatus;

    private String messageCode;

    private String userName;

    private List<T> result;

    private int totalCount;
}
