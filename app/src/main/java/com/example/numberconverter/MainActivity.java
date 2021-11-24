package com.example.numberconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner fromSpinner, toSpinner;
    private EditText enterNumb, result;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterNumb = findViewById(R.id.numberEntered);
        enterNumb.setShowSoftInputOnFocus(false);
        enterNumb.setText("");

        result = findViewById(R.id.result);
        result.setShowSoftInputOnFocus(false);
        result.setText("");

        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        String[] bases = {"2", "8", "10", "16"};
        ArrayAdapter ad = new ArrayAdapter<String>(this,R.layout.spinner_item, bases);
        fromSpinner.setAdapter(ad);
        toSpinner.setAdapter(ad);

        convertButton = findViewById(R.id.convert_button);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertNumber(fromSpinner.getSelectedItem().toString(), toSpinner.getSelectedItem().toString());
            }
        });
    }

    void convertNumber(String fSpinner, String tSpinner) {
        String numberEntered = enterNumb.getText().toString();
        int fBase = Integer.parseInt(fSpinner);
        int tBase = Integer.parseInt(tSpinner);

        if (numberEntered.equals(""))
            return;

        int decimalValue;
        String resStr = "";

        try {
            decimalValue = Integer.parseInt(numberEntered, fBase);
        } catch(Exception e) {
            result.setTextColor(Color.RED);
            result.setText("Wrong input type!");
            return;
        }


        switch (tBase) {
            case 2:
                resStr = Integer.toBinaryString(decimalValue);
                break;

            case 8:
                resStr = Integer.toOctalString(decimalValue);
                break;

            case 10:
                resStr = Integer.toString(decimalValue);
                break;

            case 16:
                resStr = Integer.toHexString(decimalValue).toUpperCase();
                break;
        }

        result.setTextColor(Color.BLACK);
        result.setText(resStr);
    }
}