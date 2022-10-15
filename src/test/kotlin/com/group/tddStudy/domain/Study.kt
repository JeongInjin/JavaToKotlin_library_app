package com.group.tddStudy.domain

import java.time.LocalDateTime

class Study {
    var status: StudyStatus? = StudyStatus.DRAFT
    var limit: Int
    var name: String? = null
    private var openedDateTime: LocalDateTime? = null
    private val owner: Member? = null

//    constructor(limit: Int, name: String?) {
//        this.limit = limit
//        this.name = name
//    }

    constructor(limit: Int) {
        if(limit <= 0) throw IllegalArgumentException("limit 은 0 보다 커야 합니다.")
        this.limit = limit
    }

    override fun toString(): String {
        return "Study{" +
                "status=" + status +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}'
    }

    fun open() {
        openedDateTime = LocalDateTime.now()
        status = StudyStatus.OPENED
    }
}