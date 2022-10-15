package com.group.tddStudy.study

import com.group.tddStudy.domain.Study
import org.springframework.data.jpa.repository.JpaRepository

interface StudyRepository : JpaRepository<Study, Long> {
}