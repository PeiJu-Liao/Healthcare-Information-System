package com.springboot.his.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "report_urine_test")
public class ReportUrineTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long urineId; // PK

    @OneToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report; // FK

    public Long getUrineId() {
        return urineId;
    }

    public Report getReport() {
        return report;
    }

    public String getProtein() {
        return protein;
    }

    public String getOccultBlood() {
        return occultBlood;
    }

    private String protein; // 尿蛋白 (陰性/陽性/數值)
    private String occultBlood; // 尿潛血 (陰性/陽性/數值)

    public ReportUrineTest() {}
}