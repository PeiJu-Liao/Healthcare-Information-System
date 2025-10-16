package com.springboot.his.controller;

import com.itextpdf.text.DocumentException;
import com.springboot.his.entity.Report;
import com.springboot.his.service.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/report")
@SessionAttributes("searchResult")
public class ReportDownloadController {

    @Autowired
    private PDFGeneratorService pdfGeneratorService;

    public void PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    /**
     * 下載報告檔案
     */
    @GetMapping("/download")
    // ResponseEntity是Spring Framework 用於封裝和回傳整個HTTP 響應的類別，讓開發者能靈活地控制HTTP 回應的狀態碼、Header 和Body。
    // <byte[]> 適合傳遞二進制的數據，例如圖片、PDF、或其他文件
    public ResponseEntity<byte[]> downloadReport(@RequestParam("reportId") Long reportId,
                                                 @ModelAttribute("searchResult") List<Report> searchResults, SessionStatus sessionStatus) throws IOException, DocumentException {

        System.out.println("SearchResults>>" + searchResults);

        // 防呆(就流程來講，有查詢結果才會顯示下載按鈕)
        if (searchResults == null || searchResults.isEmpty()) {
            sessionStatus.setComplete(); // 清除session中名為searchResult的屬性
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("查詢結果不存在或已過期，請重新查詢。".getBytes());
        }


//         找出要下載的那筆
//        Report report = searchResults.stream()
//                .filter(r -> r.getReportId().equals(reportId))
//                .findFirst()
//                .orElse(null);

        // 只會有一筆結果，就直接拿第一筆
        Report report = searchResults.get(0);

        if (report == null) {
            sessionStatus.setComplete(); // 清除session中名為searchResult的屬性
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("找不到指定的報告".getBytes());
        }

        // 生成 PDF
        byte[] pdfBytes = pdfGeneratorService.generateReportPDF(report);

        // Content-Disposition 標頭是HTTP響應中的一個字段，用於指示伺服器將內容以何種形式展示給用戶端瀏覽器。
        // 主要有兩種模式：以網頁形式內嵌顯示 (inline)，或是作為附件下載到本地 (attachment)。
        return ResponseEntity.ok() // statusCode 200
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RPT_" + reportId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}