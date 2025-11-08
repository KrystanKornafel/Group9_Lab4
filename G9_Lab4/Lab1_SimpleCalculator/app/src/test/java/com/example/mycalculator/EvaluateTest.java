package com.example.mycalculator;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.script.ScriptException;

/**
 * Test class
 */
public class EvaluateTest {

    //Test Method -> Tests for Addition
    @Test
    public void evaluate_add() throws ScriptException {
        Evaluate e = new Evaluate();
        double actual = e.evaluate("2+2").doubleValue();
        double expected = 5.0;
        //assertEquals("We have an error. Value don't match", expected, actual, 0.0001);
        actual = e.evaluate("2+3").doubleValue();
        assertEquals("This test passed. The values match!", expected, actual, 0.0001);
    }

}
