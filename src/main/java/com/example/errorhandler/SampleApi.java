package com.example.errorhandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


    public static class Member {
        private String name;
        private String email;

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
