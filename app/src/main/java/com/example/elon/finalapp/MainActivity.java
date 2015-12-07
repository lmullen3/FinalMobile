package com.example.elon.finalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText hourInput;
    private EditText minuteInput;
    private String hourText, minuteText, printerText;
    private int printerNum;
    private RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hourInput = (EditText) findViewById(R.id.hourInput);
        minuteInput = (EditText) findViewById(R.id.minuteInput);
    }

    public void onClickNext(View view) {

        hourText = hourInput.getText().toString();
        minuteText = minuteInput.getText().toString();

        Intent intent = new Intent(this, InputCheck.class);
        intent.putExtra("hourInput", hourText);
        intent.putExtra("minuteInput", minuteText);
        intent.putExtra("printerInput", printerNum);
        intent.putExtra("printerText",printerText);
        startActivity(intent);

    }

    public void onRadioButtonClicked(View view) {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        int id = radioGroup.getCheckedRadioButtonId();
        switch (id) {

            case R.id.fusion:
                printerNum = 1;
                printerText = "Fusion 3";
                break;

            case R.id.wanhao:
                printerNum = 2;
                printerText = "Wanhao";


                break;

        }

    }
}
