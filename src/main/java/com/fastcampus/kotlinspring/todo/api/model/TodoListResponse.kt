package com.fastcampus.kotlinspring.todo.api.model

import com.fastcampus.kotlinspring.todo.domain.Todo
import com.fasterxml.jackson.annotation.JsonIgnore

data class TodoListResponse (
    val items: List<TodoResponse>,
) {
    // Utility
    // Size를 Property로 만들면 Json에서 자동으로 응답으로 내려가는데
    // getter에 @JsonIgnore를 추가하면 안 내려간다
    val size: Int
        @JsonIgnore //
        get() = items.size

    // 인자를 받아야 하기 때문에 Property로 가져올 수 없다 -> 메소드
    fun get(index: Int) = items[index]

    // Kotlin은 static이 없다 -> Companion Object
    companion object {
        fun of(todoList: List<Todo>) =
            TodoListResponse(todoList.map(TodoResponse::of))
    }
}