package com.example.tugasamstr.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugasamstr.R;

public class CalorieActivity extends AppCompatActivity {

    int genderPicked;
    double activityPicked, numCalorieResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);

        final EditText inputAge = findViewById(R.id.calorieAgeInput);
        final EditText inputWeight = findViewById(R.id.calorieWeightInput);
        final EditText inputHeight = findViewById(R.id.calorieHeightInput);

        final RadioGroup pickGender = findViewById(R.id.radioGroup);
        final RadioGroup pickActivity = findViewById(R.id.radioGroup2);

        final TextView calorieResult = findViewById(R.id.calorieResult);

        Button calorieCalculate = findViewById(R.id.calorieCalculate);
        final Button calorieReset = findViewById(R.id.calorieReset);

        calorieCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String strAge = inputAge.getText().toString();
                final String strWeight = inputWeight.getText().toString();
                final String strHeight = inputHeight.getText().toString();

                if (TextUtils.isEmpty(strAge)) {

                    inputAge.setError("Masukkan umur Anda");

                }

                if (TextUtils.isEmpty(strWeight)) {

                    inputWeight.setError("Masukkan berat badan Anda");

                }

                if (TextUtils.isEmpty(strHeight)) {

                    inputHeight.setError("Masukkan tinggi badan Anda");

                }

                int checkedGender = pickGender.getCheckedRadioButtonId();
                int checkedActivity = pickActivity.getCheckedRadioButtonId();

                if (checkedGender == -1) {

                    Toast.makeText(CalorieActivity.this, "Pilih gender Anda", Toast.LENGTH_SHORT).show();

                } else {

                    findGenderRadioButton(checkedGender);

                }

                if (checkedActivity == -1) {

                    Toast.makeText(CalorieActivity.this, "Pilih aktivitas Anda", Toast.LENGTH_SHORT).show();

                } else {

                    findActivityRadioButton(checkedActivity);

                }

                if (!TextUtils.isEmpty(strAge) && !TextUtils.isEmpty(strWeight) && !TextUtils.isEmpty(strHeight) && checkedGender != -1 && checkedActivity != -1) {


                    numCalorieResult = ((Double.parseDouble(strWeight) * 10) + (Double.parseDouble(strHeight) * 6.25) - (Double.parseDouble(strAge) * 5) + genderPicked) * activityPicked;

                    String strCalorieResult = "Saran Sajian Kalori Per Hari: " + Double.toString(numCalorieResult) + " kkal";

                    calorieResult.setText(strCalorieResult);

                }


            }

        });

        calorieReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputAge.setText(null);
                inputWeight.setText(null);
                inputHeight.setText(null);
                pickGender.clearCheck();
                pickActivity.clearCheck();
                calorieResult.setText("Saran Sajian Kalori Per Hari: ");

            }
        });

    }

    private int findGenderRadioButton(int pickedGender){

        switch (pickedGender){

            case R.id.calorieGender1:

                genderPicked = 5;
                break;

            case R.id.calorieGender2:

                genderPicked = -161;
                break;

            default:

                genderPicked = 0;
                break;
        }

        return genderPicked;

    }

    private double findActivityRadioButton(int pickedActivity){

        switch (pickedActivity){


            case R.id.calorieButton1:

                activityPicked = 1.2;
                break;

            case R.id.calorieButton2:

                activityPicked = 1.375;
                break;

            case R.id.calorieButton3:

                activityPicked = 1.55;
                break;

            case R.id.calorieButton4:

                activityPicked = 1.725;
                break;

            case R.id.calorieButton5:

                activityPicked = 1.9;
                break;

            default:

                activityPicked = 0;
                break;

        }
        Log.d("messageActivity", String.valueOf(activityPicked));
        return activityPicked;

    }


}
