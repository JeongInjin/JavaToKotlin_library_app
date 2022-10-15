package com.group.tddStudy

import com.group.tddStudy.annotation.FastTest
import com.group.tddStudy.annotation.SlowTest
import com.group.tddStudy.domain.Study
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName

/**
 * custom annotation 을 사용하는 예제를 배웁니다.
 * 해당 예제는 @FstTest, @SlowTest 를 만들어 테스트를 합니다.
 * 활용
 * 1.실행 설정에서 tags 테스트를 설정해 해당 @Tag 의 필드명만 실행할 수 있다.(ex: @Tag("fast") => tags: fast)
 */
class Step05 {
    /**
     * 커스텀 태그
     * Annotation 을 생성하여, Tag Annotation 을 달지않고도 같은 효과를 가질 수 있다.
     */
    @FastTest
    @DisplayName("커스텀 태그를 통한 지정 테스트 - fast")
    fun custom_tag_fast() {
        val study = Study(10)
        Assertions.assertThat(study.limit).isGreaterThan(0)
    }

    @SlowTest
    @DisplayName("커스텀 태그를 통한 지정 테스트 - slow")
    fun custom_tag_slow() {
        val study = Study(10)
        Assertions.assertThat(study.limit).isGreaterThan(0)
    }

    /**
     * junit4의 확장모델 @RunWith(Runner), TestRule, MethodRule =>
     * junit5 는 Extension 하나의 확장 모델로 통합됨.
     * - 확장팩 등록 방법
     *      - 선언전인 등록 @ExtendWith
     *      - 프로그래밍 등록 @RegisterExtension
     *      - 자동 등록 자바 Serv
     */
    //해당 기능은 추후 필요에 따라 추가하겠음..아직 불 필요해 보임 괜한 코드만 늘어날 것으로 예상.
}