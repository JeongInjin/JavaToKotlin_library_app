package com.group.bowlinggame

class Game {
    private var score: Int = 0
    var rolls = IntArray(21)
    var currentRoll = 0

    fun roll(pins: Int) {
        rolls[currentRoll++] = pins
    }

    fun getScore(): Int {
        var score =0
        var firstRollInFrame = 0
        for (frame in 0 until 10) {
            if (isSpare(firstRollInFrame)) {
                score += 10 + nextBallsForSpare(firstRollInFrame)
                firstRollInFrame += 2
            } else if (isStrike(firstRollInFrame)) {
                score += 10 + nextBallsForStrike(firstRollInFrame)
                firstRollInFrame += 1
            } else {
                score += nextBallsForFrame(firstRollInFrame)
            firstRollInFrame += 2
            }
        }
        return score
    }

    private fun nextBallsForStrike(firstRollInFrame: Int) =
        rolls[firstRollInFrame + 1] + rolls[firstRollInFrame + 2]

    private fun nextBallsForSpare(firstRollInFrame: Int) = rolls[firstRollInFrame + 2]

    private fun isStrike(firstRollInFrame: Int) = rolls[firstRollInFrame] == 10

    private fun isSpare(firstRollInFrame: Int) = rolls[firstRollInFrame] + rolls[firstRollInFrame + 1] == 10

    private fun nextBallsForFrame(firstRollInFrame: Int) = rolls[firstRollInFrame] + rolls[firstRollInFrame + 1]

}
