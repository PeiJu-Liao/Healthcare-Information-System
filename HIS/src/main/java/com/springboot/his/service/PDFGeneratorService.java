package com.springboot.his.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import com.springboot.his.entity.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.lowagie.text.*;
import org.springframework.stereotype.Service;

@Service
public class PDFGeneratorService {

    public static byte[] generateReportPDF(Report report) throws IOException, DocumentException {
        // 適用於需要對二進位資料進行操作(在記憶體中處理二進位資料)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Document document = new Document();
        // 是 iText 函式庫中用於建立 PDF 寫入器實例的靜態方法。
        // 它會將一個 Document 物件與一個 OutputStream 連結起來，讓你能將內容寫入到 PDF 檔案中。
        PdfWriter.getInstance(document, baos);
        document.open(); // 開啟文件

        // 標題
        Font fontTitle = FontFactory.getFont("MSung-Light", "UniCNS-UCS2-H", BaseFont.NOT_EMBEDDED, 26, Font.BOLD);
        Phrase title = new Phrase("個人健康檢查報告", fontTitle);
        document.add(title); // 寫入文件

        // 內建的HELVETICA是不支援中文的
        // 用法：FontFactory.getFont(字型名稱, 編碼, 是否嵌入, 字體大小, 字型樣式)
        Font fontHeader = FontFactory.getFont("MSung-Light", "UniCNS-UCS2-H", BaseFont.NOT_EMBEDDED, 12, Font.BOLD);
        Font fontBody = FontFactory.getFont("MSung-Light", "UniCNS-UCS2-H", BaseFont.NOT_EMBEDDED, 11, Font.NORMAL);

        // 建立一個2欄的表格
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100); // 寬度100%
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        table.setWidths(new float[]{1, 4}); // 欄寬比例（可依需求調整）

        // ==== 基本資料 ====
        Map<String, String> checkItems = new LinkedHashMap<>();
        Patient patient = report.getPatient();
        checkItems.put("姓名", patient.getFirstName() + " " + patient.getLastName());
        checkItems.put("身分證字號", patient.getNationalId());
        checkItems.put("出生日期", patient.getBirthDate());
        checkItems.put("性別", patient.getGender());
        // ==== 使用 for-each 迴圈塞入 ====
        for (Map.Entry<String, String> entry : checkItems.entrySet()) {
            addTabelCell(table, entry.getKey(), fontBody, Element.ALIGN_LEFT, Color.lightGray);
            addTabelCell(table, entry.getValue(), fontBody, Element.ALIGN_LEFT, Color.white);
        }

        // ==== 基本檢查內容TABLE ====
        PdfPTable basicTable = new PdfPTable(2);
        basicTable.setWidthPercentage(100); // 寬度100%
        basicTable.setSpacingBefore(10f);
        basicTable.setSpacingAfter(10f);
        basicTable.setWidths(new float[]{2, 3}); // 欄寬比例（可依需求調整）
        // ==== 加上表頭 ====
        addTabelCell(basicTable, "基本檢查項目", fontHeader, Element.ALIGN_CENTER, Color.lightGray);
        addTabelCell(basicTable, "檢核值", fontHeader, Element.ALIGN_CENTER, Color.lightGray);
        Map<String, String> basicCheckList = new LinkedHashMap<>();
        // 肝腎檢查
        ReportBasicCheck basicReport = report.getBasicCheck();
        basicCheckList.put("身高", basicReport.getHeight().toString());
        basicCheckList.put("體重", basicReport.getWeight().toString());
        basicCheckList.put("腰圍", basicReport.getWaist().toString());
        basicCheckList.put("左眼視力", basicReport.getVisionLeft().toString());
        basicCheckList.put("右眼視力", basicReport.getVisionRight().toString());
        basicCheckList.put("是否為色盲", basicReport.getColorBlind().toString());
        basicCheckList.put("左耳聽力", basicReport.getHearingLeft().toString());
        basicCheckList.put("右耳聽力", basicReport.getHearingRight().toString());
        basicCheckList.put("血壓", basicReport.getBloodPressure().toString());
        for (Map.Entry<String, String> entry : basicCheckList.entrySet()) {
            addTabelCell(basicTable, entry.getKey(), fontBody, Element.ALIGN_LEFT, Color.white);
            addTabelCell(basicTable, entry.getValue(), fontBody, Element.ALIGN_LEFT, Color.white);
        }

