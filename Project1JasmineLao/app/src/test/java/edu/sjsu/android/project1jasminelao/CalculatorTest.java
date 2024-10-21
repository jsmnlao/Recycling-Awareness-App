package edu.sjsu.android.project1jasminelao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {
    private void myTests(double P, double J, int N, double T, double expected){
        J = J / 12 / 100;
        N = N * 12;
        T = T / 100.0;
        double actual =  Calculator.calculate(P, J, N, T);
        assertEquals(actual, expected, 0.01); // don't differ more than 0.01
    }

    @Test
    public void testNoTax(){
        myTests(10000.0, 5.5, 15, 0.0, 81.71);
    }

    @Test
    public void testNoInterestRate(){
        myTests(20000.0, 0.0, 20, 0.0, 83.33);
    }

    @Test
    public void testWithTax(){
        double P = 10000.0;
        myTests(10000.0, 5.5, 15, 0.1 * P, 91.71);
    }
}
