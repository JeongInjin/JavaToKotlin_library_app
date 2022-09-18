package com.group.libraryapp.calculator

/**
 * 1.계산기는 정수만을 취급한다.
 * 2.계산기가 생성될 때 숫자 1개를 받는다.
 * 3.최초 숫자가 기록된 이후에는 연산자 함수를 통해 숫자를 받아 지속적으로 계산한다.
 */
//class Calculator(
//    private var _number: Int
//) {
//    //backing properties
//    val number: Int
//        get() = this._number
//
//    fun add(operand: Int) {
//        this._number += operand
//    }
//
//    fun minus(operand: Int) {
//        this._number -= operand
//    }
//
//    fun multiply(operand: Int) {
//        this._number *= operand
//    }
//
//    fun divide(operand: Int) {
//        if (operand == 0) {
//            throw IllegalArgumentException("0 으로 나늘 수 없습니다.")
//        }
//        this._number /= operand
//    }
//}

//아래와 같이 Backing Properties 사용하지 않는 방식으로 진행.
class Calculator(
    var number: Int
) {

    fun add(operand: Int) {
        this.number += operand
    }

    fun minus(operand: Int) {
        this.number -= operand
    }

    fun multiply(operand: Int) {
        this.number *= operand
    }

    fun divide(operand: Int) {
        if (operand == 0) {
            throw IllegalArgumentException("0 으로 나늘 수 없습니다.")
        }
        this.number /= operand
    }
}