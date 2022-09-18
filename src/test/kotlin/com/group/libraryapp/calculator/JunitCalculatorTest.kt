package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
    컨트롤러
     서비스
 도메인 | 레포지토리

 도메인 계층 -> 클래스를 테스트하는 것과 동일하게 테스트.(아래의 사칙연산처럼..)
 서비스, 레포지토리 -> 스프링 빈을 사용하는 테스트방법 사용 (@SpringBootTest)(데이터 위주 검증)
 컨트롤러 -> 스프링 빈을 사용하는 테스트방법 사용, 응답 받는 JSON 을 비롯한 HTTP 위주 검증.
 */

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