package com.springboot.his.service;

import com.lowagie.text.Font;
import com.springboot.his.entity.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import org.springframework.stereotype.Service;

@Service
public class PDFGeneratorService {

    public byte[] generateReportPDF(Report report) throws IOException {
        // 適用於需要對二進位資料進行操作(在記憶體中處理二進位資料)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            Document document = new Document();
            // 是 iText 函式庫中用於建立 PDF 寫入器實例的靜態方法。
            // 它會將一個 Document 物件與一個 OutputStream 連結起來，讓你能將內容寫入到 PDF 檔案中。
            PdfWriter.getInstance(document, baos);
            document.open(); // 開啟文件

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle.setSize(20);

            // 標題
            Paragraph title = new Paragraph("Health Examination Report", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 25));
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title); // 寫入文件
            document.add(new com.itextpdf.text.Chunk("\n"));

            // 病人基本資料
            Patient patient = report.getPatient();
            if (patient != null) {
                document.add(new Paragraph("Name - " + patient.getFirstName() + " " + patient.getLastName()));
                document.add(new Paragraph("National ID - " + patient.getNationalId()));
                document.add(new Paragraph("Birth Dat - " + patient.getBirthDate()));
                document.add(new Paragraph("Gender - " + patient.getGender()));
                document.add(new Paragraph("Report Date - " + report.getReportDate()));
            }
            document.add(new com.itextpdf.text.Chunk("\n"));

            // 血液檢查內容
            ReportBloodTest blood = report.getBloodTest();
            if (blood != null) {
                document.add(new Paragraph("Result of the Blood Examine：", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20)));
                document.add(new Paragraph("WbcCount - " + blood.getWbcCount()));
                document.add(new Paragraph("Hemoglobin - " + blood.getHemoglobin()));
            }
            document.add(new com.itextpdf.text.Chunk("\n"));

            // 肝腎檢查內容
            ReportLiverKidney liver = report.getLiverKidney();
            if (liver != null) {
                document.add(new Paragraph("Result of the Liver Examine：", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20)));
                document.add(new Paragraph("getAlt - " + liver.getAlt()));
                document.add(new Paragraph("Creatinine - " + liver.getCreatinine()));
                document.add(new Paragraph("CholesterolTotal - " + liver.getCholesterolTotal()));
                document.add(new Paragraph("Triglyceride - " + liver.getTriglyceride()));
                document.add(new Paragraph("Hdl - " + liver.getHdl()));
                document.add(new Paragraph("FastingGlucose - " + liver.getFastingGlucose()));
            }
            document.add(new com.itextpdf.text.Chunk("\n"));

            // 尿液檢查內容
            ReportUrineTest urine = report.getUrineTest();
            if (urine != null) {
                document.add(new Paragraph("Result of the Urine Examine：", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20)));
                document.add(new Paragraph("Urine Protein - " + urine.getProtein()));
                document.add(new Paragraph("Urine OccultBlood - " + urine.getOccultBlood()));
            }
            document.add(new com.itextpdf.text.Chunk("\n"));

            // 尿液檢查內容
            ReportXray xray = report.getXray();
            if (xray != null) {
                document.add(new Paragraph("Result of the X-Ray Examine：", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20)));
                document.add(new Paragraph("X-Ray Result - " + xray.getXrayResult()));
            }
            document.add(new com.itextpdf.text.Chunk("\n"));

            // 健檢整體備註
            document.add(new Paragraph("Summary of the Whole Examine：", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20)));
            document.add(new Paragraph("Summary - " + report.getSummary()));

            document.close(); // 關閉文件
        } catch (com.itextpdf.text.DocumentException e ) {
            throw new IOException("PDF Generated Failed: " + e.getMessage());
        }

        // 用toByteArray() 方法一次性取出
        return baos.toByteArray();
    }
}
