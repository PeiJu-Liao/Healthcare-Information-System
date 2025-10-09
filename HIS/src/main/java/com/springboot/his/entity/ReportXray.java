package com.springboot.his.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "report_xray")
public class ReportXray {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long xrayId;

    @OneToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report; // FK

    public String getXrayResult() {
        return xrayResult;
    }

    public Report getReport() {
        return report;
    }

    public Long getXrayId() {
        return xrayId;
    }

    @Column(columnDefinition = "TEXT")
    private String xrayResult;

    public ReportXray() {}
}