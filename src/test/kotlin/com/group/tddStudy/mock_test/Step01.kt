package com.group.tddStudy.mock_test

import com.group.tddStudy.member.MemberService
import com.group.tddStudy.study.StudyRepository
import com.group.tddStudy.study.StudyService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

/**
  대역의 종류
    - Stub(스텁)
        - 구현을 단순한 것으로 대체한다. 테스트에 맞게 단순히 원하는 동작을 수행한다.
    - Fake(가짜)
        - 제품에는 적합하지 않지만, 실제 동작하는 구현을 제공한다
        - ex: DB 대신에 Memory 를 이용한..
    - Spy(스파이)
        - 호출된 내역을 기록한다.
        - 기록한 내용은 테스트 결과를 검증할 때 사용한다.
    - Mock(모의)
        - 기대한 대로 상호작용하는지 행위를 검증한다.
        - 기대한 대로 동작하지 않으면 익셉션을 발생할 수 있다.
        - 모의 객체는 스텁이자 스파이도 된다.
 */


@ExtendWith(MockitoExtension::class) //@Mock Annotation 을 사용하기위한 extension - 필수
class Step01 {

    @Mock
    lateinit var memberService: MemberService
    @Mock
    lateinit var studyRepository: StudyRepository

    @Test
    @DisplayName("mock 객체 만들기")
    fun createStudyService1() {
        val memberService = Mockito.mock(MemberService::class.java)
        val studyRepository = Mockito.mock(StudyRepository::class.java)
        val studyService = StudyService(memberService, studyRepository)
        assertThat(studyService).isNotNull
    }

    @Test
    @DisplayName("mock 객체를 매개변수로 받기")
    fun createStudyService2(@Mock memberService: MemberService, @Mock studyRepository: StudyRepository) {
        val studyService = StudyService(memberService, studyRepository)
        assertThat(studyService).isNotNull
    }

    @Test
    @DisplayName("mock 객체를 전역 Mock Annotation 을 이용해서 받기")
    fun createStudyService3() {
        val studyService = StudyService(memberService, studyRepository)
        assertThat(studyService).isNotNull
    }

    /**
     * Stubbing 이란
     *  Mock 객체의 행동을 조작하는 것
     */
}