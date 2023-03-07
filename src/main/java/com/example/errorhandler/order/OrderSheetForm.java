package com.example.errorhandler.order;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrderSheetFormValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderSheetForm {
    String message() default "주문 정보가 올바르지 않습니다.";   // 유효성 검사 false 시 반환할 기본 메시지

    Class<?>[] groups() default {}; // 어노테이션을 적용할 특정 상황 (예를 들어 특정 Class 시 어노테이션 동작)

    Class<? extends Payload>[] payload() default {};    // 심각한 정도 등 메타 데이터를 정의해 넣을 수 있다.
}
