package com.darsgateway.controller;

import com.darsgateway.commons.RestResultListVO;
import com.darsgateway.commons.ViewResultVO;
import com.darsgateway.entity.StTntCallsDailyEntity;
import com.darsgateway.model.ViewInfoModel;
import com.darsgateway.repository.ViewInfoRepository;
import com.darsgateway.service.ViewInfoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ViewInfoController {

    private ViewInfoService viewInfoServiceImpl;
    private ViewInfoRepository viewInfoRepository;

    @Autowired
    public ViewInfoController(ViewInfoService viewInfoServiceImpl, ViewInfoRepository viewInfoRepository) {
        this.viewInfoServiceImpl = viewInfoServiceImpl;
        this.viewInfoRepository = viewInfoRepository;
    }

    @RequestMapping("/darsgw/view")
    public RestResultListVO<ViewInfoModel> getViewInfo (HttpSession session, @RequestBody ViewInfoModel vo) throws Exception {
        return viewInfoServiceImpl.getViewInfoList(session, vo);
    }

    @GetMapping("/find/{id}")
    public Optional<StTntCallsDailyEntity> findView(@ModelAttribute ViewInfoModel viewInfoModel, @PathVariable Long id) {
        return viewInfoRepository.findById(id);
    }
}
