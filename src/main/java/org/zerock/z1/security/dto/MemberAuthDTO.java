package org.zerock.z1.security.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.zerock.z1.member.dto.MemberDTO;

import java.util.Collection;

@ToString
@Getter
public class MemberAuthDTO extends User {

    private String password;

    public MemberAuthDTO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.password = password;
    }
}
