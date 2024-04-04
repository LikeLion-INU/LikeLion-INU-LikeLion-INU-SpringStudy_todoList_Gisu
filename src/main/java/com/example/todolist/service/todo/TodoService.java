package com.example.todolist.service.todo;

import com.example.todolist.dto.todo.TodoRequestDTO;
import com.example.todolist.dto.todo.TodoResponseDTO;

import java.util.List;

public interface TodoService {
    TodoResponseDTO.TodoCreateDTO create(Long memberId, TodoRequestDTO.TodoCreateDTO todoCreateDTO);
    TodoResponseDTO.TodoUpdateDTO update(Long memberId, Long todoId, TodoRequestDTO.TodoUpdateDTO todoUpdateDTO);
    TodoResponseDTO.TodoToggleDTO toggle(Long memberId, Long todoId);
    String delete(Long memberId, Long todoId);
    List<TodoResponseDTO.TodoFindAllDTO> findAll(Long memberId);
}
