package com.example.camp_calculator_with_compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.camp_calculator_with_compose.calculator.Calculator
import com.example.camp_calculator_with_compose.tool.string2number

class MainViewModel: ViewModel() {

    private val _calculator = Calculator()

    private val _resultText = MutableLiveData("0")
    val resultText: LiveData<String> = _resultText

    private val _storeText = MutableLiveData("")
    val storeText: LiveData<String> = _storeText

    private val _operationText = MutableLiveData("")
    val operationText: LiveData<String> = _operationText

    private var _operationCode = -1

    val numericalButtonClickListener: (Int) -> Unit = {
        _resultText.value = if(_resultText.value!! == "0") {
            it.toString()
        } else _resultText.value + it.toString()
    }

    /**
     * 5가지 연산( +, -, *, /, % )를 제외한 연산은 따로 분기 처리 해주자.
     * 지금 매우 지저분 한 상황 그리고 지금 정수 밖에 출력이 안돼
     * */
    val operationButtonClickListener: (Int) -> Unit = {
        if(it == OP_INIT) {
            _operationCode = -1
            _operationText.value = ""
            _storeText.value = ""
            _resultText.value = "0"
        } else if(it == OP_SIGN_CHANGE) {
            if(_resultText.value != "0") _resultText.value = "-" + _resultText.value
        } else {
            if (_storeText.value == "") {
                _storeText.value = _resultText.value
                _operationCode = it
                _operationText.value = when (it) {
                    1 -> "x"
                    2 -> "+"
                    3 -> "-"
                    4 -> "/"
                    8 -> "%"
                    else -> "e"
                }
                _resultText.value = "0"
            } else {
                val num1 = string2number(_storeText.value!!)
                val num2 = string2number(_resultText.value!!)
                if (it == OP_EQUAL) {
                    _resultText.value = when (_operationCode) {
                        OP_MULTIPLICATION -> _calculator.multiplication(num1, num2).toString()
                        OP_ADDITION -> _calculator.addition(num1, num2).toString()
                        OP_SUBTRACTION -> _calculator.subtraction(num1, num2).toString()
                        OP_DIVISION -> _calculator.division(num1, num2).toString()
                        OP_REMAINDER -> _calculator.remainder(num1, num2).toString()
                        else -> throw Exception()
                    }
                    _storeText.value = ""
                    _operationCode = -1
                    _operationText.value = ""
                } else {
                    _storeText.value = when (_operationCode) {
                        OP_MULTIPLICATION -> {
                            _operationCode = OP_MULTIPLICATION
                            _operationText.value = "x"
                            _calculator.multiplication(num1, num2).toString()
                        }

                        OP_ADDITION -> {
                            _operationCode = OP_ADDITION
                            _operationText.value = "+"
                            _calculator.addition(num1, num2).toString()
                        }

                        OP_SUBTRACTION -> {
                            _operationCode = OP_SUBTRACTION
                            _operationText.value = "-"
                            _calculator.subtraction(num1, num2).toString()
                        }

                        OP_DIVISION -> {
                            _operationCode = OP_DIVISION
                            _operationText.value = "/"
                            _calculator.division(num1, num2).toString()
                        }

                        OP_REMAINDER -> {
                            _operationCode = OP_REMAINDER
                            _operationText.value = "%"
                            _calculator.remainder(num1, num2).toString()
                        }

                        else -> {
                            "0: ERROR"
                        }
                    }
                    _resultText.value = "0"
                }
            }
        }
    }
}