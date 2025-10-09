package com.springboot.his.entity;


import jakarta.persistence.*;

import java.time.LocalDate;


// 每一份健檢都有一個「報告總表」，裡面連結細項
@Entity
@Table(name="report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="report_id")
    private Long reportId; // PK

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient; // FK

//  身體基本檢查與問診
    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private ReportBasicCheck basicCheck;

//  尿液檢查
    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private ReportUrineTest urineTest;

//  血液檢查
    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
    private ReportBloodTest bloodTest;

//  肝腎功能檢查
    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
    private ReportLiverKidney liverKidney;

//  胸部X光
    @OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
    private ReportXray xray;

    private LocalDate reportDate; // 報告日期
    private String reportType; // 報告類型
    private String reportStatus; // 報告狀態
    private String summary; // 關鍵字搜尋
    private LocalDate createdAt; // 建立時間
    private LocalDate updatedAt; // 更新時間

    // Constructors
    public Report() {}

    // Getters and Setters
    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ReportBasicCheck getBasicCheck() {
        return basicCheck;
    }

    public void setBasicCheck(ReportBasicCheck basicCheck) {
        this.basicCheck = basicCheck;
    }

    public ReportUrineTest getUrineTest() {
        return urineTest;
    }

    public void setUrineTest(ReportUrineTest urineTest) {
        this.urineTest = urineTest;
    }

    public ReportBloodTest getBloodTest() {
        return bloodTest;
    }

    public void setBloodTest(ReportBloodTest bloodTest) {
        this.bloodTest = bloodTest;
    }

    public ReportLiverKidney getLiverKidney() {
        return liverKidney;
    }

    public void setLiverKidney(ReportLiverKidney liverKidney) {
        this.liverKidney = liverKidney;
    }

    public ReportXray getXray() {
        return xray;
    }

    public void setXray(ReportXray xray) {
        this.xray = xray;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", patient=" + patient + // db
                ", basicCheck=" + basicCheck + //db
                ", urineTest=" + urineTest + //db
                ", bloodTest=" + bloodTest + //db
                ", liverKidney=" + liverKidney + //db
                ", xray=" + xray + // db
                ", reportDate=" + reportDate +
                ", reportType='" + reportType + '\'' +
                ", reportStatus='" + reportStatus + '\'' +
                ", summary='" + summary + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

