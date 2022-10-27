package com.group.bowlinggame

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class GameTest {
    lateinit var game: Game

    @BeforeEach
    fun setUp() {
        game = Game()
    }

    @Test
    fun canRoll() {
        game.roll(0)
    }

    private fun rollMany(frames: Int, pins: Int) {
        for (i in 0 until frames) {
            game.roll(pins)
        }
    }

    private fun rollStrike() {
        game.roll(10)
    }

    private fun rollSpare() {
        game.roll(5)
        game.roll(5)
    }

    @Test
    fun gutterGame() {
        rollMany(20, 0)
        assertThat(game.getScore()).isEqualTo(0)
    }

    @Test
    fun allOnes() {
        rollMany(20, 1)
        assertThat(game.getScore()).isEqualTo(20)
    }

    @Test
    fun oneSpare() {
        rollSpare()
        game.roll(3)
        rollMany(17, 0)
        assertThat(game.getScore()).isEqualTo(16)
    }

    @Test
    fun oneStrike() {
        rollStrike()
        game.roll(5)
        game.roll(3)
        rollMany(16, 0)
        assertThat(game.getScore()).isEqualTo(26)
    }

    @Test
    fun perfectGame() {
        rollMany(10, 10)
        game.roll(10)
        game.roll(10)

        assertThat(game.getScore()).isEqualTo(300)
    }


}