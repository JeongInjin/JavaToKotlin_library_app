package com.group.tddStudy.domain

import java.time.LocalDateTime

class Study(
    var limit: Int,
    var name: String? = null
) {
    init {
        if(limit <= 0) throw IllegalArgumentException("limit 은 0 보다 커야 합니다.")
    }
    var status: StudyStatus? = StudyStatus.DRAFT

    private var openedDateTime: LocalDateTime? = null
    var owner: Member? = null

    fun open() {
        openedDateTime = LocalDateTime.now()
        status = StudyStatus.OPENED
    }
}