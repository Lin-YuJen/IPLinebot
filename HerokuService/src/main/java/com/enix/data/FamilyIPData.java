package com.enix.data;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * 僅用於儲存 IP 位置資料用
 */
@Component
public class FamilyIPData {

    private String ip;
    private String updateUser;
    private LocalDateTime updateTime;
    private Instant systemUpdateTime;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        systemUpdateTime = Instant.now();
        this.ip = ip;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        systemUpdateTime = Instant.now();
        this.updateUser = updateUser;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        systemUpdateTime = Instant.now();
        this.updateTime = updateTime;
    }

    public Instant getSystemUpdateTime() {
        return systemUpdateTime;
    }

    @Override
    public String toString() {
        return "FamilyIPData{" +
                "ip='" + ip + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
