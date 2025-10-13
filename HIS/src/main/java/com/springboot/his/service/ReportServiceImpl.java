package com.springboot.his.service;

import com.springboot.his.dao.ReportRepository;
import com.springboot.his.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> findAll() {
        return List.of();
    }

    @Override
    public List<Report> searchReports(String nationalId,
                                      LocalDate startDate,
                                      LocalDate endDate,
                                      String type,
                                      String status,
                                      String keyword) {
        if (type != null && type.trim().isEmpty()) type = null;
//        if (keyword != null && keyword.trim().isEmpty()) keyword = null;
//        if (status != null && status.trim().isEmpty()) status = null;
        return reportRepository.findReports(nationalId, startDate, endDate, type, status, keyword);
    }

}
