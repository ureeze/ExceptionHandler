package com.example.errorhandler.validation;

import com.example.errorhandler.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.MessageFormat;

@Component
@RequiredArgsConstructor
public class EmailDuplicationValidator implements ConstraintValidator<EmailUnique, String> {
    private final MemberRepository memberRepository;

    @Override
    public void initialize(EmailUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext ctx) {
        final boolean isExistEmail = memberRepository.existsByEmail(email);
        if (isExistEmail) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(
                            MessageFormat.format("{0} 이미 존재하는 이메일 입니다.", email))
                    .addConstraintViolation();
        }
        return !isExistEmail ;
    }
}
