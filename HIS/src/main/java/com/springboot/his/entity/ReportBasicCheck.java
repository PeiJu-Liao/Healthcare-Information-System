package com.springboot.his.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "report_basic_check")
public class ReportBasicCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basicCheckId; // PK

    @OneToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report; // FK

    private Double height;
    private Double weight;

    private Double waist;
    private Double visionLeft;
    private Double visionRight;
    private Boolean colorBlind;
    private String hearingLeft;
    private String hearingRight;
    private String bloodPressure;

    @Column(columnDefinition = "TEXT")
    private String history;

    public ReportBasicCheck() {}


    public Long getBasicCheckId() {
        return basicCheckId;
    }

    public void setBasicCheckId(Long basicCheckId) {
        this.basicCheckId = basicCheckId;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getVisionLeft() {
        return visionLeft;
    }

    public void setVisionLeft(Double visionLeft) {
        this.visionLeft = visionLeft;
    }

    public Double getVisionRight() {
        return visionRight;
    }

    public void setVisionRight(Double visionRight) {
        this.visionRight = visionRight;
    }

    public Boolean getColorBlind() {
        return colorBlind;
    }

    public void setColorBlind(Boolean colorBlind) {
        this.colorBlind = colorBlind;
    }

    public String getHearingLeft() {
        return hearingLeft;
    }

    public void setHearingLeft(String hearingLeft) {
        this.hearingLeft = hearingLeft;
    }

    public String getHearingRight() {
        return hearingRight;
    }

    public void setHearingRight(String hearingRight) {
        this.hearingRight = hearingRight;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

}
