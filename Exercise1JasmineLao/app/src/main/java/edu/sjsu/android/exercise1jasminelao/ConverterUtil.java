package edu.sjsu.android.exercise1jasminelao;

public class ConverterUtil {
    // Converts to Celsius
    public static float convertFahrenheitToCelsius(float fahrenheit){
        return ((fahrenheit -32) * 5 / 9);
    }

    //Converts to Fahrenheit
    public static float convertCelsiusToFahrenheit(float celsius){
        return ((celsius * 9) / 5) + 32;
    }
}
