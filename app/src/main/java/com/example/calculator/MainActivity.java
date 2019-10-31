package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	TextView resultText;
	Button buttonDegText;
	Button buttonSinText;
	Button buttonCosText;
	Button buttonTanText;

	boolean dotInNumber = false;
	boolean mathOperationOnBoard = false;
	boolean twond = false;
	boolean degree = true;
	char mathOperation;
	String enteredNumber = "";
	double firstNumber;

	public void number(String numberSymbol) {
		enteredNumber = enteredNumber + numberSymbol;
		resultOnBoard();
	}

	public void simpleMathOperation(char mathOperationSymbol) {
		if (enteredNumber != "") {
			if (mathOperationOnBoard == false) {
				firstNumber = Double.valueOf(enteredNumber);
				mathOperationOnBoard = true;
			} else
				firstNumber = simpleMathOperationExecution(firstNumber, Double.valueOf(enteredNumber), mathOperation);
			mathOperation = mathOperationSymbol;
			enteredNumber = "";
			dotInNumber = false;
		} else if (mathOperationOnBoard == true)
			mathOperation = mathOperationSymbol;
		resultOnBoard();
	}

	public static double simpleMathOperationExecution(double firstNumber, double secondNumber, char mathOperation) {
		double mathResult = 0;

		switch (mathOperation) {
			case '+':
				mathResult = firstNumber + secondNumber;
				break;
			case '-':
				mathResult = firstNumber - secondNumber;
				break;
			case '*':
				mathResult = firstNumber * secondNumber;
				break;
			case '/':
				if (secondNumber != 0)
					mathResult = firstNumber / secondNumber;
				else
					mathResult = 0;
				break;
			case '^':
				mathResult = Math.pow(firstNumber, secondNumber);
				break;
			case '%':
				mathResult = firstNumber / 100 * secondNumber;
				break;
		}
		return mathResult;
	}

	public void advancedMathOperation(String mathOperationSymbol) {
		if (enteredNumber != "") {
			if (mathOperationOnBoard == false)
				firstNumber = Double.valueOf(enteredNumber);
			else
				firstNumber = simpleMathOperationExecution(firstNumber, Double.valueOf(enteredNumber), mathOperation);

			switch (mathOperationSymbol) {

				case "1/x":
					if (firstNumber != 0)
						enteredNumber = String.valueOf(1 / firstNumber);
					else
						enteredNumber = String.valueOf(firstNumber);
					break;

				case "x!":
					//
					break;

				case "root":
					if (firstNumber >= 0)
						enteredNumber = String.valueOf(Math.sqrt(firstNumber));
					else
						enteredNumber = String.valueOf(firstNumber);
					break;

				case "x_sq":
					enteredNumber = String.valueOf(Math.pow(firstNumber, 2));
					break;

				case "x_thD":
					enteredNumber = String.valueOf(Math.pow(firstNumber, 3));
					break;

				case "sin":
					if (twond == false) {
						if (degree == true)
							firstNumber = Math.toRadians(firstNumber);
						else
							firstNumber = firstNumber;
						enteredNumber = String.valueOf(Math.sin(firstNumber));
					} else {
						if (firstNumber >= -1 && firstNumber <= 1)
							enteredNumber = String.valueOf(Math.toDegrees(Math.asin(firstNumber)));
						else
							enteredNumber = String.valueOf(firstNumber);
					}
					break;

				case "cos":
					if (twond == false) {
						if (degree == true)
							firstNumber = Math.toRadians(firstNumber);
						else
							firstNumber = firstNumber;
						enteredNumber = String.valueOf(Math.cos(firstNumber));
					} else {
						if (firstNumber >= -1 && firstNumber <= 1)
							enteredNumber = String.valueOf(Math.toDegrees(Math.acos(firstNumber)));
						else
							enteredNumber = String.valueOf(firstNumber);
					}
					break;

				case "tan":
					double checkTan = firstNumber;
					if (degree == false)
						checkTan = Math.toDegrees(checkTan);
					else
						checkTan = checkTan;
					if (checkTan > 360)
						while (checkTan > 360)
							checkTan = checkTan - 360;
					if (checkTan < -360)
						while (checkTan < -360)
							checkTan = checkTan + 360;
					if (checkTan == 90 || checkTan == -90)
						enteredNumber = String.valueOf(firstNumber);
					else {
						if (twond == false) {
							if (degree == true)
								firstNumber = Math.toRadians(firstNumber);
							else
								firstNumber = firstNumber;
							enteredNumber = String.valueOf(Math.tan(firstNumber));
						} else
							enteredNumber = String.valueOf(Math.toDegrees(Math.atan(firstNumber)));
					}
					break;

				case "ln":
					if (firstNumber >= 0)
						enteredNumber = String.valueOf(Math.log(firstNumber));
					else
						enteredNumber = String.valueOf(firstNumber);
					break;

				case "lg":
					if (firstNumber >= 0)
						enteredNumber = String.valueOf(Math.log10(firstNumber));
					else
						enteredNumber = String.valueOf(firstNumber);
					break;

			}

			String[] separatedEnteredNumber = enteredNumber.split("\\.");
			if (Double.valueOf(separatedEnteredNumber[1]) == 0) {
				enteredNumber = separatedEnteredNumber[0];
				dotInNumber = false;
			} else {
				enteredNumber = String.valueOf(Math.round(Double.valueOf(enteredNumber) * 100000000000d) / 100000000000d);
				dotInNumber = true;
			}
			firstNumber = 0;
			mathOperationOnBoard = false;
			resultOnBoard();
		}
	}

	public void resultOnBoard() {
		firstNumber = Math.round(firstNumber * 100000000000d) / 100000000000d;
		String[] separatedFirstNumber = String.valueOf(firstNumber).split("\\.");
		if (firstNumber != 0 || mathOperationOnBoard == true) {
			if (Double.valueOf(separatedFirstNumber[1]) == 0 && firstNumber < 10000000)
				resultText.setText(String.format("%s%s%s", separatedFirstNumber[0], mathOperation, enteredNumber));
			else
				resultText.setText(String.format("%s%s%s", firstNumber, mathOperation, enteredNumber));
		} else
			resultText.setText(enteredNumber);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		resultText = findViewById(R.id.textView_field);
		buttonSinText = findViewById(R.id.button_sin);
		buttonCosText = findViewById(R.id.button_cos);
		buttonTanText = findViewById(R.id.button_tan);
		buttonDegText = findViewById(R.id.button_degree);

		findViewById(R.id.button_null).setOnClickListener(this);
		findViewById(R.id.button_one).setOnClickListener(this);
		findViewById(R.id.button_two).setOnClickListener(this);
		findViewById(R.id.button_three).setOnClickListener(this);
		findViewById(R.id.button_four).setOnClickListener(this);
		findViewById(R.id.button_five).setOnClickListener(this);
		findViewById(R.id.button_six).setOnClickListener(this);
		findViewById(R.id.button_seven).setOnClickListener(this);
		findViewById(R.id.button_eight).setOnClickListener(this);
		findViewById(R.id.button_nine).setOnClickListener(this);
		findViewById(R.id.button_e).setOnClickListener(this);
		findViewById(R.id.button_pi).setOnClickListener(this);
		findViewById(R.id.button_plus).setOnClickListener(this);
		findViewById(R.id.button_minus).setOnClickListener(this);
		findViewById(R.id.button_multiply).setOnClickListener(this);
		findViewById(R.id.button_divide).setOnClickListener(this);
		findViewById(R.id.button_dot).setOnClickListener(this);
		findViewById(R.id.button_1_x).setOnClickListener(this);
		findViewById(R.id.button_x_fact).setOnClickListener(this);
		findViewById(R.id.button_root).setOnClickListener(this);
		findViewById(R.id.button_x_sq).setOnClickListener(this);
		findViewById(R.id.button_x_thD).setOnClickListener(this);
		findViewById(R.id.button_x_y).setOnClickListener(this);
		findViewById(R.id.button_percent).setOnClickListener(this);
		findViewById(R.id.button_2nd).setOnClickListener(this);
		findViewById(R.id.button_degree).setOnClickListener(this);
		findViewById(R.id.button_sin).setOnClickListener(this);
		findViewById(R.id.button_cos).setOnClickListener(this);
		findViewById(R.id.button_tan).setOnClickListener(this);
		findViewById(R.id.button_ln).setOnClickListener(this);
		findViewById(R.id.button_lg).setOnClickListener(this);
		findViewById(R.id.button_bracketL).setOnClickListener(this);
		findViewById(R.id.button_bracketR).setOnClickListener(this);
		findViewById(R.id.button_clear).setOnClickListener(this);
		findViewById(R.id.button_equation).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

			case R.id.button_null:
				number("0");
				break;

			case R.id.button_one:
				number("1");
				break;

			case R.id.button_two:
				number("2");
				break;

			case R.id.button_three:
				number("3");
				break;

			case R.id.button_four:
				number("4");
				break;

			case R.id.button_five:
				number("5");
				break;

			case R.id.button_six:
				number("6");
				break;

			case R.id.button_seven:
				number("7");
				break;

			case R.id.button_eight:
				number("8");
				break;

			case R.id.button_nine:
				number("9");
				break;

			case R.id.button_e:
				if (dotInNumber == false) {
					number("2.71828");
					dotInNumber = true;
				}
				break;

			case R.id.button_pi:
				if (dotInNumber == false) {
					number("3.14152");
					dotInNumber = true;
				}
				break;

			case R.id.button_plus:
				simpleMathOperation('+');
				break;

			case R.id.button_minus:
				simpleMathOperation('-');
				break;

			case R.id.button_multiply:
				simpleMathOperation('*');
				break;

			case R.id.button_divide:
				simpleMathOperation('/');
				break;

			case R.id.button_dot:
				if (dotInNumber == false) {
					dotInNumber = true;
					number(".");
				}
				break;

			case R.id.button_1_x:
				advancedMathOperation("1/x");
				break;

			case R.id.button_x_fact:
				advancedMathOperation("x!");
				break;

			case R.id.button_root:
				advancedMathOperation("root");
				break;

			case R.id.button_x_sq:
				advancedMathOperation("x_sq");
				break;

			case R.id.button_x_thD:
				advancedMathOperation("x_thD");
				break;

			case R.id.button_x_y:
				simpleMathOperation('^');
				break;

			case R.id.button_percent:
				simpleMathOperation('%');
				break;

			case R.id.button_2nd:
				if (degree == true)
					twond = !twond;
				if (twond == false) {
					buttonSinText.setText("sin");
					buttonCosText.setText("cos");
					buttonTanText.setText("tan");
				} else {
					buttonSinText.setText("asin");
					buttonCosText.setText("acos");
					buttonTanText.setText("atan");
				}
				break;

			case R.id.button_degree:
				if (twond == false)
					degree = !degree;
				if (degree == true)
					buttonDegText.setText("deg");
				else
					buttonDegText.setText("rad");
				break;

			case R.id.button_sin:
				advancedMathOperation("sin");
				break;

			case R.id.button_cos:
				advancedMathOperation("cos");
				break;

			case R.id.button_tan:
				advancedMathOperation("tan");
				break;

			case R.id.button_ln:
				advancedMathOperation("ln");
				break;

			case R.id.button_lg:
				advancedMathOperation("lg");
				break;

			case R.id.button_bracketL:
				//
				break;

			case R.id.button_bracketR:
				//
				break;

			case R.id.button_clear:
				dotInNumber = false;
				mathOperationOnBoard = false;
				twond = false;
				degree = true;
				mathOperation = 0;
				enteredNumber = "";
				firstNumber = 0;
				resultOnBoard();
				break;

			case R.id.button_equation:
				if (mathOperationOnBoard == true) {
					enteredNumber = String.valueOf(simpleMathOperationExecution(firstNumber, Double.valueOf(enteredNumber), mathOperation));
					enteredNumber = String.valueOf(Math.round(Double.valueOf(enteredNumber) * 100000000000d) / 100000000000d);
					String[] separatedEnteredNumber = enteredNumber.split("\\.");
					if (Double.valueOf(separatedEnteredNumber[1]) == 0) {
						enteredNumber = separatedEnteredNumber[0];
						dotInNumber = false;
					} else
						dotInNumber = true;
					firstNumber = 0;
					mathOperation = 0;
					mathOperationOnBoard = false;
					resultOnBoard();
				}
				break;
		}
	}
}