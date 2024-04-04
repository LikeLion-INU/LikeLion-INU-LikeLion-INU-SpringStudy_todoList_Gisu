package com.example.todolist.controller;

import com.example.todolist.service.todo.TodoServiceImpl;
import com.example.todolist.common.ApiResponse;
import com.example.todolist.dto.todo.TodoRequestDTO;
import com.example.todolist.dto.todo.TodoResponseDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
@Slf4j
public class TodoController {
    private final TodoServiceImpl todoService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody TodoRequestDTO.TodoCreateDTO todoCreateDTO, HttpSession httpSession) {
        log.info("[TodoController] create");
        Long memberId = (Long) httpSession.getAttribute("memberId");
        TodoResponseDTO.TodoCreateDTO result = todoService.create(memberId, todoCreateDTO);
        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "todo create success", result));
    }

    @PostMapping("/update/{todoId}")
    public ResponseEntity<?> update(@RequestBody TodoRequestDTO.TodoUpdateDTO todoUpdateDTO, @PathVariable("todoId") Long todoId, HttpSession httpSession) {
        log.info("[TodoController] update");
        Long memberId = (Long) httpSession.getAttribute("memberId");
        TodoResponseDTO.TodoUpdateDTO result = todoService.update(memberId, todoId, todoUpdateDTO);

        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "todo update success", result));
    }

    @PostMapping("/toggle/{todoId}")
    public ResponseEntity<?> toggle(@PathVariable("todoId") Long todoId, HttpSession httpSession) {

        log.info("[TodoController] toggle");
        Long memberId = (Long) httpSession.getAttribute("memberId");
        TodoResponseDTO.TodoToggleDTO result = todoService.toggle(memberId, todoId);
        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "todo toggle success", result));
    }

    @PostMapping("/delete/{todoId}")
    public ResponseEntity<?> delete(@PathVariable("todoId") Long todoId, HttpSession httpSession) {

        log.info("[TodoController] delete");
        Long memberId = (Long) httpSession.getAttribute("memberId");
        String result = todoService.delete(memberId, todoId);
        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.CREATED.value(), "todo delete success", result));
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(HttpSession httpSession) {
        log.info("[TodoController] findAll");
        Long memberId = (Long) httpSession.getAttribute("memberId");
        List<TodoResponseDTO.TodoFindAllDTO> result = todoService.findAll(memberId);
        return ResponseEntity.ok().body(ApiResponse.SUCCESS(HttpStatus.OK.value(), "Todo findAll success", result));
    }


}
