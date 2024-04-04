package com.example.todolist.repository.todo;

import com.example.todolist.dto.todo.TodoResponseDTO;
import com.example.todolist.entity.todo.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long>{
    List<TodoEntity> findByMemberEntityId(Long memberId);
    //TodoResponseDTO.TodoFindAllDTO findAll(Long memberId);

    void deleteByMemberEntityId(Long memberId);
}
