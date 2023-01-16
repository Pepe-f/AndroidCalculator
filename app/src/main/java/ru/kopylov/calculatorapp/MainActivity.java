package ru.kopylov.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText firstNumEt, secondNumEt, operationEt;
    private TextView resultTv;
    private Button calculateButton;
    private Toast toastError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNumEt = findViewById(R.id.first_num_edit_text);
        secondNumEt = findViewById(R.id.second_num_edit_text);
        operationEt = findViewById(R.id.operation_edit_text);
        resultTv = findViewById(R.id.result_text_view);
        calculateButton = findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(this);
    }

    public void toastErrorHandler(int errorId) {
        if (toastError != null) {
            toastError.cancel();
        }

        toastError = Toast.makeText(this, errorId, Toast.LENGTH_SHORT);
        toastError.show();
    }

    @Override
    public void onClick(View view) {
        float firstNum, secondNum, result = 0;
        String operation;
        boolean isCorrectOperation = true;

        try {
            firstNum = Float.parseFloat(firstNumEt.getText().toString());
            secondNum = Float.parseFloat(secondNumEt.getText().toString());
            operation = operationEt.getText().toString();

            switch (operation) {
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "*":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    if (secondNum == 0) {
                        throw new ArithmeticException();
                    }
                    result = firstNum / secondNum;
                    break;
                default:
                    isCorrectOperation = false;
                    break;
            }
        } catch (ArithmeticException e) {
            toastErrorHandler(R.string.divide_zero);
            return;
        } catch (NullPointerException e) {
            toastErrorHandler(R.string.null_data);
            return;
        } catch (NumberFormatException e) {
            toastErrorHandler(R.string.wrong_format);
            return;
        }

        if (isCorrectOperation) {
            resultTv.setText(firstNum + " " + operation + " " + secondNum + " = " + result);
        } else {
            toastErrorHandler(R.string.wrong_operation);
        }
    }
}