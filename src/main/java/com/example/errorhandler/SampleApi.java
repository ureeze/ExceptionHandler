package com.example.errorhandler;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/sample")
public class SampleApi {
    @GetMapping
    public Member test(@RequestParam String value) {
        if (value.equals("value")) {
            throw new IllegalArgumentException("System Error Java 8 Version........");
        } else if (value.equals("none")) {
            throw new IllegalStateException("System Error Java 8 Version........");
        } else {
            return new Member("yun", "yun@asd.com");
        }
    }

    @PostMapping
    public void test2(@RequestBody @Valid Member member) {

    }


    public static class Member {
        @NotEmpty
        private String name;
        @Email
        private String email;

        public Member() {

        }

        public Member(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}
