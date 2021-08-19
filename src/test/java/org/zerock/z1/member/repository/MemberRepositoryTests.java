package org.zerock.z1.member.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.z1.member.entity.Member;
import org.zerock.z1.member.entity.MemberRole;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testInsert1() {

        IntStream.range(1,10).forEach(i -> {

            Member member = Member.builder()
                    .mid("user"+i)
                    .mpw(passwordEncoder.encode("1111"))
                    .mname("사용자"+i)
                    .email("user"+i+"@aaa.com")
                    .build();

            member.addRole(MemberRole.A);
            member.addRole(MemberRole.B);
            member.addRole(MemberRole.C);

            memberRepository.save(member);
        });
    }

    @Test
    public void testReadById() {

        log.info(memberRepository.findById("user1").get());

    }

    @Test
    public void testReadByRole() {

        log.info(memberRepository.findMemberByRole(MemberRole.A));

    }

}
