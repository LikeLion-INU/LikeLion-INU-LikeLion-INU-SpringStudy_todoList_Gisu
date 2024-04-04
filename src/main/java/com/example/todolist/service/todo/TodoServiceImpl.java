package com.example.todolist.service.todo;

import com.example.todolist.dto.todo.TodoRequestDTO;
import com.example.todolist.dto.todo.TodoResponseDTO;
import com.example.todolist.entity.member.MemberEntity;
import com.example.todolist.entity.todo.TodoEntity;
import com.example.todolist.repository.member.MemberRepository;
import com.example.todolist.repository.todo.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.todolist.entity.todo.TodoEntity.TodoStatus.FINISH;
import static com.example.todolist.entity.todo.TodoEntity.TodoStatus.NOT_FINISH;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class TodoServiceImpl implements TodoService {
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    @Override
    @Transactional
    public TodoResponseDTO.TodoCreateDTO create(Long memberId, TodoRequestDTO.TodoCreateDTO todoCreateDTO) {

        log.info("[TodoServiceImpl] create");
        MemberEntity findMember = getMember_memberId(memberId);

        TodoEntity todoEntity = new TodoEntity(todoCreateDTO.getTodoName(), findMember);
        todoRepository.save(todoEntity);

        return new TodoResponseDTO.TodoCreateDTO(todoEntity);
    }

    @Override
    public TodoResponseDTO.TodoUpdateDTO update(Long memberId, Long todoId, TodoRequestDTO.TodoUpdateDTO todoUpdateDTO) {
        log.info("[TodoServiceImpl] update");

        MemberEntity findMember = getMember_memberId(memberId);
        TodoEntity findTodo = getTodo_todoId(todoId);

        checkMemberRelationTodo(findMember, findTodo);

        // 업데이트 하기
        findTodo.todoUpdate(todoUpdateDTO.getTodoName());

        return new TodoResponseDTO.TodoUpdateDTO(findTodo);
    }

    @Override
    public TodoResponseDTO.TodoToggleDTO toggle(Long memberId, Long todoId) {
        log.info("[TodoServiceImpl] toggle");

        MemberEntity findMember = getMember_memberId(memberId);
        TodoEntity findTodo = getTodo_todoId(todoId);

        checkMemberRelationTodo(findMember, findTodo);

        toggleTodo(findTodo);

        return new TodoResponseDTO.TodoToggleDTO(findTodo);
    }

    @Override
    public String delete(Long memberId, Long todoId) {
        log.info("[TodoServiceImpl] delete");

        MemberEntity findMember = getMember_memberId(memberId);
        TodoEntity findTodo = getTodo_todoId(todoId);

        checkMemberRelationTodo(findMember, findTodo);

        todoRepository.deleteById(todoId);

        return "SUCCESS";
    }

    @Override
    public List<TodoResponseDTO.TodoFindAllDTO> findAll(Long memberId) {
        log.info("[TodoServiceImpl] findAll");
        //회원 id 기준 엔티티리스트에 디비에서 받아오기
        List<TodoEntity> todoEntityList = todoRepository.findByMemberEntityId(memberId);
        //반환 할 디티오리스트 생성
        List<TodoResponseDTO.TodoFindAllDTO> todoResponseDTOList = new ArrayList<>();


        for (TodoEntity todoEntity : todoEntityList) {
            // TodoEntity를 TodoFindOneDTO(단일조회용)으로 매핑
            TodoResponseDTO.TodoFindOneDTO todoFindOneDTO = new TodoResponseDTO.TodoFindOneDTO(todoEntity);
            // 모든 할 일 조회디티오 생성
            TodoResponseDTO.TodoFindAllDTO todoFindAllDTO = new TodoResponseDTO.TodoFindAllDTO();
            //리스트 초기화 (안하면 null)
            todoFindAllDTO.setTodoList(new ArrayList<>());
            //단일->모든 리스트로 넣기
            todoFindAllDTO.getTodoList().add(todoFindOneDTO);
            todoResponseDTOList.add(todoFindAllDTO);
        }

        return todoResponseDTOList;
    }




    private MemberEntity getMember_memberId(Long memberId){
        Optional<MemberEntity> optionalFindMember = memberRepository.findById(memberId);
        if(optionalFindMember.isEmpty()) {
            log.info("[ERROR] 존재하지 않은 회원 입니다.");
        }
        return optionalFindMember.get();
    }
    private void toggleTodo(TodoEntity findTodo)  {
        if(findTodo.getTodoStatus() == NOT_FINISH) {
            findTodo.updateTodoStatus(FINISH);
        } else {
            findTodo.updateTodoStatus(NOT_FINISH);
        }
    }
    private TodoEntity getTodo_todoId(Long todoId){
        Optional<TodoEntity> optionalFindTodo = todoRepository.findById(todoId);
        if(optionalFindTodo.isEmpty()) {
            log.info("[ERROR] 존재하지 않은 투두 입니다.");
        }
        return optionalFindTodo.get();
    }
    private static void checkMemberRelationTodo(MemberEntity findMember, TodoEntity findTodo) {
        if(!Objects.equals(findMember.getId(), findTodo.getMemberEntity().getId())) {
            log.info("[ERROR] 투두의 주인이 아닙니다.");
        }
    }

}
