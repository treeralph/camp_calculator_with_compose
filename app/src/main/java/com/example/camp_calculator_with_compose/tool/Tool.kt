package com.example.camp_calculator_with_compose.tool

fun string2number(input: String): Number {
    val sign = (input.substring(0, 1) != "-") // positive -> true, negative -> false
    val target = if(sign) input else input.substring(1) // delete sign
    return if(target.contains(".")) { // Rational Number
        val integerPart = target.split(".")[0]
        if(integerPart.length >= 4) input.toDouble()
        else input.toFloat()
    } else { // Integer
        try { input.toByte() } catch(e: NumberFormatException) {
            try { input.toShort() } catch(e: NumberFormatException) {
                try { input.toInt() } catch(e: NumberFormatException) {
                    input.toLong()
                }
            }
        }
    }
}