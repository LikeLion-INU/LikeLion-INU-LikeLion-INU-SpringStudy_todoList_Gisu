package com.example.todolist.controller;

import com.example.todolist.common.ApiResponse;


import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.member.MemberResponseDTO;
import com.example.todolist.service.member.MemberServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;



//@Controller --> 왜 레스트컨트롤러,,? -> 500타임리프오류

@RequiredArgsConstructor
@Slf4j //logging
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberServiceImpl memberService;


    //1.회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody MemberRequestDTO.MemberJoinDTO memberJoinDTO) {
        log.info("[MemberController] join");
        MemberResponseDTO.MemberJoinDTO result = memberService.join(memberJoinDTO);
        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member join success", result));
    }

    //2.로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDTO.MemberLoginDTO memberLoginDTO, HttpSession httpSession) {

        log.info("[MemberController] login");
        MemberResponseDTO.MemberLoginDTO result = memberService.login(memberLoginDTO);

        httpSession.setAttribute("memberId", result.getId());

        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member login success", result));
    }

    //3.모든회원조회
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        log.info("[MemberController] findAll");
        MemberResponseDTO.MemberFindAllDTO result = memberService.findAll();
        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member findAll success", result));
    }


    //4.특정회원조회
    @GetMapping("/findOne")
    public ResponseEntity<?> findOne(HttpSession httpSession) {

        log.info("[MemberController] findOne");
        Long memberId = (Long) httpSession.getAttribute("memberId");
        MemberResponseDTO.MemberFindOneDTO result = memberService.findOne(memberId);

        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member findOne success", result));
    }

    //5.정보 수정
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody MemberRequestDTO.MemberUpdateDTO memberUpdateDTO, HttpSession httpSession) {
        log.info("[MemberController] update");
        Long memberId = (Long) httpSession.getAttribute("memberId");
        MemberResponseDTO.MemberUpdateDTO result = memberService.update(memberId, memberUpdateDTO);
        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member update success", result));
    }

    //6.로그아웃

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession httpSession, HttpServletRequest request) {

        log.info("[MemberController] logout");
        MemberResponseDTO.MemberLogoutDTO result = memberService.logout(request, (Long) httpSession.getAttribute("memberId"));

        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member logout success", result));
    }

    //7.회원 탈퇴
    @PostMapping("/delete")
    public ResponseEntity<?> delete(HttpSession httpSession) {

        log.info("[MemberController] delete");
        String result = memberService.delete((Long) httpSession.getAttribute("memberId"));
        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member delete success", result));
    }

}







