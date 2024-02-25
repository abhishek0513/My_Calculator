package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv , solutionTv;

    MaterialButton button_clr, button_openBracket, button_closeBracket;
    MaterialButton button_Multiply, button_Plus , button_Minus , button_Divide, button_Equal;
    MaterialButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    MaterialButton buttonAc, buttonDot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_textview);
        solutionTv = findViewById(R.id.solution_textview);

        assignID(button_clr, R.id.button_clr);
        assignID(button_openBracket, R.id.button_openBracket);
        assignID(button_closeBracket, R.id.button_closeBracket);
        assignID(button_Multiply, R.id.button_Multiply);
        assignID(button_Plus, R.id.button_Plus);
        assignID(button_Minus, R.id.button_Minus);
        assignID(button_Divide, R.id.button_Divide);
        assignID(button_Equal, R.id.button_Equal);
        assignID(button1, R.id.button_one);
        assignID(button2, R.id.button_two);
        assignID(button3, R.id.button_three);
        assignID(button4, R.id.button_four);
        assignID(button5, R.id.button_five);
        assignID(button6, R.id.button_six);
        assignID(button7, R.id.button_seven);
        assignID(button8, R.id.button_eight);
        assignID(button9, R.id.button_nine);
        assignID(button0, R.id.button_zero);
        assignID(buttonDot, R.id.button_dot);
        assignID(buttonAc, R.id.button_allclear);
    }

    void assignID(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String btnText  = button.getText().toString();

        String dataCal = solutionTv.getText().toString();
        if(btnText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if(btnText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }

        if(btnText.equals("Clr")){
            dataCal = dataCal.substring(0, dataCal.length()-1);
        }
        else{
            dataCal = dataCal + btnText;
        }

        solutionTv.setText(dataCal);
        String finalResult = getResult(dataCal);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            data = data.replaceAll("x", "*");
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript",1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch(Exception e){
            return "Err";
        }

    }
}