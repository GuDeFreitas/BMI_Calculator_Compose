package com.gustavo.bmicalculatorcompose.calculate

import java.text.DecimalFormat

class CalculateBmi {

    private var bmiResult = ""

    fun calculateBmi(weight: String, height: String){

        val convertedWeight = weight.toDouble()
        val convertedHeight = height.toDouble()
        val result: String

        val bmi = convertedWeight / (convertedHeight * convertedHeight)
        val decimalFormat = DecimalFormat("0.00")

        result = if (bmi <= 18.5) {
            "Under Weight \n BMI: ${decimalFormat.format(bmi)}"
        }else if (bmi <= 24.9) {
            "Normal Weight \n BMI: ${decimalFormat.format(bmi)}"
        }else if (bmi <= 29.9) {
            "Over Weight \n BMI: ${decimalFormat.format(bmi)}"
        }else if (bmi <= 34.9) {
            "Grade I Obesity \n BMI: ${decimalFormat.format(bmi)}"
        }else if (bmi <= 39.9) {
            "Grade II Severe Obesity \n BMI: ${decimalFormat.format(bmi)}"
        }else{
            "Grade III Morbid Obesity \n BMI: ${decimalFormat.format(bmi)}"
        }
        bmiResult = result
    }

    fun bmiResult(): String{
        return bmiResult
    }

}