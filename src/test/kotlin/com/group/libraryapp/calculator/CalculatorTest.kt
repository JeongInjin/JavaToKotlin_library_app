package com.group.libraryapp.calculator

import org.junit.jupiter.api.Assertions.*

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
    calculatorTest.divideTest()
    calculatorTest.divideExceptionTest()
}

internal class CalculatorTest {

    fun addTest() {
        //given
        val calculator = Calculator(4)

        //when
        calculator.add(5)

        //then
        if(calculator.number != 9) throw IllegalArgumentException()
    }

    fun minusTest() {
        //given
        val calculator = Calculator(9)

        //when
        calculator.minus(5)

        //then
        if(calculator.number != 4) throw IllegalArgumentException()
    }

    fun multiplyTest() {
        //given
        val calculator = Calculator(9)

        //when
        calculator.multiply(5)

        //then
        if(calculator.number != 45) throw IllegalArgumentException()
    }

    fun divideTest() {
        //given
        val calculator = Calculator(9)

        //when
        calculator.divide(5)

        //then
        if(calculator.number != 1) throw IllegalArgumentException()
    }

    fun divideExceptionTest() {
        //given
        val calculator = Calculator(9)

        //when
        try {
            calculator.divide(0)
        } catch (e: IllegalArgumentException) {
            if (e.message != "0 으로 나늘 수 없습니다.") {
                throw java.lang.IllegalStateException("메세지가 다릅니다.")
            }
            return //테스트 성공
        } catch (e: Exception) {
            throw IllegalStateException()
        }
        throw IllegalStateException("기대하는 예외가 발생하지 않았습니다.")

    }

}