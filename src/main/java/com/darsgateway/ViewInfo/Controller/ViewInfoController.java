package com.darsgateway.ViewInfo.Controller;

import com.darsgateway.ViewInfo.Entity.ViewInfoEntity;
import com.darsgateway.ViewInfo.VO.ViewResultVO;
import com.darsgateway.ViewInfo.Model.ViewInfoModel;
import com.darsgateway.ViewInfo.Repository.ViewInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ViewInfoController {

    private final ViewInfoRepository viewInfoRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @PostMapping("/darsgw/view")
    public ViewResultVO viewDars(@RequestBody ViewInfoModel viewInfoModel) {
        ViewInfoEntity tntDarsCallsEntity = new ViewInfoEntity();
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
            viewInfoRepository.save(tntDarsCallsEntity);

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
}