        // ==== 血液檢查TABLE ====
        PdfPTable bloodTable = new PdfPTable(2);
        bloodTable.setWidthPercentage(100); // 寬度100%
        bloodTable.setSpacingBefore(10f);
        bloodTable.setSpacingAfter(10f);
        bloodTable.setWidths(new float[]{2, 3}); // 欄寬比例（可依需求調整）
        // ==== 加上表頭 ====
        addTabelCell(bloodTable, "血液檢查項目", fontHeader, Element.ALIGN_CENTER, Color.lightGray);
        addTabelCell(bloodTable, "檢核值", fontHeader, Element.ALIGN_CENTER, Color.lightGray);
        Map<String, String> bloodCheckList = new LinkedHashMap<>();
        // 血液檢查
        ReportBloodTest blood = report.getBloodTest();
        bloodCheckList.put("白血球數", blood.getWbcCount().toString());
        bloodCheckList.put("血色素", blood.getHemoglobin().toString());
        for (Map.Entry<String, String> entry : bloodCheckList.entrySet()) {
            addTabelCell(bloodTable, entry.getKey(), fontBody, Element.ALIGN_LEFT, Color.white);
            addTabelCell(bloodTable, entry.getValue(), fontBody, Element.ALIGN_LEFT, Color.white);
        }

        // ==== 肝腎檢查內容TABLE ====
        PdfPTable liverTable = new PdfPTable(2);
        liverTable.setWidthPercentage(100); // 寬度100%
        liverTable.setSpacingBefore(10f);
        liverTable.setSpacingAfter(10f);
        liverTable.setWidths(new float[]{2, 3}); // 欄寬比例（可依需求調整）
        // ==== 加上表頭 ====
        addTabelCell(liverTable, "肝腎檢查項目", fontHeader, Element.ALIGN_CENTER, Color.lightGray);
        addTabelCell(liverTable, "檢核值", fontHeader, Element.ALIGN_CENTER, Color.lightGray);
        Map<String, String> liverCheckList = new LinkedHashMap<>();
        // 肝腎檢查
        ReportLiverKidney liver = report.getLiverKidney();
        liverCheckList.put("血清丙胺酸轉胺酶 (ALT)", liver.getAlt().toString());
        liverCheckList.put("肌酸酐", liver.getCreatinine().toString());
        liverCheckList.put("總膽固醇", liver.getCholesterolTotal().toString());
        liverCheckList.put("三酸甘油酯", liver.getTriglyceride().toString());
        liverCheckList.put("高密度脂蛋白膽固醇", liver.getHdl().toString());
        liverCheckList.put("空腹血糖", liver.getFastingGlucose().toString());
        for (Map.Entry<String, String> entry : liverCheckList.entrySet()) {
            addTabelCell(liverTable, entry.getKey(), fontBody, Element.ALIGN_LEFT, Color.white);
            addTabelCell(liverTable, entry.getValue(), fontBody, Element.ALIGN_LEFT, Color.white);
        }

        // ==== 尿液檢查內容TABLE ====
        PdfPTable urineTable = new PdfPTable(2);
        urineTable.setWidthPercentage(100); // 寬度100%
        urineTable.setSpacingBefore(10f);
        urineTable.setSpacingAfter(10f);
        urineTable.setWidths(new float[]{2, 3}); // 欄寬比例（可依需求調整）
        // ==== 加上表頭 ====
        addTabelCell(urineTable, "尿液檢查項目", fontHeader, Element.ALIGN_CENTER, Color.lightGray);
        addTabelCell(urineTable, "檢核值", fontHeader, Element.ALIGN_CENTER, Color.lightGray);
        Map<String, String> urineCheckList = new LinkedHashMap<>();
        // 肝腎檢查
        ReportUrineTest urine = report.getUrineTest();
        urineCheckList.put("尿蛋白 (陰性/陽性/數值)", urine.getProtein().toString());
        urineCheckList.put("尿潛血 (陰性/陽性/數值)", urine.getOccultBlood().toString());
        for (Map.Entry<String, String> entry : urineCheckList.entrySet()) {
            addTabelCell(urineTable, entry.getKey(), fontBody, Element.ALIGN_LEFT, Color.white);
            addTabelCell(urineTable, entry.getValue(), fontBody, Element.ALIGN_LEFT, Color.white);
        }

