package com.group.unittesting.ch02

import com.group.unittesting.ch02.Product.*
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ch02Test {

    /**
     * 고전파
     */
    @Test
    @DisplayName("재고가 충분할때 구매가 성공 합니다")
    fun Purchase_succeeds_when_enough_inventory() {
        //given
        var store = Store()
        store.AddInventory(Shampoo, 10)
        var customer = Customer()

        //when
        val success: Boolean = customer.Purchase(store, Shampoo, 5)

        //then
        assertThat(success).isTrue
    }

    @Test
    @DisplayName("재고가 충분하지 않으면 구매가 실패 합니다")
    fun Purchase_fails_when_not_enough_inventory() {
        //given
        var store = Store()
        store.AddInventory(Shampoo, 10)
        var customer = Customer()

        //when
        val success: Boolean = customer.Purchase(store, Shampoo, 10)

        //then
        assertThat(success).isFalse
    }

    /**
     * 런던파
     */
    @Test
    fun Purchase_succeeds_when_enough_inventory_London() {
        //given
        var storeMock = mock(Store::class.java)
        `when`(storeMock.HasEnoughInventory(Shampoo, 5)).thenReturn(true)
        var customer = Customer()

        //when
        val storeSuccess = storeMock.HasEnoughInventory(Shampoo, 5)
        val success = customer.Purchase(storeMock, Shampoo,  5)

        //then
        assertThat(success).isTrue
        verify(storeMock, times(1)).RemoveInventory()
    }

    @Test
    fun Purchase_fails_when_not_enough_inventory_London() {
        //given
        var storeMock = mock(Store::class.java)
        `when`(storeMock.HasEnoughInventory(Shampoo, 10)).thenReturn(false)
        var customer = Customer()

        //when
        val storeSuccess = storeMock.HasEnoughInventory(Shampoo, 10)
        val success = customer.Purchase(storeMock, Shampoo,  10)

        //then
        assertThat(success).isFalse
        verify(storeMock, never()).RemoveInventory()
    }

}

class Customer{
    fun Purchase(store: Store, shampoo: Product, i: Int): Boolean {
        var result = true
        if (i <= 5) {
            store.RemoveInventory()
        }else result = false

        return result
    }

}

class Store {
    fun AddInventory(shapoo: Any?, i: Int) {}
    fun HasEnoughInventory(shampoo: Product, i: Int): Boolean {
        return i >= 5
    }

    fun RemoveInventory() {

    }
}
enum class Product {
    Shampoo, Book
}