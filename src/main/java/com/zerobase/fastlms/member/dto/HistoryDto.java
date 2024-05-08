package com.zerobase.fastlms.member.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryDto {
    private String userId;
    private LocalDateTime loginTime;
    private String connectIp;
    private String connectUserAgent;
}
