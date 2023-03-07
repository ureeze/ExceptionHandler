package com.example.errorhandler;

import com.example.errorhandler.member.Member;
import com.example.errorhandler.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memberRepository.save(new Member("yun@test.com"));
        memberRepository.save(new Member("test@test.com"));
    }
}
