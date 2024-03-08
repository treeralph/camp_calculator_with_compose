# Simple Android Calculator Application
### with jetpack compose

Main Feature is below.
~~~kotlin
fun string2number(input: String): Number {
    val sign = (input.substring(0, 1) != "-")
    val target = if(sign) input else input.substring(1)
    return if(target.contains(".")) {
        val integerPart = target.split(".")[0]
        if(integerPart.length >= 4) input.toDouble()
        else input.toFloat()
    } else {
        try { input.toByte() } catch(e: NumberFormatException) {
            try { input.toShort() } catch(e: NumberFormatException) {
                try { input.toInt() } catch(e: NumberFormatException) {
                    input.toLong()
                }
            }
        }
    }
}

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
~~~

ViewModel is below
~~~kotlin
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
~~~

Calculator Composable is below
~~~kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    viewModel: MainViewModel = viewModel(),
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BUTTON_ROW_INTER_SPACING.dp),
                text = viewModel.storeText.observeAsState(initial = "").value,
                fontWeight = FontWeight.Bold,
                fontSize = SCREEN_SUB_TEXT_SIZE.sp,
                style = TextStyle(textAlign = TextAlign.Right)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BUTTON_ROW_INTER_SPACING.dp),
                text = viewModel.operationText.observeAsState(initial = "").value,
                fontWeight = FontWeight.Bold,
                fontSize = SCREEN_SUB_TEXT_SIZE.sp,
                style = TextStyle(textAlign = TextAlign.Right)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BUTTON_ROW_INTER_SPACING.dp),
                text = viewModel.resultText.observeAsState(initial = "0").value,
                fontWeight = FontWeight.Bold,
                fontSize = SCREEN_TEXT_SIZE.sp,
                style = TextStyle(textAlign = TextAlign.Right)
            )
            Column(
                modifier = Modifier
                    .padding(BUTTON_ROW_INTER_SPACING.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(BUTTON_ROW_INTER_SPACING.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BUTTON_ROW_INTER_SPACING.dp)
                ) {
                    CalculatorButton(
                        onClickListener = { viewModel.operationButtonClickListener(OP_INIT) },
                        text = "C"
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.operationButtonClickListener(OP_SIGN_CHANGE) },
                        text = "+/-",
                        fontSize = BUTTON_TEXT_SIZE_UNDER
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.operationButtonClickListener(OP_REMAINDER) },
                        text = "%"
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.operationButtonClickListener(OP_DIVISION) },
                        text = "/"
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BUTTON_ROW_INTER_SPACING.dp)
                ) {
                    CalculatorButton(
                        onClickListener = { viewModel.numericalButtonClickListener(1) },
                        text = "1"
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.numericalButtonClickListener(2) },
                        text = "2"
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.numericalButtonClickListener(3) },
                        text = "3"
                    )
                    CalculatorButton(
                        onClickListener = {
                            viewModel.operationButtonClickListener(OP_MULTIPLICATION)
                        },
                        text = "x"
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BUTTON_ROW_INTER_SPACING.dp)
                ) {
                    CalculatorButton(
                        onClickListener = { viewModel.numericalButtonClickListener(4) },
                        text = "4"
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.numericalButtonClickListener(5) },
                        text = "5"
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.numericalButtonClickListener(6) },
                        text = "6"
                    )
                    CalculatorButton(
                        onClickListener = {
                            viewModel.operationButtonClickListener(OP_SUBTRACTION)
                        },
                        text = "-"
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BUTTON_ROW_INTER_SPACING.dp)
                ) {
                    CalculatorButton(
                        onClickListener = { viewModel.numericalButtonClickListener(7) },
                        text = "7"
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.numericalButtonClickListener(8) },
                        text = "8"
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.numericalButtonClickListener(9) },
                        text = "9"
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.operationButtonClickListener(OP_ADDITION) },
                        text = "+"
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(BUTTON_ROW_INTER_SPACING.dp)
                ) {
                    CalculatorButton(
                        onClickListener = { viewModel.numericalButtonClickListener(0) },
                        text = "0",
                        aspectRatioValue = 2f
                    )
                    CalculatorButton(
                        onClickListener = { viewModel.operationButtonClickListener(OP_EQUAL) },
                        text = "=",
                        aspectRatioValue = 2f
                    )
                }
            }
        }
    }
}

@Composable
fun RowScope.CalculatorButton(
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    weightValue: Float = 1f,
    aspectRatioValue: Float = 1f,
    text: String = "",
    fontSize: Int = BUTTON_TEXT_SIZE,
) {
    ElevatedButton(
        modifier = modifier
            .weight(weightValue)
            .aspectRatio(aspectRatioValue),
        onClick = onClickListener,
        shape = RoundedCornerShape(BUTTON_CORNER_ROUNDED_VALUE.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
    ) {
        Text(
            text = text,
            color = contentColor,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = TextStyle(textAlign = TextAlign.Center)
        )
    }
}
~~~
