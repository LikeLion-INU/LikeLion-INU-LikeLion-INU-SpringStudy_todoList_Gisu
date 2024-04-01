package com.example.todolist.dto.member;
import com.example.todolist.entity.member.MemberEntity;
import lombok.Data;

import java.util.List;

@Data
public class MemberResponseDTO {
    public static class MemberJoinDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberJoinDTO(MemberEntity memberEntity) {
            this.Id = memberEntity.getId();
            this.memberEmail = memberEntity.getMemberEmail();
            this.memberPassword = memberEntity.getMemberPassword();
            this.memberName = memberEntity.getMemberName();
            this.memberPhone = memberEntity.getMemberPhone();
        }
    }

    // 로그인
    @Data
    public static class MemberLoginDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberLoginDTO(MemberEntity memberEntity) {
            this.Id = memberEntity.getId();
            this.memberEmail = memberEntity.getMemberEmail();
            this.memberPassword = memberEntity.getMemberPassword();
            this.memberName = memberEntity.getMemberName();
            this.memberPhone = memberEntity.getMemberPhone();
        }
    }
    // 로그아웃
    @Data
    public static class MemberLogoutDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberLogoutDTO(MemberEntity memberEntity) {
            this.Id = memberEntity.getId();
            this.memberEmail = memberEntity.getMemberEmail();
            this.memberPassword = memberEntity.getMemberPassword();
            this.memberName = memberEntity.getMemberName();
            this.memberPhone = memberEntity.getMemberPhone();
        }
    }

    // 회원 탈퇴
    @Data
    public static class MemberDeleteDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberDeleteDTO(MemberEntity memberEntity) {
            this.Id = memberEntity.getId();
            this.memberEmail = memberEntity.getMemberEmail();
            this.memberPassword = memberEntity.getMemberPassword();
            this.memberName = memberEntity.getMemberName();
            this.memberPhone = memberEntity.getMemberPhone();
        }
    }

    // 회원 수정
    @Data
    public static class MemberUpdateDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberUpdateDTO(MemberEntity memberEntity) {
            this.Id = memberEntity.getId();
            this.memberEmail = memberEntity.getMemberEmail();
            this.memberPassword = memberEntity.getMemberPassword();
            this.memberName = memberEntity.getMemberName();
            this.memberPhone = memberEntity.getMemberPhone();
        }
    }

    // 내 정보 조회
    @Data
    public static class MemberFindOneDTO {
        private Long Id; // 고유 식별자
        private String memberEmail; // 이메일
        private String memberPassword; // 비밀번호
        private String memberName; // 이름
        private String memberPhone; // 전화번호

        public MemberFindOneDTO(MemberEntity memberEntity) {
            this.Id = memberEntity.getId();
            this.memberEmail = memberEntity.getMemberEmail();
            this.memberPassword = memberEntity.getMemberPassword();
            this.memberName = memberEntity.getMemberName();
            this.memberPhone = memberEntity.getMemberPhone();
        }
    }

    // 모든 회원 조회
    @Data
    public static class MemberFindAllDTO {
        private List<MemberFindOneDTO> memberList;

        public MemberFindAllDTO(List<MemberFindOneDTO> memberFindOneDTOList) {
            this.memberList = memberFindOneDTOList;
        }
    }
}






