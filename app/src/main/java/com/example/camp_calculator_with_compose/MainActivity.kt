package com.example.camp_calculator_with_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.camp_calculator_with_compose.ui.theme.Camp_calculator_with_composeTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Camp_calculator_with_composeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScaffold()
                }
            }
        }
    }
}

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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Camp_calculator_with_composeTheme {
        MainScaffold()
    }
}