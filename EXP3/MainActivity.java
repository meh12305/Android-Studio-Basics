package com.example.calu_42;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String currentInput = "";
    private boolean lastButtonWasOperator = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        // Initialize Buttons
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonSquare = findViewById(R.id.buttonSquare);
        Button buttonSquareRoot = findViewById(R.id.buttonSquareRoot);
        Button buttonDot = findViewById(R.id.buttonDot);
        Button buttonEqual = findViewById(R.id.buttonEqual);
        Button buttonC = findViewById(R.id.buttonC);
        Button buttonParenthesis = findViewById(R.id.buttonParenthesis);
        Button buttonSin = findViewById(R.id.buttonSin);
        Button buttonCos = findViewById(R.id.buttonCos);
        Button buttonTan = findViewById(R.id.buttonTan);
        Button buttonLog = findViewById(R.id.buttonLog);

        // Set button click listeners
        button0.setOnClickListener(v -> appendToInput("0"));
        button1.setOnClickListener(v -> appendToInput("1"));
        button2.setOnClickListener(v -> appendToInput("2"));
        button3.setOnClickListener(v -> appendToInput("3"));
        button4.setOnClickListener(v -> appendToInput("4"));
        button5.setOnClickListener(v -> appendToInput("5"));
        button6.setOnClickListener(v -> appendToInput("6"));
        button7.setOnClickListener(v -> appendToInput("7"));
        button8.setOnClickListener(v -> appendToInput("8"));
        button9.setOnClickListener(v -> appendToInput("9"));
        buttonDot.setOnClickListener(v -> appendToInput("."));

        buttonAdd.setOnClickListener(v -> appendOperator("+"));
        buttonSubtract.setOnClickListener(v -> appendOperator("-"));
        buttonMultiply.setOnClickListener(v -> appendOperator("*"));
        buttonDivide.setOnClickListener(v -> appendOperator("/"));

        buttonSquare.setOnClickListener(v -> appendToInput("^2"));
        buttonSquareRoot.setOnClickListener(v -> appendToInput("sqrt("));
        buttonSin.setOnClickListener(v -> appendToInput("sin("));
        buttonCos.setOnClickListener(v -> appendToInput("cos("));
        buttonTan.setOnClickListener(v -> appendToInput("tan("));
        buttonLog.setOnClickListener(v -> appendToInput("log("));

        buttonParenthesis.setOnClickListener(v -> {
            if (currentInput.isEmpty() || currentInput.endsWith("(")) {
                appendToInput("(");
            } else {
                appendToInput(")");
            }
        });

        buttonEqual.setOnClickListener(v -> calculateResult());

        buttonC.setOnClickListener(v -> {
            currentInput = "";
            editText.setText("");
            lastButtonWasOperator = false;
        });
    }

    private void appendToInput(String str) {
        currentInput += str;
        editText.setText(currentInput);
        lastButtonWasOperator = false;
    }

    private void appendOperator(String operator) {
        if (!lastButtonWasOperator && !currentInput.isEmpty()) {
            currentInput += " " + operator + " ";
            editText.setText(currentInput);
            lastButtonWasOperator = true;
        }
    }

    private void calculateResult() {
        try {
            // Replace function placeholders with actual Java methods
            String result = evaluateExpression(currentInput.replace("sqrt(", "Math.sqrt(")
                    .replace("sin(", "Math.sin(Math.toRadians(")
                    .replace("cos(", "Math.cos(Math.toRadians(")
                    .replace("tan(", "Math.tan(Math.toRadians(")
                    .replace("log(", "Math.log10(")
                    .replace("^", ""));
            editText.setText(result);
            currentInput = result;
        } catch (Exception e) {
            editText.setText("Error");
            currentInput = "";
        }
        lastButtonWasOperator = false;
    }

    private String evaluateExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                values.push(Double.parseDouble(sb.toString()));
                i--;
            } else if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(ch);
            } else if (ch == '^') {
                operators.push(ch);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop().toString();
    }

    private int precedence(char operator) {
        if (operator == '+' || operator == '-') return 1;
        if (operator == '*' || operator == '/') return 2;
        if (operator == '^') return 3;
        return -1;
    }

    private double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new UnsupportedOperationException("Operator not supported");
 }
    }
}