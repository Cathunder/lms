package com.zerobase.fastlms.member.dto;

import com.zerobase.fastlms.member.entity.History;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryDto {
    private String userId;
    private LocalDateTime loginDt;
    private String connectIp;
    private String connectUserAgent;

    public static HistoryDto fromEntity(History history) {
        return HistoryDto.builder()
                .userId(history.getUserId())
                .loginDt(history.getLoginTime())
                .connectIp(history.getConnectIp())
                .connectUserAgent(history.getConnectUserAgent())
                .build();
    }

    public String getLoginDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        return loginDt != null ? loginDt.format(formatter) : "";
    }
}
