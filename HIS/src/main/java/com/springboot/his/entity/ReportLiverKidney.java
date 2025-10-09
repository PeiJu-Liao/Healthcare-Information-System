package com.springboot.his.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "report_liver_kidney")
public class ReportLiverKidney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lk_Id; // PK

    @OneToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report; // FK

    private Double alt; // 血清丙胺酸轉胺酶 (ALT)
    private Double creatinine; // 肌酸酐
    private Double cholesterolTotal; // 總膽固醇
    private Double triglyceride; // 三酸甘油酯
    private Double hdl; // 高密度脂蛋白膽固醇
    private Double fastingGlucose; //空腹血糖

    public Long getLk_Id() {
        return lk_Id;
    }

    public void setLk_Id(Long lk_Id) {
        this.lk_Id = lk_Id;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Double getAlt() {
        return alt;
    }

    public void setAlt(Double alt) {
        this.alt = alt;
    }

    public Double getCreatinine() {
        return creatinine;
    }

    public void setCreatinine(Double creatinine) {
        this.creatinine = creatinine;
    }

    public Double getCholesterolTotal() {
        return cholesterolTotal;
    }

    public void setCholesterolTotal(Double cholesterolTotal) {
        this.cholesterolTotal = cholesterolTotal;
    }

    public Double getTriglyceride() {
        return triglyceride;
    }

    public void setTriglyceride(Double triglyceride) {
        this.triglyceride = triglyceride;
    }

    public Double getHdl() {
        return hdl;
    }

    public void setHdl(Double hdl) {
        this.hdl = hdl;
    }

    public Double getFastingGlucose() {
        return fastingGlucose;
    }

    public void setFastingGlucose(Double fastingGlucose) {
        this.fastingGlucose = fastingGlucose;
    }

    public ReportLiverKidney() {}
}
