package org.zerock.z1.member.entity;


import lombok.*;
import org.zerock.z1.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member  extends BaseEntity {

    @Id
    private String mid;

    private String mpw;

    @Column(nullable = false)
    private String mname;

    @Column(nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<MemberRole> memberRoleSet = new HashSet<>();

    public void addRole(MemberRole memberRole){
        memberRoleSet.add(memberRole);
    }
}
