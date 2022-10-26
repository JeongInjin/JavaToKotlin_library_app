package com.group.unittesting.ch06

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ch06Test {

    @Test
    @DisplayName("출력 기반 테스트")
    fun Discount_of_two_products() {
        //given
        val product1 = Product("Hand wash");
        val product2 = Product("Shampoo");
        val sut = PriceEngine()

        //when
        val discount = sut.calculateDiscount(product1, product2)

        //then
        assertThat(discount).isEqualTo(0.02)
    }

    @Test
    @DisplayName("상태 기반 테스트")
    fun Adding_a_product_to_an_order() {
        //given
        var product = Product("Hand wash")
        var sut = Order()

        //when
        sut.addProduct(product)

        //given
        assertThat(sut.products.size).isEqualTo(1)
        assertThat(sut.products[0]).isEqualTo(product)
    }
    //통신 기반 테스트 => mock 객체를 이용한 테스트 대상 시스템과 협력자 간의 통신을 검증.

}

class PriceEngine
{
    fun calculateDiscount(vararg product: Product): Double {
        val discount: Double = product.size * 0.01
//        return DecimalFormat("#.##").format(discount.coerceAtMost(0.2)).toDouble()
        return discount.coerceAtMost(0.2)
    }
}


class Product(name: String) {
    private val _name: String

    init {
        _name = name
    }
}

class Order {
    val products = mutableListOf<Product>()

    fun addProduct(product: Product) {
        this.products.add(product)
    }
}