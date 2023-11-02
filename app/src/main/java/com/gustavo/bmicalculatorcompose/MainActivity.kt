package com.gustavo.bmicalculatorcompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gustavo.bmicalculatorcompose.calculate.CalculateBmi
import com.gustavo.bmicalculatorcompose.ui.theme.BMICalculatorComposeTheme
import com.gustavo.bmicalculatorcompose.ui.theme.DARK_GREEN
import com.gustavo.bmicalculatorcompose.ui.theme.LIGHT_GREEN
import com.gustavo.bmicalculatorcompose.ui.theme.WHITE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMICalculatorComposeTheme {
                BmiCalculator()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BmiCalculator() {

    val context = LocalContext.current
    val calculateBmi = CalculateBmi()

    var weight by remember {
        mutableStateOf("")
    }

    var height by remember {
        mutableStateOf("")
    }

    var resultText by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = LIGHT_GREEN,
                title = {
                    Text(text = "BMI Calculator", color = WHITE)
                },
                actions = {
                    IconButton(
                        onClick = {
                            weight = ""
                            height = ""
                            resultText = ""
                        }
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_refresh),
                            contentDescription = "Icon to reset all fields"
                        )
                    }
                }
            )
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            Text(
                text = "BMI Calculator",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = LIGHT_GREEN,
                modifier = Modifier.padding(50.dp)
            )

            OutlinedTextField(
                value = weight,
                onValueChange = {
                    weight = it
                },
                label = {
                    Text(text = "Weight (kg)")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = LIGHT_GREEN,
                    focusedBorderColor = LIGHT_GREEN,
                    textColor = DARK_GREEN,
                    focusedLabelColor = DARK_GREEN
                ),
                textStyle = TextStyle(DARK_GREEN, 28.sp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                value = height,
                onValueChange = {
                    height = it
                },
                label = {
                    Text(text = "Height")
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    cursorColor = LIGHT_GREEN,
                    focusedBorderColor = LIGHT_GREEN,
                    textColor = DARK_GREEN,
                    focusedLabelColor = DARK_GREEN
                ),
                textStyle = TextStyle(DARK_GREEN, 28.sp),
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            Button(
                onClick = {
                    if (weight.isEmpty() || height.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Complete all fields!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        calculateBmi.calculateBmi(weight, height)
                        resultText = calculateBmi.bmiResult()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = LIGHT_GREEN,
                    contentColor = WHITE
                )
            ) {
                Text(
                    text = "Calculate BMI",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = resultText,
                fontSize = 18.sp,
                color = DARK_GREEN,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMICalculatorComposeTheme {
        BmiCalculator()
    }
}