package com.joel.finalproject3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView Result,Solution;
    MaterialButton btnC,btnBracketOpen,btnBracketClose;
    MaterialButton btnDivide, btnMultiply, btnPlus, btnMinus, btnEqual;
    MaterialButton btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    MaterialButton btnAC,btnPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Result = findViewById(R.id.result);
        Solution = findViewById(R.id.solution);

        assignId(btnC, R.id.buttonC);
        assignId(btnBracketClose, R.id.buttonCloseBracket);
        assignId(btnBracketOpen, R.id.buttonOpenBracket);
        assignId(btnDivide, R.id.buttonDivide);
        assignId(btnMultiply, R.id.buttonMultiply);
        assignId(btnPlus, R.id.buttonPlus);
        assignId(btnMinus, R.id.buttonMinus);
        assignId(btnEqual, R.id.buttonEqual);
        assignId(btn0, R.id.buttonZero);
        assignId(btn1, R.id.buttonOne);
        assignId(btn2, R.id.buttonTwo);
        assignId(btn3, R.id.buttonThree);
        assignId(btn4, R.id.buttonFour);
        assignId(btn5, R.id.buttonFive);
        assignId(btn6, R.id.buttonSix);
        assignId(btn7, R.id.buttonSeven);
        assignId(btn8, R.id.buttonEight);
        assignId(btn9, R.id.buttonNine);
        assignId(btnAC, R.id.buttonAC);
        assignId(btnPoint, R.id.buttonPoint);

    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = Solution.getText().toString();

        if(buttonText.equals("AC")){
            Solution.setText("");
            Result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            Solution.setText(Result.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate=dataToCalculate+buttonText;
        }
        Solution.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("err")){
            Result.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "err";
        }
    }
}