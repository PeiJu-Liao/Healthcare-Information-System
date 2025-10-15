package com.springboot.his.validation;

import com.springboot.his.dto.ReportSearchForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateRangeValidator  implements ConstraintValidator<ValidDateRange, ReportSearchForm> {
    @Override
    public boolean isValid(ReportSearchForm form, ConstraintValidatorContext context) {
        if (form == null) return true;

        LocalDate start = form.getStartDate();
        LocalDate end = form.getEndDate();

        // 只要有任一個日期是空的，就不驗證這條規則
        if (start == null || end == null) return true;

        // 開始日期不能晚於結束日期
        if (start.isAfter(end)) {
            // 關鍵：告訴 Validation 錯誤屬於哪個欄位
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("開始日期不可晚於結束日期")
                    .addPropertyNode("endDate")  // 想讓錯誤顯示在指定欄位上
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
