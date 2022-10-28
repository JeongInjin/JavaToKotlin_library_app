package com.group.nameinverter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NameInverterTest {

    private val nameInverter = NameInverter()

    @Test
    fun name() {
        assertThat(nameInverter.invert(null)).isEqualTo("")
        assertThat(nameInverter.invert("")).isEqualTo("")
        assertThat(nameInverter.invert("name")).isEqualTo("name")
        assertThat(nameInverter.invert("first last")).isEqualTo("last, first")
        assertThat(nameInverter.invert("    name    ")).isEqualTo("name")
        assertThat(nameInverter.invert("first     last")).isEqualTo("last, first")
        assertThat(nameInverter.invert("Mr. first last")).isEqualTo("last, first")
        assertThat(nameInverter.invert("Mrs. first last")).isEqualTo("last, first")
        assertThat(nameInverter.invert("first last SR.")).isEqualTo("last, first SR.")
        assertThat(nameInverter.invert("first last BS. Phd.")).isEqualTo("last, first BS. Phd.")
        assertThat(nameInverter.invert("    Robert Martin II esq.")).isEqualTo("Martin, Robert II esq.")

    }
}