package com.group.primefactors

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


/**
 * 소수(prime number): 1과 자기 자신 외의 정수로는 나누어 떨어지지 않는 정수, 예: 2, 3, 5, 7, 11, 13, 17, 19 ...
 * 소인수(prime factor): 어떤 정수를 소수만의 곱으로 나타낼 때 그 인수가 되는 각각의 소수, 예: '30 = 2 * 3 * 5' 따위의 소스를 말한다.
 */
class PrimeFactorsTest {

    @Test
    fun canFactorIntoPrimes() {
        assertPrimeFactors(1, mutableListOf())
        assertPrimeFactors(2, mutableListOf(2))
        assertPrimeFactors(3, mutableListOf(3))
        assertPrimeFactors(4, mutableListOf(2, 2))
        assertPrimeFactors(5, mutableListOf(5))
        assertPrimeFactors(6, mutableListOf(2, 3))
        assertPrimeFactors(7, mutableListOf(7))
        assertPrimeFactors(8, mutableListOf(2, 2, 2))
        assertPrimeFactors(9, mutableListOf(3, 3))
        assertPrimeFactors(2 * 2 * 2 * 3 * 5 * 7 * 11 * 13, mutableListOf(2 , 2 , 2 , 3 , 5 , 7 , 11 , 13))
    }

    private fun assertPrimeFactors(n: Int, list: MutableList<Int>) {
        assertThat(of(n)).isEqualTo(list)
    }

    private fun of(np: Int): List<Int?>? {
        val factors = mutableListOf<Int>()
        var n = np
        var divisor = 2
        while (n > 1) {
            while (n % divisor == 0) {
                factors.add(divisor)
                n /= divisor
            }
            divisor++
        }
        return factors
    }
}