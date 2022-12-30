package com.enix.controller;

import com.enix.data.FamilyIPData;
import com.enix.service.TokenService;
import com.enix.vo.ResponseJsonObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.enix.enumeration.ResponseMessageEnum.*;

@RestController
@RequestMapping("/raspberry")
public class RaspberryPiController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private DateTimeFormatter dateTimeFormatter;
    private final FamilyIPData familyIPData;
    private final TokenService tokenService;

    @Autowired
    public RaspberryPiController(FamilyIPData familyIPData,
                                 TokenService tokenService,
                                 DateTimeFormatter dateTimeFormatter) {
        this.familyIPData = familyIPData;
        this.tokenService = tokenService;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    /**
     * 更新 IP 資料
     *
     * @param json Json Data
     * @return 處理結果
     */
    @PostMapping("/updateIPInfo")
    public JsonObject updateIPInfo(@RequestBody JsonObject json) {

        JsonElement token = json.get("token");
        if (token == null || !tokenService.isTokenValid(token.getAsString())) {
            return new ResponseJsonObject(FORBIDDEN).build();
        }

        JsonElement ipData = json.get("ip");
        JsonElement updateTime = json.get("updateTime");
        JsonElement userName = json.get("userName");

        if (ipData == null || updateTime == null) {
            return new ResponseJsonObject(MISSING_PARAMETERS).build();
        }

        LocalDateTime userUpdateTime = dateTimeFormatter.parse(updateTime.getAsString(), LocalDateTime::from);
        familyIPData.setIp(ipData.getAsString());
        familyIPData.setUpdateUser(userName != null ? userName.getAsString() : "未知使用者");
        familyIPData.setUpdateTime(userUpdateTime);

        log.info("IPRecord is {}", ipData.getAsString());
        return new ResponseJsonObject(SUCCESS).build();
    }

    //    @GetMapping("/getIPData")
    public String showFamilyIPData() {

        return familyIPData.toString();
    }

}
