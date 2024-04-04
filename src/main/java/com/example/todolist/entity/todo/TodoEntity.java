package com.example.todolist.entity.todo;


import com.example.todolist.entity.member.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TodoEntity {
    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id; // 고유 식별자
    private String todoName; // 투두 제목


    public enum TodoStatus {
        NOT_FINISH, FINISH
    }

    @Enumerated(EnumType.STRING)
    private TodoStatus todoStatus = TodoStatus.NOT_FINISH; // 투두 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity; // 회원과 투두는 일대다 관계, 최대한 "다"에 단방향 관계를 해주는 게 좋음.


    /** 메서드 **/
    public void todoUpdate(String todoName) {
        this.todoName = todoName;
    }
    public void updateTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }


    public TodoEntity(String todoName) {
        this.todoName = todoName;
    }

    public TodoEntity(String todoName, MemberEntity memberEntity) {
        this.todoName = todoName;
        this.memberEntity = memberEntity;
    }

}
