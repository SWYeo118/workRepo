package com.darsgateway.controller;

import com.darsgateway.commons.ViewResultVO;
import com.darsgateway.entity.TntDarsCallsEntity;
import com.darsgateway.model.ViewInfoModel;
import com.darsgateway.repository.TntDarsCallsRepository;
import com.darsgateway.repository.ViewInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ViewInfoController {

    private ViewInfoRepository viewInfoRepository;
    private TntDarsCallsRepository tntDarsCallsRepository;
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    public ViewInfoController(ViewInfoRepository viewInfoRepository, TntDarsCallsRepository tntDarsCallsRepository, RedisTemplate<String, String> redisTemplate) {
        this.viewInfoRepository = viewInfoRepository;
        this.tntDarsCallsRepository = tntDarsCallsRepository;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/darsgw/view")
    public ViewResultVO saveDars(@RequestBody ViewInfoModel viewInfoModel) {
        TntDarsCallsEntity tntDarsCallsEntity = new TntDarsCallsEntity();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            tntDarsCallsEntity.setCount(viewInfoModel.getIndex());
            tntDarsCallsEntity.setCall_uuid(viewInfoModel.getCallId());
            tntDarsCallsEntity.setServiceId(viewInfoModel.getServiceId());
            tntDarsCallsEntity.setServiceName(viewInfoModel.getServiceName());
            LocalDateTime parse = LocalDateTime.parse(viewInfoModel.getOpenedAt(), dateTimeFormatter);
            String parsed = parse.format(dtf);
            tntDarsCallsEntity.setCreateTime(parsed);
            tntDarsCallsRepository.save(tntDarsCallsEntity);

            ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(viewInfoModel.getCallId(), parsed);

            ViewResultVO viewResultVO = new ViewResultVO();
            viewResultVO.setCode(200);
            viewResultVO.setMsg("성공하였습니다.");
            return viewResultVO;
        } catch (Exception e) {
            e.printStackTrace();
            ViewResultVO viewResultVO = new ViewResultVO();
            viewResultVO.setCode(300);
            viewResultVO.setMsg("실패하였습니다.");
            return viewResultVO;
        }
    }

    @PostMapping("/darsgw/view/check")
    public ViewResultVO checkDars(@RequestBody ViewInfoModel viewInfoModel) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String key = viewInfoModel.getCallId();
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String value = valueOperations.get(key);
        LocalDateTime now = LocalDateTime.parse(viewInfoModel.getOpenedAt(), dateTimeFormatter);
        LocalDateTime savedTime = LocalDateTime.parse(value, dateTimeFormatter2);
        LocalDateTime timeNow = now.truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime timeSaved = savedTime.truncatedTo(ChronoUnit.MINUTES);
        int compareResult = timeNow.compareTo(timeSaved);
        if (compareResult >= 5) {
            ViewResultVO viewResultVO = new ViewResultVO();
            viewResultVO.setCode(200);
            viewResultVO.setMsg("성공하였습니다.");
            return viewResultVO;
        } else {
            ViewResultVO viewResultVO = new ViewResultVO();
            viewResultVO.setCode(300);
            viewResultVO.setMsg("실패하였습니다.");
            return viewResultVO;
        }
    }
}
