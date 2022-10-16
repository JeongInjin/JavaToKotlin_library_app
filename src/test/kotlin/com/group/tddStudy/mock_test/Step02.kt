package com.group.tddStudy.mock_test

import com.group.tddStudy.domain.Member
import com.group.tddStudy.domain.Study
import com.group.tddStudy.member.MemberService
import com.group.tddStudy.study.StudyRepository
import com.group.tddStudy.study.StudyService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

//기타 mock verify 참고용.
@ExtendWith(MockitoExtension::class) //@Mock Annotation 을 사용하기위한 extension - 필수
class Step02 {


    @Mock
    lateinit var memberService: MemberService
    @Mock
    lateinit var studyRepository: StudyRepository

    @Test
    fun createStudy() {
        //given
        val studyService = StudyService(memberService, studyRepository)
//        val basicMember = Member(1L, "basic@email.com")
        val member = Member.fixture()
        val study = Study(10, "Kotlin")

        BDDMockito.given(memberService.findById(1)).willReturn(member)
        BDDMockito.given(studyRepository.save(study)).willReturn(study)

        //when
        studyService.createNewStudy(1L, study)

        //then
        Assertions.assertThat(member).isEqualTo(study.owner)

        /**
        아래쪽은 그냥 참고 정도만 하면 될듯..
        아래부분은 mock 객체가 어떻게 사용 됐는지 확인하는 방법의 코드..
        verify? => 특정 메소드가 특정 매개변수로 몇번 호출 되었는지 확인
         */

        //studyService mock 객체 호출 시 notify 가 1번 호출되었는가
        BDDMockito.verify(memberService, BDDMockito.times(1)).notify(study)
        BDDMockito.verify(memberService, BDDMockito.times(1)).notify(member)

        //verify -> then
        BDDMockito.then(memberService).should(BDDMockito.times(1)).notify(study)
        BDDMockito.then(memberService).should(BDDMockito.times(1)).notify(member)

        //memberService 의 validate 호출이 전혀 되지 않았는지
        BDDMockito.verify(memberService, BDDMockito.never()).validate(1L)

        //호출 순서를 확인한다.
        val inOrder = BDDMockito.inOrder(memberService)
        inOrder.verify(memberService).notify(study) //먼저 호출
        inOrder.verify(memberService).notify(member) //다음 호출

    }
}