package org.zerock.z1.member.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.z1.member.entity.Member;
import org.zerock.z1.member.entity.MemberRole;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {


    @EntityGraph(attributePaths = "memberRoleSet")
    @Query("select m from Member m join m.memberRoleSet role where role = :memberRole")
    List<Member> findMemberByRole(MemberRole memberRole);

}
