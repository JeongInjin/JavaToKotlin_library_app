package com.group.tdd_technique

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MyStackTest {

    @Test
    fun pop_should_return_pushed_value() {
        val stack = MyStack()
        stack.push(1)
        stack.push(2)

        assertThat(stack.pop()).isEqualTo(2)
        assertThat(stack.pop()).isEqualTo(1)
    }
}