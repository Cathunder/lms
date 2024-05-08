package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByUserId(String userId);
}
