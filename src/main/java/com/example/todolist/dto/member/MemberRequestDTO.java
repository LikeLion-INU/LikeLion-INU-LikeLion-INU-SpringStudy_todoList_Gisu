package com.example.todolist.dto.member;


import com.example.todolist.entity.member.MemberEntity;
import lombok.Data;



@Data
public class MemberRequestDTO {




    //1.회원가입
    @Data
    public static class MemberJoinDTO {
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        //setter x -> 메소드 실행시  Entity에서 값을 넣어서 객체를 생성 후 반환
        //setter o -> 메소드 실행시 entity객체 생성후  객체에 값 주입
        public MemberEntity toEntity() { // DTO를 Member 엔티티로 변환하는 메소드
            return new MemberEntity(this.memberEmail, this.memberPassword, this.memberName, this.memberPhone);
        }
    }

    // 로그인
    @Data
    public static class MemberLoginDTO {
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호

    }

    // 회원 수정
    @Data
    public static class MemberUpdateDTO {
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberEntity toEntity() { // DTO를 Member 엔티티로 변환하는 메소드
            return new MemberEntity(this.memberEmail, this.memberPassword, this.memberName, this.memberPhone);
        }
    }
}
