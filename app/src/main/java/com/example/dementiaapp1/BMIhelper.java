package com.example.dementiaapp1;

public class BMIhelper {
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public double calculateBMI(double heightCm, double weightKg) {
        double bmiIndex = -1;
        if (heightCm <= 0 && weightKg <= 0) {
            errorMsg = "Height and weight can't be zero or less";
        } else {
            bmiIndex = weightKg / ((heightCm / 100) * (heightCm / 100));
        }
        return bmiIndex;
    }

    public String determineCategory(double bmiIndex) {
        String bmiCategory = "";
        if (bmiIndex < 16) {
            bmiCategory = "Severe Thinness";
        } else if (bmiIndex >= 16 && bmiIndex < 17) {
            bmiCategory = "Moderate Thinness";
        } else if (bmiIndex >= 17 && bmiIndex < 18.5) {
            bmiCategory = "Mild Thinness";
        } else if (bmiIndex >= 18.5 && bmiIndex < 25) {
            bmiCategory = "Normal";
        } else if (bmiIndex >= 25 && bmiIndex < 30) {
            bmiCategory = "Overweight";
        } else if (bmiIndex >= 30 && bmiIndex < 35) {
            bmiCategory = "Obese Class I";
        } else if (bmiIndex >= 35 && bmiIndex < 40) {
            bmiCategory = "Obese Class II";
        } else if (bmiIndex >= 40) {
            bmiCategory = "Obese Class III";
        }
        return bmiCategory;
    }
}
