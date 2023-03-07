package com.example.errorhandler.order;

import org.springframework.util.ObjectUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class OrderSheetFormValidator implements ConstraintValidator<OrderSheetForm, OrderSheetRequest> {
//    @Override
//    public void initialize(OrderSheetForm constraintAnnotation) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
//    }

    @Override
    public boolean isValid(OrderSheetRequest request, ConstraintValidatorContext context) {
        int invalidCount = 0;
        if (request.getPayment().getAccount() == null & request.getPayment().getCard() == null) {
            addConstraintViolation(context, "카드 정보 혹은 계정 정보는 필수이다.", "payment");
            invalidCount += 1;
        }

        if (request.getPayment().getPaymentMethod() == PaymentMethod.CARD) {
            final OrderSheetRequest.Card card = request.getPayment().getCard();

            if (card == null) {
                addConstraintViolation(context, "카드 필수입니다.", "payment", "card");
                invalidCount += 1;
            } else {
                if (ObjectUtils.isEmpty(card.getBrand())) {
                    addConstraintViolation(context, "카드 브랜드는 필수입니다.", "payment", "card", "brand");
                    invalidCount += 1;
                }
                if (ObjectUtils.isEmpty(card.getCsv())) {
                    addConstraintViolation(context, "CSV 값은 필수 입니다.", "payment", "card", "csv");
                    invalidCount += 1;
                }
                if (ObjectUtils.isEmpty(card.getNumber())) {
                    addConstraintViolation(context, "카드 번호는 필수 입니다.", "payment", "card", "number");
                    invalidCount += 1;
                }
            }
        }

        if (request.getPayment().getPaymentMethod() == PaymentMethod.BANK_TRANSFER) {
            final OrderSheetRequest.Account account = request.getPayment().getAccount();

            if (account == null) {
                addConstraintViolation(context, "계좌정보는 필수입니다.", "payment", "account");
                invalidCount += 1;
            } else {
                if (ObjectUtils.isEmpty(account.getBankCode())) {
                    addConstraintViolation(context, "은행코드는 필수입니다.", "payment", "account", "bankCode");
                    invalidCount += 1;
                }
                if (ObjectUtils.isEmpty(account.getHolder())) {
                    addConstraintViolation(context, "계좌주는 값은 필수 입니다.", "payment", "account", "holder");
                    invalidCount += 1;
                }
                if (ObjectUtils.isEmpty(account.getNumber())) {
                    addConstraintViolation(context, "계좌번호는 필수값입니다.", "payment", "account", "number");
                    invalidCount += 1;
                }
            }
        }

        return invalidCount == 0;
    }

    private void addConstraintViolation(
            ConstraintValidatorContext context,
            String errorMessage,
            String firstNode
    ) {
        context.disableDefaultConstraintViolation(); // 기본 메시지 비활성화
        context.buildConstraintViolationWithTemplate(errorMessage)  // 새로운 메시지 추가
                .addPropertyNode(firstNode)
                .addConstraintViolation();
    }

    private void addConstraintViolation(
            final ConstraintValidatorContext context,
            final String errorMessage,
            final String firstNode,
            final String secondNode
    ) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addPropertyNode(firstNode)
                .addPropertyNode(secondNode)
                .addConstraintViolation();
    }

    private void addConstraintViolation(
            final ConstraintValidatorContext context,
            final String errorMessage,
            final String firstNode,
            final String secondNode,
            final String thirdNode
    ) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addPropertyNode(firstNode)
                .addPropertyNode(secondNode)
                .addPropertyNode(thirdNode)
                .addConstraintViolation();
    }
}
