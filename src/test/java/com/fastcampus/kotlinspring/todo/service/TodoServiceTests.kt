package com.fastcampus.kotlinspring.todo.service

import com.fastcampus.kotlinspring.todo.domain.Todo
import com.fastcampus.kotlinspring.todo.domain.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
class TodoServiceTests {

    @MockkBean
    // lateinit을 통해, 추후에 이 repository에 대해 mockking을 해줄거다
    lateinit var repository: TodoRepository

    lateinit var service: TodoService

    // by lazy : stub을 사용하는 시점에 초기화
    // 변수를 immutalble하게 가져갈 수 있다
    val stub: Todo by lazy {
        Todo(
            id = 1,
            title = "테스트",
            description = "테스트 상세",
            done = false,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
    }

    @BeforeEach
    fun setUp() {
        service = TodoService(repository)
    }


    @Test
    fun `한개의 TODO를 반환해야한다`() {
        // Given
        every { repository.findByIdOrNull(1L) } returns stub

        // When
        val actual = service.findById(1L)

        // Then
        assertThat(actual).isNotNull
        assertThat(actual).isEqualTo(stub)
    }

}