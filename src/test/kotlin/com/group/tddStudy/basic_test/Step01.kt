package com.group.tddStudy.basic_test

import org.junit.jupiter.api.*

class Step01 {

    companion object {
        @BeforeAll
        @JvmStatic
        fun `beforeAll`() {
            println("static 메서드여야하고 반환값이 없어야함, 테스트 시작 전 1번만 실행 beforeAll - 코틀린은 @JvmStatic annotation 필요")
        }

        @AfterAll
        @JvmStatic
        fun afterAll() {
            println("static 메서드여야하고 반환값이 없어야함, 테스트 시작 후 1번만 실행 AfterAll - 코틀린은 @JvmStatic annotation 필요")
        }
    }

    @BeforeEach
    fun beforeEach() {
        println("테스트 시작 전 매번 실행 -> BeforeEach")
    }

    @AfterEach
    fun afterEach() {
        println("테스트 종료 후 매번 실행 -> AfterEach");
    }


    @Test
    fun test() {
        println("====================================")
        println("test 실행 ~")
        println("====================================")
    }
}