package com.darsgateway.service;

import com.darsgateway.commons.RestResultListVO;
import com.darsgateway.model.ViewInfoModel;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ViewInfoServiceImpl implements ViewInfoService{

    @Override
    public RestResultListVO<ViewInfoModel> getViewInfoList(HttpSession session, ViewInfoModel vo) throws Exception {
        RestResultListVO<ViewInfoModel> viewInfoList = new RestResultListVO<>();
        List<ViewInfoModel> list = new ArrayList<>();
        ViewInfoModel response = new ViewInfoModel();
        try {
            log.info("INDEX : {}", vo.getIndex());
            log.info("CALL-ID : {}", vo.getCallId());
            log.info("SERVICE ID : {}", vo.getServiceId());
            log.info("SERVICE NAME : {}", vo.getServiceName());
            log.info("OpenedAt : {}", vo.getOpenedAt());
            viewInfoList.setResultStatus("0");
            viewInfoList.setMessageCode("0");
            viewInfoList.setUserName("0");
            response.setIndex(0);
            response.setServiceId("0");
            response.setServiceName("0");
            response.setCallId("0");
            response.setOpenedAt("0");
            list.add(response);
            viewInfoList.setResult(list);
        } catch (Exception e) {
            viewInfoList.setResultStatus("F");
            viewInfoList.setMessageCode("insertError");
        }
        return viewInfoList;
    }
}
