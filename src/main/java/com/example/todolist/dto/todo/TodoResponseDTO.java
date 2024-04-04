package com.example.todolist.dto.todo;

import com.example.todolist.entity.todo.TodoEntity;
import lombok.Data;

import java.util.List;

@Data
public class TodoResponseDTO {
    @Data
    public static class TodoCreateDTO {
        private Long id;
        private String todoName;
        private TodoEntity.TodoStatus todoStatus;

        public TodoCreateDTO(TodoEntity todo) {
            this.id = todo.getId();
            this.todoName = todo.getTodoName();
            this.todoStatus = todo.getTodoStatus();
        }
    }

    @Data
    public static class TodoUpdateDTO {
        private Long id;
        private String todoName;
        private TodoEntity.TodoStatus todoStatus;
        public TodoUpdateDTO(TodoEntity todo) {
            this.id = todo.getId();
            this.todoName = todo.getTodoName();
            this.todoStatus = todo.getTodoStatus();
        }
    }

    @Data
    public static class TodoToggleDTO {
        private Long id;
        private String todoName;
        private TodoEntity.TodoStatus todoStatus;
        public TodoToggleDTO(TodoEntity todo) {
            this.id = todo.getId();
            this.todoName = todo.getTodoName();
            this.todoStatus = todo.getTodoStatus();
        }
    }

    @Data
    public static class TodoDeleteDTO {
        private Long id;
        private String todoName;
        private TodoEntity.TodoStatus todoStatus;
        public TodoDeleteDTO(TodoEntity todo) {
            this.id = todo.getId();
            this.todoName = todo.getTodoName();
            this.todoStatus = todo.getTodoStatus();
        }
    }
    @Data
    public static class TodoFindOneDTO {
        private Long id;
        private String todoName;
        private TodoEntity.TodoStatus todoStatus;
        public TodoFindOneDTO(TodoEntity todo) {
            this.id = todo.getId();
            this.todoName = todo.getTodoName();
            this.todoStatus = todo.getTodoStatus();
        }
        public TodoFindOneDTO(Long id, String todoName, TodoEntity.TodoStatus todoStatus) {
            this.id = id;
            this.todoName = todoName;
            this.todoStatus = todoStatus;
        }
    }
    @Data
    public static class TodoFindAllDTO {
        private List<TodoFindOneDTO> todoList;
        public TodoFindAllDTO() {
            this.todoList = todoList;
        }
    }
}
