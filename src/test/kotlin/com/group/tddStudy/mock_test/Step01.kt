package com.group.tddStudy.mock_test

import com.group.tddStudy.domain.Member
import com.group.tddStudy.domain.Study
import com.group.tddStudy.member.MemberService
import com.group.tddStudy.study.StudyRepository
import com.group.tddStudy.study.StudyService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
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

    lateinit var studyService: StudyService

    @BeforeEach
    fun init() {
        studyService = StudyService(memberService, studyRepository)
    }

    @Test
    @DisplayName("mock 객체 만들기")
    fun createStudyService1() {
        val memberService = mock(MemberService::class.java)
        val studyRepository = mock(StudyRepository::class.java)
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
        assertThat(studyService).isNotNull
    }

    /**
     * Stubbing 이란
     *  Mock 객체의 행동을 조작하는 것
     * Mock 객체의 기본값
     *      리턴 값이 있는 메소드는 모두 Null 을 리턴하고 있다.
     *      - Optional 타입인 경우 Optional.empty로 리턴
     *      Primitive 타입은 모두 Primitive 값을 따르고 있다.
     *      - Ex. Boolean 인 경우 'false' / Integer 혹은 Long 인 경우 0
     *      Collection 의 경우 모두 비어있는 Collection 을 가지고 있다.
     *      Void 메소드의 경우 예외를 던지지 않고 아무 일도 발생하지 않는다.
     */

    @Test
    @DisplayName("조건을 받아 mock 객체를 반환한다.")
    fun createNewStudy() {
        //given
//        val studyService = StudyService(memberService, studyRepository)
//        val basicMember = Member(1L, "basic@email.com")
        val member = Member.fixture()

        //when
        // mock dml when() => 조건을 받아 mock 객체를 반환한다. Argument matchers 의 any() 를 사용하면 모든것을 허용한다.

        //when - > 조건을 받아 mock 객체를 반환한다. Argument matchers 의 any() 를 사용하면 모든것을 허용한다.
//        `when`(memberService.findById(1L)).thenReturn(member)
        `when`(memberService.findById(any(Long::class.java))).thenReturn(member)
        val study = Study(10, "Kotlin")
        val findById = memberService.findById(1L)
        assertThat(findById!!.Email).isEqualTo("injin@email.com")

        //조건을 주어 exception 을 발생시킨다.
//        `when`(memberService.validate(1L)).thenThrow(java.lang.IllegalArgumentException::class.java)
        doThrow(IllegalArgumentException()).`when`(memberService).validate(1L)
        assertThrows(IllegalArgumentException::class.java) { memberService.validate(1L) }

    }

    @Test
    @DisplayName("메소드가 동일한 매개변수로 여러번 호출될 때 각기 다르게 행동하도록 조작할 수 있다.")
    fun createNewStudy2() {
        //given
        val member = Member.fixture()

        //when
        `when`(memberService.findById(any(Long::class.java)))
            .thenReturn(member) //첫번째 호출 반환
            .thenThrow(java.lang.RuntimeException::class.java) //두번째 호출 반환
            .thenReturn(null) //세번째 호출 반환

        //첫번째 호출 테스트
        val findById = memberService.findById(1L)
        assertThat(findById!!.Email).isEqualTo("injin@email.com")

        //두번째 호출 테스트
        assertThrows(RuntimeException::class.java){ memberService.findById(1L)}

        //세번째 호출 테스트
        assertThat(memberService.findById(1L)).isNull()
    }

}






























