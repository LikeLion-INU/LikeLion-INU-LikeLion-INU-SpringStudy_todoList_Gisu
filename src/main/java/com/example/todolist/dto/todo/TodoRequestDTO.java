package com.example.todolist.dto.todo;

import lombok.Data;

@Data
public class TodoRequestDTO {
    @Data
    public static class TodoCreateDTO{
        private String todoName;
    }
    @Data
    public static class TodoUpdateDTO {
        private String todoName;
    }
}
