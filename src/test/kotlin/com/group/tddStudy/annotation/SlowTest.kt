package com.group.tddStudy.annotation

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

//설정은 FastTest 와 동일.
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RetentionPolicy.RUNTIME)
@Test
@Tag("slow")
annotation class SlowTest()
