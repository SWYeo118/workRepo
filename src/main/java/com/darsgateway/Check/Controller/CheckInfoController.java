package com.darsgateway.Check.Controller;

import com.darsgateway.ViewInfo.VO.ViewResultVO;
import com.darsgateway.ViewInfo.Model.ViewInfoModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CheckInfoController {

    private final RedisTemplate<String, String> redisTemplate;

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