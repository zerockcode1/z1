package org.zerock.z1.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.z1.member.dto.MemberDTO;
import org.zerock.z1.member.entity.Member;
import org.zerock.z1.member.repository.MemberRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void register(MemberDTO memberDTO) {

        log.info(memberDTO);

        Member member = dtoToEntity(memberDTO);

        log.info(member);

        memberRepository.save(member);
    }
}
