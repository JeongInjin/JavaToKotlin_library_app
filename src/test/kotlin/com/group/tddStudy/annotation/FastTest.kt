package com.group.tddStudy.annotation

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Target: 어디에 적용할것인지 target 을 설정하는 곳.
 * FUNCTION: 메서드 적용, PROPERTY_GETTER, PROPERTY_SETTER: 프로퍼티 getter, setter 적용
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RetentionPolicy.RUNTIME) //해당 annotation 사용하는 정보가 RUNTIME 까지 유지해야한다는 설정.
@Test
@Tag("fast")
annotation class FastTest
