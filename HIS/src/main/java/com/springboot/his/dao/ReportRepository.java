package com.springboot.his.dao;
import com.springboot.his.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    // 新增方法來進行參數篩選
    @Query("SELECT r FROM Report r " +
            "JOIN r.patient p " +
            "WHERE (:nationalId IS NULL OR p.nationalId = :nationalId) " +
            "AND (:startDate IS NULL OR r.reportDate >= :startDate) " +
            "AND (:endDate IS NULL OR r.reportDate <= :endDate) " +
            "AND (:type IS NULL OR r.reportType = :type) " )
//            "AND (:status IS NULL OR r.reportStatus = :status) " +
//            "AND (:keyword IS NULL OR LOWER(r.summary) LIKE LOWER(CONCAT('%', :keyword, '%')))")
        List<Report> findReports(@Param("nationalId") String nationalId,
                                 @Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate,
                                 @Param("type") String type,
                                 @Param("status") String status,
                                @Param("keyword") String keyword);
}
