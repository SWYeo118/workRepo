package com.darsgateway.service;


import com.darsgateway.commons.RestResultListVO;
import com.darsgateway.model.ViewInfoModel;
import jakarta.servlet.http.HttpSession;

public interface ViewInfoService {
    public RestResultListVO<ViewInfoModel> getViewInfoList(HttpSession session, ViewInfoModel vo) throws Exception;

}
