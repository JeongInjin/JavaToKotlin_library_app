package com.group.tddStudy.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Member(
    @Id
    @GeneratedValue
    var Id: Long? = null,
    var Email: String? = null,
) {


    companion object {
        fun fixture(
            id: Long? = 1L,
            email: String = "injin@email.com"
        ): Member {
            return Member(
                Id = id,
                Email = email
            )
        }
    }
}