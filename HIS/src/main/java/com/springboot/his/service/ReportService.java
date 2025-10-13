package com.springboot.his.service;

import com.springboot.his.entity.Report;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<Report> findAll();

    List<Report> searchReports(String nationId, LocalDate startDate, LocalDate endDate, String type, String status, String keyword);


}
