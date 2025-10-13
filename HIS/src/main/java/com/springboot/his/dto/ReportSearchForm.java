package com.springboot.his.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReportSearchForm {
    private String nationalId;   // 身分證
    private LocalDate startDate; // 開始日期
    private LocalDate endDate;   // 結束日期
    private String reportType;   // 非必填
    private String status;       // 非必填
    private String keyword;      // 非必填

    public ReportSearchForm() {}

    public ReportSearchForm(String nationalId, LocalDate startDate, LocalDate endDate, String reportType, String status, String keyword) {
        this.nationalId = nationalId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reportType = reportType;
        this.status = status;
        this.keyword = keyword;
    }

    // getters and setters
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
