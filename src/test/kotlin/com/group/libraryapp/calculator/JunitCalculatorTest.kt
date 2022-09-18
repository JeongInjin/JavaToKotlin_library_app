package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JunitCalculatorTest {

    @Test
    fun addTest() {
        //given
        val calculator = Calculator(4)

        //when
        calculator.add(5)

        //then
        assertThat(calculator.number).isEqualTo(9)
    }

    @Test
    fun minusTest() {
        //given
        val calculator = Calculator(9)

        //when
        calculator.minus(5)

        //then
        assertThat(calculator.number).isEqualTo(4)
    }

    @Test
    fun multiplyTest() {
        //given
        val calculator = Calculator(9)

        //when
        calculator.multiply(5)

        //then
        assertThat(calculator.number).isEqualTo(45)
    }

    @Test
    fun divideTest() {
        //given
        val calculator = Calculator(9)

        //when
        calculator.divide(5)

        //then
        assertThat(calculator.number).isEqualTo(1)
    }

    @Test
    fun divideExceptionTest() {
        //given
        val calculator = Calculator(9)

        //when && then
        val message = assertThrows<java.lang.IllegalArgumentException> {
            calculator.divide(0)
        }.message

        assertThat(message).isEqualTo("0 으로 나늘 수 없습니다.")

        assertThrows<java.lang.IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("0 으로 나늘 수 없습니다.")
        }
    }
}