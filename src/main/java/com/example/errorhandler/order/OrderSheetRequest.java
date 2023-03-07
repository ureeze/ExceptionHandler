package com.example.errorhandler.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@OrderSheetForm
public class OrderSheetRequest {
    @Min(1)
    private BigDecimal price;

    @NotNull
    @Valid
    private Payment payment;

    @NotNull
    @Valid
    private Address address;

    @NoArgsConstructor
    @Getter
    @ToString
    public static class Payment {
        @NotNull
        private PaymentMethod paymentMethod;
        private Account account;
        private Card card;

        @JsonIgnore
        public boolean hasPaymentInfo() {
            return account != null && card != null;
        }
    }


    @NoArgsConstructor
    @Getter
    @ToString
    public static class Address{
        @NotEmpty
        private String city;
        @NotEmpty
        private String state;
        @NotEmpty
        private String zipCode;
    }

    @NoArgsConstructor
    @Getter
    @ToString
    public static class Account{
        private String number;
        private String bankCode;
        private String holder;
    }

    @NoArgsConstructor
    @Getter
    @ToString
    public static class Card{
        private String number;
        private String brand;
        private String csv;

    }
}
