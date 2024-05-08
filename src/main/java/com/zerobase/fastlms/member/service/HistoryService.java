package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.member.dto.HistoryDto;
import com.zerobase.fastlms.member.entity.History;
import com.zerobase.fastlms.member.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public void saveHistory(String userId, String clientIp, String userAgent) {

        History history = History.builder()
                .userId(userId)
                .connectIp(clientIp)
                .connectUserAgent(userAgent)
                .loginTime(LocalDateTime.now())
                .build();

        historyRepository.save(history);
    }

    public List<HistoryDto> getUserHistory(String userId) {
        return historyRepository.findAllByUserId(userId).stream()
                .map(HistoryDto::fromEntity)
                .collect(Collectors.toList());
    }
}
