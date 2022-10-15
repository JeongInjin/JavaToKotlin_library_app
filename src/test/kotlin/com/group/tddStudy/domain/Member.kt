package com.group.tddStudy.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member {
    @Id
    @GeneratedValue
    private val id: Long? = null
    private val Email: String? = null
}