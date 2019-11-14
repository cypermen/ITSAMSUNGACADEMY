package ru.samsung.itschool.book.equation243;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    // Вызывается при создании Активности
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Инициализирует Активность.
        setContentView(R.layout.activity_main);
    }

    /** Вызывается при нажатии пользователем на кнопку Решить */
        public void solveEquation(View view) {
             // ax+b=c
            double a = Double.parseDouble( ((EditText)
                    findViewById(R.id.coefficient_a)).getText().toString());
            double b = Double.parseDouble( ((EditText)
                    findViewById(R.id.coefficient_b)).getText().toString());
            double c = Double.parseDouble( ((EditText)
                    findViewById(R.id.coefficient_c)).getText().toString());
            TextView result = (TextView) findViewById(R.id.result);
            if(a == 0){
                if(b == 0 && c == 0){
                    result.setText("The equation has infinitely many solutions");
                }else{
                    if (b == 0) {
                        result.setText("Exception: division by zero");
                    }else{
                        result.setText("x = " + String.valueOf((-c) / b));
                    }
                }
            }else{
                double discr = b * b - 4 * a * c;
                if (discr == 0) {
                    result.setText("" + String.valueOf((-b) / ( 2 * a)));
                }else{
                    if(discr < 0){
                        result.setText("The equation has no real roots");
                    }else{
                        result.setText("x1 = " + String.valueOf(((-b) + Math.sqrt(discr)) / (2 * a))+"\nx2 = " + String.valueOf(((-b) - Math.sqrt(discr)) / (2 * a)));
                    }
                }
            }

        }

}
