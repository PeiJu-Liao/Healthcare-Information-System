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
}
