package com.group.tdd_technique

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MailBoxTest {
    @Test
    fun newMailBox_should_empty() {
        val mailbox = MailBox()
        assertThat(mailbox.messageCount()).isEqualTo(0)
    }
}