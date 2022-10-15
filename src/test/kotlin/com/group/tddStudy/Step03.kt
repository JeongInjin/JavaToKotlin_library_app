package com.group.tddStudy

import com.group.tddStudy.domain.Study
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.aggregator.AggregateWith
import org.junit.jupiter.params.aggregator.ArgumentsAccessor
import org.junit.jupiter.params.aggregator.ArgumentsAggregator
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.converter.SimpleArgumentConverter
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

class Step03 {

    /**
     * 반복 테스트 repetitionInfo 인자를 통해 현재 몇번째 반복인지 알 수 있다.
     * 반복시 마다 매개변수의 값을 변경하면서 테스트 하고 싶다면 ParameterizedTest 를 이용하면 된다.
     */
    @DisplayName("반복 테스트를 진행한다.")
    @RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    fun repeatTest(repetitionInfo: RepetitionInfo?) {
        println("반복 테스트 $repetitionInfo")
    }

    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @ValueSource(strings = ["a", "b", "c", "d"])
    @DisplayName("매개변수 변경 반복 테스트")
    fun parameterizedTest(message: String?) {
        println("메세지: $message")
    }

    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @ValueSource(strings = ["a", "b", "c", "d"])
//    @EmptySource //인자에 빈값을 추가한다
//    @NullSource //인자에 널값을 추가한다
    @NullAndEmptySource //위 2가지 Annotation 을 합친경우 주석 후 에도 확인해 보세요.
    @DisplayName("매개변수 변경 반복 테스트 2")
    fun parameterizedTest2(message: String?) {
        println("메세지: $message")
    }

    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @ValueSource(ints = [10, 20, 30, 40])
    @DisplayName("StudyConvert 를 이용한 테스트")
    fun parameterizedTest3(@ConvertWith(StudyConvert::class) study: Study) {
        println("메세지(study.limit): ${study.limit}")
    }

    //매개변수 convert
    class StudyConvert : SimpleArgumentConverter() {
        override fun convert(source: Any?, targetType: Class<*>?): Any {
            assertThat(targetType).`as`("Study class 만 변환 가능 합니다.").isEqualTo(Study::class.java)
            return Study(source.toString().toInt())
        }

    }

    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @CsvSource("10, '코틀린 스터디'", "20, '자바 스터디'")
    @DisplayName("CsvSource 와 argumentsAccessor 를 이용한 파싱된 객체 받기")
    fun parameterizedTest6(@AggregateWith(StudyAggregator::class) study: Study) {
        println("메세지(study): $study")
    }

    class StudyAggregator : ArgumentsAggregator {
        override fun aggregateArguments(accessor: ArgumentsAccessor?, context: ParameterContext?): Any {
            return Study(limit = accessor!!.getInteger(0), name = accessor!!.getString(1))
        }
    }

}

