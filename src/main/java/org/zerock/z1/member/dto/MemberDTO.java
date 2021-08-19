package org.zerock.z1.member.dto;


import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Data
public class MemberDTO {

    private String mid, mpw, mname, email;

    private String[] roles;
}
