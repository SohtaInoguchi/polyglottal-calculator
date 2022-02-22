package com.example.polyglottal_calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private EditText mEditTextTargetWeight;
    private DatePicker mTargetDate;
    private EditText mEditTextWeight;
    private EditText mEditTextHeight;
    private EditText mEditTextName;
    private EditText mEditTextAge;
    private Spinner mSpinnerGender;
    private Spinner mSpinnerActivity;
    private TextView mTextViewResult;
    private Button mButtonCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditTextWeight = findViewById(R.id.input_weight);
        mEditTextTargetWeight = findViewById(R.id.target_weight);
        mTargetDate = findViewById(R.id.target_date);

        mEditTextHeight = findViewById(R.id.input_height);
        mEditTextName = findViewById(R.id.input_name);
        mEditTextAge = findViewById(R.id.input_age);
        mSpinnerGender = findViewById(R.id.input_gender);
        mSpinnerActivity = findViewById(R.id.activity_level);
        mTextViewResult = findViewById(R.id.result);
        mButtonCalc = findViewById(R.id.calc_button);

        mButtonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int weight = Integer.parseInt(mEditTextWeight.getText().toString());
                int targetWeight = Integer.parseInt(mEditTextName.getText().toString());
                int weightDiff = targetWeight - weight;
                int calNeeded = 7200 * weightDiff;

                LocalDate curDate = LocalDate.now();
                System.out.println(curDate);

                int height = Integer.parseInt(mEditTextHeight.getText().toString());
                int age = Integer.parseInt(mEditTextAge.getText().toString());
                String name = mEditTextName.getText().toString();
                Boolean isMale = mSpinnerGender.getSelectedItem().toString().equals("Male");
                String activity = mSpinnerActivity.getSelectedItem().toString();
                double bmr;
                double coefficient;

                if (activity.equals("Sedantary")) {
                    coefficient = 1.2;
                } else if (activity.equals("Light activity")) {
                    coefficient = 1.375;
                } else if (activity.equals(("Moderate Activity"))) {
                    coefficient = 1.55;
                } else if (activity.equals("Very Active")) {
                    coefficient = 1.9;
                } else {
                    coefficient = 1.0;
                }

                if (isMale) {
                    bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
                } else {
                    bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
                }

                double amr = coefficient * bmr;
                System.out.println(amr);
                mTextViewResult.setText(String.format("%4.3f", amr));

            }
        });
    }
}