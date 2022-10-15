package com.group.tddStudy.member

import com.group.tddStudy.domain.Member
import com.group.tddStudy.domain.Study

interface MemberService {
    fun findById(memberId: Long): Member?

    fun validate(memberId: Long)

    fun notify(newStudy: Study)

    fun notify(member: Member)
}
