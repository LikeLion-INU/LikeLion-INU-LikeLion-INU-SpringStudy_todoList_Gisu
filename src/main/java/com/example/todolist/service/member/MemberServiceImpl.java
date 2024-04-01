package com.example.todolist.service.member;


import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.member.MemberResponseDTO;
import com.example.todolist.entity.member.MemberEntity;
import com.example.todolist.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
// 생성자 자동 생성
// 의존성 주입 방식 -> 1. field 주입(this)-주입받는객체변화o 2. setter 주입 3.생성자주입(autowired)-주입객체변동x,반드시객체주입
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService  {
    private final MemberRepository memberRepository;
//        {this.memberRepository = memberRepository;}
    @Override
    @Transactional
    public MemberResponseDTO.MemberJoinDTO join(MemberRequestDTO.MemberJoinDTO memberJoinDTO){
        log.info("[MemberServiceImpl] join");
        MemberEntity memberEntity = memberJoinDTO.toEntity();
        Optional<MemberEntity> optionalFindMember = memberRepository.findByMemberEmail(memberEntity.getMemberEmail());

        if(optionalFindMember.isPresent()) {
            // 중복 이메일 발생
            log.info("[ERROR] 중복 이메일 입니다.");
            return null;
        }

        memberRepository.save(memberEntity); // 중복 이메일이 없다면 DB에 저장하기.
        return new MemberResponseDTO.MemberJoinDTO(memberEntity);


    }





}

