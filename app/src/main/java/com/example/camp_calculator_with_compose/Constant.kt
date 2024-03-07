package com.example.camp_calculator_with_compose

const val BUTTON_CORNER_ROUNDED_VALUE = 8
const val BUTTON_ROW_INTER_SPACING = 12
const val BUTTON_TEXT_SIZE = 32
const val BUTTON_TEXT_SIZE_UNDER = 24
const val SCREEN_TEXT_SIZE = 60
const val SCREEN_SUB_TEXT_SIZE = 36

const val OP_MULTIPLICATION = 1
const val OP_ADDITION = 2
const val OP_SUBTRACTION = 3
const val OP_DIVISION = 4
const val OP_EQUAL = 5
const val OP_INIT = 6
const val OP_SIGN_CHANGE = 7
const val OP_REMAINDER = 8

val numberClass = listOf(
    Double::class, Float::class, Long::class,
    Int::class, Short::class, Byte::class
)