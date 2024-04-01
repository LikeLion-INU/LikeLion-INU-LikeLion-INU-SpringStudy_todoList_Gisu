package com.example.todolist.entity.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; // 고유 식별자
    private String memberEmail; // 이메일
    private String memberPassword; // 비밀번호
    private String memberName; // 이름
    private String memberPhone; // 전화번호


    /** 생성자 **/

    public MemberEntity(String memberEmail, String memberPassword) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
    }

    public MemberEntity(String memberEmail, String memberPassword, String memberName, String memberPhone) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
    }
}
