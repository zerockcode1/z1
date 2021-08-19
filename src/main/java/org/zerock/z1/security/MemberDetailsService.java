package org.zerock.z1.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.z1.member.entity.Member;
import org.zerock.z1.member.repository.MemberRepository;
import org.zerock.z1.security.dto.MemberAuthDTO;

import java.util.Optional;
import java.util.stream.Collectors;


@Log4j2
@RequiredArgsConstructor
@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("------------------------------------------------");
        log.info("------------------------------------------------");
        log.info("---------loadUserByUsername-----" + username);
        log.info("------------------------------------------------");


        Optional<Member> result = memberRepository.findById(username);

        Member member = result.orElseThrow(() -> new UsernameNotFoundException("NULL"));

        return new MemberAuthDTO(member.getMid(),member.getMpw(),
                member.getMemberRoleSet().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList())
                );
    }

}
