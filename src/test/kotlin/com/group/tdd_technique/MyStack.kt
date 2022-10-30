package com.group.tdd_technique

class MyStack {
    private var STACK_SIZE = 100
    private val value = IntArray(STACK_SIZE)
    private var index = 0



    fun pop(): Int {
        return value[--index]
    }

    fun push(value: Int) {
        this.value[index++] = value
    }

}
