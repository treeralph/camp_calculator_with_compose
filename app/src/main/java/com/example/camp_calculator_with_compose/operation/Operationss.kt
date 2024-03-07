package com.example.camp_calculator_with_compose.operation

import com.example.camp_calculator_with_compose.numberClass

class AddOperation: AbstractOperation {
    override fun operation(a: Number, b: Number): Number {
        return when(numberClass.indexOfFirst { it == a::class || it == b::class }) {
            0 -> a.toDouble() + b.toDouble()
            1 -> a.toFloat() + b.toFloat()
            2 -> a.toLong() + b.toLong()
            3 -> a.toInt() + b.toInt()
            4 -> a.toShort() + b.toShort()
            5 -> a.toByte() + b.toByte()
            else -> throw IllegalArgumentException("Parameter class: Wrong")
        }
    }
}

class SubtractOperation: AbstractOperation {
    override fun operation(a: Number, b: Number): Number {
        return when(numberClass.indexOfFirst { it == a::class || it == b::class }) {
            0 -> a.toDouble() - b.toDouble()
            1 -> a.toFloat() - b.toFloat()
            2 -> a.toLong() - b.toLong()
            3 -> a.toInt() - b.toInt()
            4 -> a.toShort() - b.toShort()
            5 -> a.toByte() - b.toByte()
            else -> throw IllegalArgumentException("Parameter class: Wrong")
        }
    }
}

class MultiplyOperation: AbstractOperation {
    override fun operation(a: Number, b: Number): Number {
        return when(numberClass.indexOfFirst { it == a::class || it == b::class }) {
            0 -> a.toDouble() * b.toDouble()
            1 -> a.toFloat() * b.toFloat()
            2 -> a.toLong() * b.toLong()
            3 -> a.toInt() * b.toInt()
            4 -> a.toShort() * b.toShort()
            5 -> a.toByte() * b.toByte()
            else -> throw IllegalArgumentException("Parameter class: Wrong")
        }
    }
}

class DivideOperation: AbstractOperation {
    override fun operation(a: Number, b: Number): Number {
        return when(numberClass.indexOfFirst { it == a::class || it == b::class }) {
            0 -> a.toDouble() / b.toDouble()
            1 -> a.toFloat() / b.toFloat()
            2 -> a.toLong() / b.toLong()
            3 -> a.toInt() / b.toInt()
            4 -> a.toShort() / b.toShort()
            5 -> a.toByte() / b.toByte()
            else -> throw IllegalArgumentException("Parameter class: Wrong")
        }
    }
}

class RemainderOperation: AbstractOperation {
    override fun operation(a: Number, b: Number): Number {
        return when(numberClass.indexOfFirst { it == a::class || it == b::class }) {
            0 -> a.toDouble() % b.toDouble()
            1 -> a.toFloat() % b.toFloat()
            2 -> a.toLong() % b.toLong()
            3 -> a.toInt() % b.toInt()
            4 -> a.toShort() % b.toShort()
            5 -> a.toByte() % b.toByte()
            else -> throw IllegalArgumentException("Parameter class: Wrong")
        }
    }
}