        document.add(table);
        document.add(basicTable);
        document.add(bloodTable);
        document.add(liverTable);
        document.add(urineTable);
        document.close();

        System.out.println("✅ PDF 已成功生成");


//            // 病人基本資料
//            Patient patient = report.getPatient();
//            if (patient != null) {
//                document.add(new Paragraph("Name - " + patient.getFirstName() + " " + patient.getLastName()));
//                document.add(new Paragraph("National ID - " + patient.getNationalId()));
//                document.add(new Paragraph("Birth Dat - " + patient.getBirthDate()));
//                document.add(new Paragraph("Gender - " + patient.getGender()));
//                document.add(new Paragraph("Report Date - " + report.getReportDate()));
//            }
//            document.add(new com.itextpdf.text.Chunk("\n"));
//
//            // 血液檢查內容
//            ReportBloodTest blood = report.getBloodTest();
//            if (blood != null) {
//                document.add(new Paragraph("Result of the Blood Examine：", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20)));
//                document.add(new Paragraph("WbcCount - " + blood.getWbcCount()));
//                document.add(new Paragraph("Hemoglobin - " + blood.getHemoglobin()));
//            }
//            document.add(new com.itextpdf.text.Chunk("\n"));
//
//            // 肝腎檢查內容
//            ReportLiverKidney liver = report.getLiverKidney();
//            if (liver != null) {
//                document.add(new Paragraph("Result of the Liver Examine：", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20)));
//                document.add(new Paragraph("getAlt - " + liver.getAlt()));
//                document.add(new Paragraph("Creatinine - " + liver.getCreatinine()));
//                document.add(new Paragraph("CholesterolTotal - " + liver.getCholesterolTotal()));
//                document.add(new Paragraph("Triglyceride - " + liver.getTriglyceride()));
//                document.add(new Paragraph("Hdl - " + liver.getHdl()));
//                document.add(new Paragraph("FastingGlucose - " + liver.getFastingGlucose()));
//            }
//            document.add(new com.itextpdf.text.Chunk("\n"));
//
//            // 尿液檢查內容
//            ReportUrineTest urine = report.getUrineTest();
//            if (urine != null) {
//                document.add(new Paragraph("Result of the Urine Examine：", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20)));
//                document.add(new Paragraph("Urine Protein - " + urine.getProtein()));
//                document.add(new Paragraph("Urine OccultBlood - " + urine.getOccultBlood()));
//            }
//            document.add(new com.itextpdf.text.Chunk("\n"));
//
//            // 尿液檢查內容
//            ReportXray xray = report.getXray();
//            if (xray != null) {
//                document.add(new Paragraph("Result of the X-Ray Examine：", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20)));
//                document.add(new Paragraph("X-Ray Result - " + xray.getXrayResult()));
//            }
//            document.add(new com.itextpdf.text.Chunk("\n"));
//
//            // 健檢整體備註
//            document.add(new Paragraph("Summary of the Whole Examine：", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 20)));
//            document.add(new Paragraph("Summary - " + report.getSummary()));
//
//            document.close(); // 關閉文件

        // 用toByteArray() 方法一次性取出
        return baos.toByteArray();
    }

    private static void addTabelCell(PdfPTable table, String text, Font font, int align, Color color) {
        Phrase content = new Phrase(text, font);
        PdfPCell cell = new PdfPCell(content);
        cell.setHorizontalAlignment(align);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(color);
        cell.setPadding(6f);
        table.addCell(cell);
    }
}
