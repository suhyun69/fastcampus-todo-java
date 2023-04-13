package com.fastcampus.kotlinspring.todo.api.model

import com.fastcampus.kotlinspring.todo.domain.Todo
import java.time.LocalDateTime

data class TodoResponse (
    val id: Long,
    val title: String,
    val description: String,
    val done: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? // non-null로 변경
) {
    companion object {
        fun of (todo: Todo?): TodoResponse {
            checkNotNull(todo) { "Todo is null" }
            checkNotNull(todo.id) { "Todo Id is null" } // checkNotNull 사용

            return TodoResponse(
                id = todo.id!!, // nullable type을 non-null 타입에 넣으려니 오류
                // !! -> 단언 연산자 사용하거나
                title = todo.title,
                description = todo.description,
                done = todo.done,
                createdAt = todo.createdAt,
                updatedAt = todo.updatedAt
            )
        }
    }
}