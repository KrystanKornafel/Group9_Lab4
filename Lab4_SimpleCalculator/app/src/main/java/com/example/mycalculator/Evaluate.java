package com.example.mycalculator;

import java.math.BigDecimal;

//import javax.script.ScriptEngine;
//import javax.script.ScriptEngineManager;
//import javax.script.ScriptException;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Evaluate {
    //public ScriptEngine engine;
    public Evaluate(){
        //engine = new ScriptEngineManager().getEngineByName("rhino");
    }

    public BigDecimal evaluate(String expression) throws Exception {
        Expression exp = new ExpressionBuilder(expression).build();
        double result = exp.evaluate();
        BigDecimal decimal = new BigDecimal(result);
        return decimal;
    }
;}
