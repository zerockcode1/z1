package org.zerock.z1.member.service;

import org.zerock.z1.member.dto.MemberDTO;
import org.zerock.z1.member.entity.Member;
import org.zerock.z1.member.entity.MemberRole;

import java.util.Arrays;

public interface MemberService {


    void register(MemberDTO memberDTO);


    default Member dtoToEntity(MemberDTO memberDTO){

        Member member = Member.builder()
                .mid(memberDTO.getMid())
                .mpw(memberDTO.getMpw())
                .mname(memberDTO.getMname())
                .email(memberDTO.getEmail())
                .build();

        Arrays.stream(memberDTO.getRoles()).forEach(roleStr -> {
            MemberRole role = MemberRole.valueOf(roleStr);
            member.addRole(role);
        });
        return member;
    }

}
