package com.example.todolist.repository.member;


import com.example.todolist.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //JPA 가 자동구현x -> 직접 구현
    //JPA가 메소드 의도 파악 - 명시적 쿼리 구현할필요없이 자동구현
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
//이메일로 회원 정보 조회 select * from member_table where member_email=?
}