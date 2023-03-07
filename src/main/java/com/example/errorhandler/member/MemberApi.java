package com.example.errorhandler.member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/members")
public class MemberApi {

    @PostMapping
    public void create(@RequestBody @Valid SignUpRequest dto) {
        // 실제 회원가입 로직 ...
    }
}
