package com.example.mycalculator;

import static org.junit.Assert.*;

import org.junit.Test;

public class EvaluateTest {

    //Test method -> Tests for addition
    //Bad test for addition
    @Test
    public void evaluate_add_bad() throws Exception {
        Evaluate e = new Evaluate ();
        double actual = e.evaluate("2+2").doubleValue();
        double expected = 5.0;
        //Bad test
        assertEquals("We have an error. Values don't match", expected, actual, 0.001);

    }


    //Good test for addition
    @Test
    public void evaluate_add_good() throws Exception {
        Evaluate e = new Evaluate();
        double actual = e.evaluate("3+3.0").doubleValue();
        double expected = 6.0;
        assertEquals("Values match", expected, actual, 0.001);
    }


}