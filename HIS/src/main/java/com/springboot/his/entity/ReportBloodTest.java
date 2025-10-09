package com.springboot.his.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "report_blood_test")
public class ReportBloodTest {
    public Long getBloodId() {
        return bloodId;
    }

    public void setBloodId(Long bloodId) {
        this.bloodId = bloodId;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Double getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(Double hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public Integer getWbcCount() {
        return wbcCount;
    }

    public void setWbcCount(Integer wbcCount) {
        this.wbcCount = wbcCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bloodId; // PK

    @OneToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report; // FK

    private Double hemoglobin; // 血色素
    private Integer wbcCount; //  白血球數

    public ReportBloodTest() {}
}
