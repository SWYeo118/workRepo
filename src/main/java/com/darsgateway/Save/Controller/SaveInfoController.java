package com.darsgateway.Save.Controller;

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

@RestController
@Slf4j
@RequiredArgsConstructor
public class SaveInfoController {
    private final RedisTemplate<String, String> redisTemplate;
    @PostMapping("/darsgw/save")
    public ViewResultVO saveDars(@RequestBody ViewInfoModel viewInfoModel) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime parse = LocalDateTime.parse(viewInfoModel.getOpenedAt(), dateTimeFormatter);
            String parsed = parse.format(dtf);
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