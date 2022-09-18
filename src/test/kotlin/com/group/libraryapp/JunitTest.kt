package com.group.libraryapp

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class JunitTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            println("모든 테스트 전 1번 호출")
        }

        @JvmStatic
        @AfterAll
        fun afterAll() {
            println("모든 테스트 후 1번 호출")
        }
    }

    @BeforeEach
    fun beforeEach() {
        println("각 테스트 전 호출")
    }

    @AfterEach
    fun afterEach() {
        println("각 테스트 후 호출")
    }

    @Test
    fun test1() {
        println("테스트 1")
    }

    @Test
    fun test2() {
        println("테스트 2")
    }
}