package com.group.wordwrapper

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WrapperTest {

    @Test
    fun should_warp() {
        assertWraps(null, 1, "")
        assertWraps("", 1, "")
        assertWraps("x", 1, "x")
        assertWraps("xx", 1, "x\nx")
        assertWraps("xxx", 1, "x\nx\nx")
        assertWraps("x x", 1, "x\nx")
        assertWraps("x xx", 3, "x\nxx")
        assertWraps("four score and seven years ago our fathers brought forth upon this continent", 7,
            "four\nscore\nand\nseven\nyears\nago our\nfathers\nbrought\nforth\nupon\nthis\ncontine\nnt")
    }

    private fun assertWraps(s: String?, width: Int, expected: String) {
        assertThat(warp(s, width)).isEqualTo(expected)
    }

    private fun warp(s: String?, width: Int): String {
        if(s == null) return ""
        return if (s.length <= width) return s
        else {
            var breakPoint = s.lastIndexOf(" ", width)
            if(breakPoint == -1) breakPoint = width
            s.substring(0, breakPoint) + "\n" + warp(s.substring(breakPoint).trim(), width)
        }


    }


//Getting Unstuck delete code
//    @Test
//    fun should_warp() {
//        assertThat(warp("word word", 4)).isEqualTo("word\nword")
//        assertThat(warp("a dog", 5)).isEqualTo("a dog")
//        assertThat(warp("a dog with a bone", 6)).isEqualTo("a dog\nwidth a\nbone")
//
//    }
//
//    private fun warp(s: String, width: Int): String {
//        return if(s.length > width) s.replace(" ", "\n") else s
//    }
}