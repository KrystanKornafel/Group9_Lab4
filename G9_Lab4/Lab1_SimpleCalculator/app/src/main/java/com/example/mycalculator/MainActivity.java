package com.example.mycalculator;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Class name: MainActivity
 * Assignment: Lab 1
 * Written/modified by: Krystan Kornafel, Hailey Owen, and Ahmed Eldesouki
 * Completion date: Monday, Oct 6, 2025
 * Due date: Monday, Oct 6, 2025
 * Purpose: This class performs all the operations for the calculator program. It is responsible
 * for taking in the input from the user, generating a result based off the information provided,
 * and printing the result to the user.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Creating our buttons that will be used by the program for processing the input from the user
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btn_dot, btn_minus, btn_mult, btn_division, btn_clear, btn_plus, btn_equal;
    //Creating an object of type TextView to set and get the text that will be printed out to the user
    TextView text_display;

    // This is to evaluate the math expression
    ScriptEngine engine;

    /**
     * Method name: onCreate
     * @param savedInstanceState
     * Purpose: This program overrides the default version of this method, and is used to initialize
     * the components of the calculator UI, namely the buttons, view, and so on.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Initialize a view for the program's UI.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Used to retrieve JavaScript code to be used by the program
        engine = new ScriptEngineManager().getEngineByName("rhino");

        //Initializing all the buttons that will be used by the program
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_mult = (Button) findViewById(R.id.btn_mult);
        btn_division = (Button) findViewById(R.id.btn_division);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        text_display = (TextView) findViewById(R.id.textview_input_display);

        //This will be used by the program when a button is clicked
        setClickListeners();
    }

    /**
     * Method name: setClickListeners
     * Purpose: Allows the program to click on a particular button/view.
     */
    private void setClickListeners() {
        //To be used when the buttons are clicked on. This makes them able to process the input, and
        //notifies the particular button object when clicked on.
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_mult.setOnClickListener(this);
        btn_division.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    /**
     * Method name: onClick
     * @param v to be used when the program wishes to know which button/view was clicked on.
     * Purpose: This method allows the program to perform a specific operation when a particular
     * button/view is clicked on.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //Add "1" to the string that will be outputted to the user
                addNumber("1");
                break;
            case R.id.btn2:
                //Add "2" to the string that will be outputted to the user
                addNumber("2");
                break;
            case R.id.btn3:
                //Add "3" to the string that will be outputted to the user
                addNumber("3");
                break;
            case R.id.btn4:
                //Add "4" to the string that will be outputted to the user
                addNumber("4");
                break;
            case R.id.btn5:
                //Add "5" to the string that will be outputted to the user
                addNumber("5");
                break;
            case R.id.btn6:
                //Add "6" to the string that will be outputted to the user
                addNumber("6");
                break;
            case R.id.btn7:
                //Add "7" to the string that will be outputted to the user
                addNumber("7");
                break;
            case R.id.btn8:
                //Add "8" to the string that will be outputted to the user
                addNumber("8");
                break;
            case R.id.btn9:
                //Add "9" to the string that will be outputted to the user
                addNumber("9");
                break;
            case R.id.btn0:
                //Add "0" to the string that will be outputted to the user
                addNumber("0");
                break;
            case R.id.btn_dot:
                //Add "." to the string that will be outputted to the user
                addNumber(".");
                break;
            case R.id.btn_plus:
                //Add "+" to the string that will be outputted to the user
                //The system will use "+" as an indicator to perform the add operation
                addNumber("+");
                break;
            case R.id.btn_minus:
                //Add "-" to the string that will be outputted to the user
                //The system will use "-" as an indicator to perform the subtract operation
                addNumber("-");
                break;
            case R.id.btn_mult:
                //Add "*" to the string that will be outputted to the user
                //The system will use "*" as an indicator to perform the multiplication operation
                addNumber("*");
                break;
            case R.id.btn_division:
                //Add "/" to the string that will be outputted to the user
                //The system will use "/" as an indicator to perform the division operation
                addNumber("/");
                break;
            case R.id.btn_equal:
                //Create a variable of type String to take in the resulting string after the
                //specified operation is complete.
                String result = null;
                //error checking in place to ensure the program does not crash
                try {
                    //Retrieve the text stored in the setters and getters, and store this string
                    //in the result variable
                    result = evaluate(text_display.getText().toString());
                    //Add the current string into the setters so that the text can be retrieved
                    //and printed.
                    text_display.setText(result);
                } catch (ScriptException e) {
                    //If an error occurs, print error message to the user
                    text_display.setText("Error");
                }
                break;
            case R.id.btn_clear:
                //If the user presses the clear button, reset everything so that the getters and
                //setters can retrieve the new information entered by the user.
                clear_display();
                break;
            default:
                text_display.setText("Enter value"); //default case
        }
    }

    /**
     * Method name: evaluate
     * @param expression is the string of the input entered by the user
     * @return returns a rounded version of the value generated by the program after the
     * specified operation is complete.
     * @throws ScriptException if it can't perform the operation
     * Purpose: To evaluate the string entered in by the user, generate the result and return
     * the result so that it can be used by the program to output the calculated result.
     */
    private String evaluate(String expression) throws ScriptException {
        //Evaluate the string expression entered in by the user
        String result = engine.eval(expression).toString();
        //Store the result of the operation into an object of type BigDecimal
        BigDecimal decimal = new BigDecimal(result);
        //Return a rounded version (to 2 decimal places) of the value calculated from the
        //specified operation performed.
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * Method name: addNumber
     * @param number is the string value entered by the user
     * Purpose: To add the new text from the user into the getters and setters, so that the text
     * can be displayed later on in the program.
     */
    private void addNumber(String number) {
        text_display.setText(text_display.getText() + number);
    }

    /**
     * Method name: clear_display
     * Purpose: to reset the text so that new inputs can be entered in, evaluated and printed out.
     * Reset the text so that the old text does not remain in memory.
     */
    private void clear_display() {
        //Reset the text in the setter to "" so that new input can be entered and stored for
        //future use by the program
        text_display.setText("");
    }
}
