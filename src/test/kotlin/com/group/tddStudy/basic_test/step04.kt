package com.group.tddStudy.basic_test

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

/**
 * 아래 TestInstance annotation 설정을 주면 전역변수 또는 beforeAll 시 하나의 인스턴스만 생성된다.
 * 해당 설정을 클래스별로 설정할 수 있고, yml 쪽에 per_class 를 줘도 된다.
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS) 를 주석을 풀고, 주석을 주고 테스트 해보자.
 */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) //필요시 test > resources 로 설정 이관
class step04 {

    /**
     * 테스트 인스턴스 와 순서
     * 테스트 클래스는 각 메서드를 생성시마다 새로운 인스턴스를 생성하기 때문에 전역변수를 사용해도 초기값만 나온다.(ex: int n = 1, n++, n++...)
     *      - 해당 방식으로 진행하는 이유는 테스트간의 의존성을 없애기 위해서임.
     * 전략변경을 원한다면 TestInstance Annotation 을 이용해야함.
     *      - @TestInstance(TestInstance.Lifecycle.PER_CLASS) 설정해줌에 따라 하나의 인스턴스만 class 별로 생성됨.
     * -참고: beforeAll, afterAll 은 반드시 static class 여야하지만, TestInstance 의 옵션이 perClass 일 경우 static 이 아니여도 된다.
     */

    //전역 변수 선언
    var number = 0

    @Order(0)//순서지정
    @Test
    @DisplayName("@TestInstance 의 작동 테스트1 - 순서도 추가")
    fun TestInstance1() {
        println("number: ${number++}")
    }

    @Order(1)//순서지정
    @Test
    @DisplayName("@TestInstance 의 작동 테스트2 - 순서도 추가")
    fun TestInstance2() {
        println("number: ${number++}")
    }

}