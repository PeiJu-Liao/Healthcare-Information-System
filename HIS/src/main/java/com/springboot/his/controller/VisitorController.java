package com.springboot.his.controller;
import com.springboot.his.dto.ReportSearchForm;
import com.springboot.his.entity.Report;
import com.springboot.his.service.ReportService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("healthcare")
@SessionAttributes("searchResult")
public class VisitorController {

    private final ReportService reportService;

    public VisitorController(ReportService reportService) {
        this.reportService = reportService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
//        System.out.println("Target: " + binder.getTarget());
    }


    @GetMapping("/searchForm")
    public String showSearchForm(Model model) {
        model.addAttribute("reportSearchForm", new ReportSearchForm());
        return "visitor/healthcare-search";
    }

    @PostMapping("/search")
    public String saveEmployee(@Valid @ModelAttribute("reportSearchForm") ReportSearchForm searchForm,
                               BindingResult theBindingResult,
                               Model model
                               ) {

        if (theBindingResult.hasErrors()){
            theBindingResult.getAllErrors().forEach(error -> {
                System.out.println("errors:" + error.getDefaultMessage());
            });
            return "visitor/healthcare-search";
        }

        // 取得查詢參數
        String nationalId = searchForm.getNationalId();
        LocalDate startDate = searchForm.getStartDate();
        LocalDate endDate = searchForm.getEndDate();
        String type = searchForm.getReportType();
        String status = searchForm.getStatus();
        String keyword = searchForm.getKeyword();
//        System.out.println("nationalId>>" + nationalId);
//        System.out.println("startDate>>" + startDate);
//        System.out.println("endDate>>" + endDate);
//        System.out.println("type>>" + type);
//        System.out.println("status>>" + status);
//        System.out.println("keyword>>" + keyword);

        // 呼叫 service 做篩選
        List<Report> results = reportService.searchReports(nationalId, startDate, endDate, type, status, keyword);
        System.out.println("results" + results.toString());

        // 將data回傳到畫面(結果儲存在session中)
        model.addAttribute("searchList", searchForm); // 篩選條件
        model.addAttribute("searchResult", results); // 篩選結果

        // use a redirect to prevent duplicate submissions
        return "visitor/healthcare-list";
    }


}
