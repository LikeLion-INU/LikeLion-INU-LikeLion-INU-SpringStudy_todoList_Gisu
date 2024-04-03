package com.example.todolist.service.member;


import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.dto.member.MemberResponseDTO;
import com.example.todolist.entity.member.MemberEntity;
import com.example.todolist.repository.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
// 생성자 자동 생성
// 의존성 주입 방식 -> 1. field 주입(this)-주입받는객체변화o 2. setter 주입 3.생성자주입(autowired)-주입객체변동x,반드시객체주입
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    //        {this.memberRepository = memberRepository;}
    @Override
    @Transactional
    //회원가입
    public MemberResponseDTO.MemberJoinDTO join(MemberRequestDTO.MemberJoinDTO memberJoinDTO) {
        log.info("[MemberServiceImpl] join");
        MemberEntity memberEntity = memberJoinDTO.toEntity();
        Optional<MemberEntity> optionalFindMember = memberRepository.findByMemberEmail(memberEntity.getMemberEmail());
        if (optionalFindMember.isPresent()) {
            // 중복 이메일 발생
            log.info("[ERROR] 중복 이메일 입니다.");
            return null;
        }
        memberRepository.save(memberEntity); // 중복 이메일이 없다면 DB에 저장하기.
        return new MemberResponseDTO.MemberJoinDTO(memberEntity);
    }

    //2.로그인
    @Override
    @Transactional
    public MemberResponseDTO.MemberLoginDTO login(MemberRequestDTO.MemberLoginDTO memberLoginDTO) {

        log.info("[MemberServiceImpl] login");
        Optional<MemberEntity> optionalFindMember = memberRepository.findByMemberEmail(memberLoginDTO.getMemberEmail()); // DB에서 회원 조회

        if (optionalFindMember.isEmpty()) {
            // 존재하지 않은 이메일
            log.info("[ERROR] 존재하지 않은 이메일 입니다.");
            return null;
        } else if (!Objects.equals(memberLoginDTO.getMemberPassword(), optionalFindMember.get().getMemberPassword())) {
            // 틀린 비밀번호
            return null;
        }
        return new MemberResponseDTO.MemberLoginDTO(optionalFindMember.get());
    }

    public MemberResponseDTO.MemberLogoutDTO logout(HttpServletRequest request, Long memberId) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }

        Optional<MemberEntity> optionalFindMember = memberRepository.findById(memberId); // DB에서 회원 조회

        if (optionalFindMember.isEmpty()) {
            // 존재하지 않은 이메일
            log.info("[ERROR] 존재하지 않은 회원 입니다.");
            return null;
        }
        return new MemberResponseDTO.MemberLogoutDTO(optionalFindMember.get());
    }

    @Override
    @Transactional
    public String delete(Long memberId) {

        log.info("[MemberServiceImpl] delete");
        Optional<MemberEntity> optionalFindMember = memberRepository.findById(memberId);

        if (optionalFindMember.isEmpty()) {
            // 존재하지 않은 이메일
            log.info("[ERROR] 존재하지 않은 회원 입니다.");
        }

        //todoRepository.deleteByMemberId(memberId);
        memberRepository.deleteById(memberId); // DB에서 회원 삭제

        return "SUCCESS";
    }

    @Override
    @Transactional
    public MemberResponseDTO.MemberUpdateDTO update(Long memberId, MemberRequestDTO.MemberUpdateDTO memberUpdateDTO) {
        log.info("[MemberServiceImpl] update");
        Optional<MemberEntity> optionalFindMember = memberRepository.findById(memberId); // DB에서 회원 조회

        if (!optionalFindMember.isPresent()) {
            // 존재하지 않은 이메일
            log.info("[ERROR] 존재하지 않은 이메일 입니다.");
        }

        // 회원 수정 과정 진행
        optionalFindMember.get().memberUpdate(memberUpdateDTO);

        return new MemberResponseDTO.MemberUpdateDTO(optionalFindMember.get());
    }

    @Override
    public MemberResponseDTO.MemberFindOneDTO findOne(Long memberId) {

        log.info("[MemberServiceImpl] findOne");

        Optional<MemberEntity> optionalFindMember = memberRepository.findById(memberId); // DB에서 회원 조회

        if (optionalFindMember.isEmpty()) {
            // 존재하지 않은 이메일
            log.info("[ERROR] 존재하지 않은 회원 입니다.");
        }

        return new MemberResponseDTO.MemberFindOneDTO(optionalFindMember.get());
    }

    @Override
    public MemberResponseDTO.MemberFindAllDTO findAll() {

        log.info("[MemberServiceImpl] findAll");
        List<MemberEntity> memberList = memberRepository.findAll();

        List<MemberResponseDTO.MemberFindOneDTO> memberFindOneDTOList = memberList.stream()
                .map(MemberResponseDTO.MemberFindOneDTO::new)
                .collect(Collectors.toList());

        return new MemberResponseDTO.MemberFindAllDTO(memberFindOneDTOList);
    }
}




















