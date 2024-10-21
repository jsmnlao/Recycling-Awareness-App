package edu.sjsu.android.project1jasminelao;

public class Calculator {

    /**
     *
     * @param P - principle
     * @param J - monthly interest in decimal form
     * @param N - number of months of loan
     * @param T - monthly taxes and insurance
     * @return double result
     */
    public static double calculate(double P, double J, int N, double T){
        if(J == 0.0){
            return P / N + T;
        }
        else{
            double temp = 1 - Math.pow(1 + J, -N);
            return (P * J / temp) + T;
        }
    }
}
