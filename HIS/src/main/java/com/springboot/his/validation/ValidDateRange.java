package com.springboot.his.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateRangeValidator.class)
@Target({ElementType.TYPE}) // 說明該客制驗證可以套用在哪些元件上
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {

    // 自定義裡面的內容(元資料Metadata)
    String message() default "開始日期不可晚於結束日期";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
