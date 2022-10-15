package com.group.tddStudy

import com.group.tddStudy.domain.Study
import com.group.tddStudy.domain.StudyStatus.DRAFT
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Assumptions.assumingThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertTimeoutPreemptively
import java.time.Duration


//https://effortguy.tistory.com/123
class Step02 {

    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE0E")
    fun create_new_study() {
        val study = Study(limit = 10)
        assertThat(study).isNotNull

        //블록 안에 있는 테스는 실패 여부와 상관없이 모두 실행 한다. (lambda 식으로 묶어줘야 함)
        //해당 블록없이 원래대로 진행한다면 실패시 그 아래부분은 진행하지 않는다.
        assertSoftly {
            it.assertThat(study).isNotNull
            it.assertThat(study.limit).isGreaterThan(0)
            it.assertThat(study.status).`as`("스터디를 처음 만들면 상태값이 $DRAFT 여야 한다.").isEqualTo(DRAFT)
            it.assertThat(study.limit > 0).`as`("스터디 최대 참가 인원은 0보다 커야 한다.").isTrue
        }
    }

    @Test
    @DisplayName("Study limit 이 0 이하 일 경우 IllegalArgumentException 이 발생 합니다.")
    fun `create_new_study_again`() {
        assertThatThrownBy { Study(0) } //action
            .isInstanceOf(IllegalArgumentException::class.java) //exception
            .hasMessageContaining("limit 은 0 보다 커야 합니다.") // 메세지까지 비교하고 싶을때..

        assertThatThrownBy { Study(-10) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("타임아웃 테스트")
    fun study_timeout() {
        //assertTimeout => 지정한 시간보다 오래 걸려도 테스트가 끝날 때까지 대기
//        assertTimeout(Duration.ofMillis(100)){
//            Study(10)
//            Thread.sleep(2000)
//        }

        //assertTimeoutPreemptively => 지정한 시간보다 오래 걸린 경우 바로 테스트 종류
        assertTimeoutPreemptively(Duration.ofMillis(100)){
            Study(10)
            Thread.sleep(50) //확인하고 싶을시 5000 으로 수정해서 테스트 해보세요.5초를 기다리지 않고 종료됨.
        }
    }

    @Test
    @DisplayName("코드 조건에 따른 테스트.")
    fun `study condition test`() {
        val test_value = "OK"
        assumeTrue("ok".equals(test_value, ignoreCase = true)) //해당 조건이 true 인경우 진행한다. *ignoreCase = true 면 대소문자 구분안함.

        val study = Study(10)
        assertThat(study.limit).isGreaterThan(0)
        println("조건이 true 로 인하여 테스트 완료.")
    }

    @Test
    @DisplayName("코드 조건에 따른 테스트 두번째.")
    fun `study condition test 2`() {
        val test_value = "OK"
        val study = Study(10)
        /**
         * assumingThat(boolean, executable)
         * 첫 번째 인자가 True 면 두 번째 인자로 들어온 함수 실행
         * 첫 번째 인자 값이 False 인 경우에도 테스트를 스킵하지 않고 다음 코드를 진행합니다.
         */
        assumingThat("ok".equals(test_value, ignoreCase = true)) {
            assertThat(study.limit).isGreaterThan(0)
            println("조건이 true 로 인하여 테스트 실행.")
        }

        assumingThat("not_ok".equals(test_value, ignoreCase = true)) {
            assertThat(study.limit).isGreaterThan(0)
            println("조건이 false 로 인하여 테스트 무시.")
        }
    }

    @Test
    @Disabled
    @DisplayName("@Disabled 붙은 테스트는 실행되지 않는다.")
    fun DisabledTest() {
        println("사용하고 싶지 않은 테스트일 경우 @Disabled 를 붙인다.")
    }
}