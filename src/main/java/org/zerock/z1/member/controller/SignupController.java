package org.zerock.z1.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.z1.member.dto.MemberDTO;
import org.zerock.z1.member.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
public class SignupController {

    private final MemberService memberService;

    @PostMapping( value ="/api/signup", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> signup(@RequestBody MemberDTO memberDTO){

        log.info("signup...............");

        log.info("----------------" + memberDTO);

        memberService.register(memberDTO);

        Map<String, String> result = new HashMap<>();
        result.put("result", "success");

        return ResponseEntity.ok(result);

    }

}
