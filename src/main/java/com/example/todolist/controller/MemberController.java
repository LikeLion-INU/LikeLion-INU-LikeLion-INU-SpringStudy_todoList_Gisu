package com.example.todolist.controller;

import com.example.todolist.common.ApiResponse;


import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.member.MemberResponseDTO;
import com.example.todolist.service.member.MemberServiceImpl;
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
    public ResponseEntity<?> join(@RequestBody MemberRequestDTO.MemberJoinDTO memberJoinDTO){
        log.info("[MemberController] join");
        MemberResponseDTO.MemberJoinDTO result = memberService.join(memberJoinDTO);
        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "member join success", result));
    }
    //2.로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDTO memberdto){
        log.info("[MemberController] login");
        return ResponseEntity.ok("login: " + memberdto.toString());
    }

    //3.모든회원조회
    @PostMapping("/findall")
    public ResponseEntity<?> findall(@RequestBody MemberRequestDTO memberdto){
        log.info("[MemberController] findall");
        return ResponseEntity.ok("findall: " + memberdto.toString());
    }

    //4.특정회원조회
    @PostMapping("/findone")
    public ResponseEntity<?> findone(@RequestBody MemberRequestDTO memberdto){
        log.info("[MemberController] findone");
        return ResponseEntity.ok("findone: " + memberdto.toString());
    }

    //5.정보 수정
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody MemberRequestDTO memberdto){
        log.info("[MemberController] update");
        return ResponseEntity.ok("update: " + memberdto.toString());
    }

    //6.로그아웃

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody MemberRequestDTO memberdto){
        log.info("[MemberController] logout");
        return ResponseEntity.ok("logout: " + memberdto.toString());
    }

    //7.회원 탈퇴
    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody MemberRequestDTO memberdto){
        log.info("[MemberController] delete");
        return ResponseEntity.ok("delete: " + memberdto.toString());
    }






}
