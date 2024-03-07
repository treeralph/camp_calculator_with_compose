package com.example.camp_calculator_with_compose.calculator

import com.example.camp_calculator_with_compose.operation.AbstractOperation
import com.example.camp_calculator_with_compose.operation.AddOperation
import com.example.camp_calculator_with_compose.operation.DivideOperation
import com.example.camp_calculator_with_compose.operation.MultiplyOperation
import com.example.camp_calculator_with_compose.operation.RemainderOperation
import com.example.camp_calculator_with_compose.operation.SubtractOperation

class Calculator(
    private val addOperation: AbstractOperation = AddOperation(),
    private val subtractOperation: AbstractOperation = SubtractOperation(),
    private val multiplyOperation: AbstractOperation = MultiplyOperation(),
    private val divideOperation: AbstractOperation = DivideOperation(),
    private val remainderOperation: AbstractOperation = RemainderOperation()
) {
    fun addition(a: Number, b: Number): Number = addOperation.operation(a, b)
    fun subtraction(a: Number, b: Number): Number = subtractOperation.operation(a, b)
    fun multiplication(a: Number, b: Number): Number = multiplyOperation.operation(a, b)
    fun division(a: Number, b: Number): Number = divideOperation.operation(a, b)
    fun remainder(a: Number, b: Number): Number = remainderOperation.operation(a, b)
}