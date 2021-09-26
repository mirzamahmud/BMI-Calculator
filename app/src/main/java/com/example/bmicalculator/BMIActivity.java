package com.example.bmicalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity
{

    android.widget.Button recalculateBMI;
    TextView bmiDisplay, bmiCategory, gender;

    // to take data from previous activity
    Intent intent;

    ImageView imageView;

    String bmi;
    float bmiResult;
    String height, weight;
    float intHeight, intWeight;
    RelativeLayout background;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color = \"white\"></font>"));
        getSupportActionBar().setTitle("BMI Result");
        getWindow().setStatusBarColor(ContextCompat.getColor(BMIActivity.this, R.color.colorBlack));

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        // reference widgets
        recalculateBMI = (android.widget.Button) findViewById(R.id.recalculate_bmi);
        bmiDisplay = (TextView) findViewById(R.id.bmi_display);
        bmiCategory = (TextView) findViewById(R.id.bmi_category);
        gender = (TextView) findViewById(R.id.gender_display);
        background = (RelativeLayout) findViewById(R.id.content_layout);
        imageView = (ImageView) findViewById(R.id.image_view);

        intent = getIntent();

        height = intent.getStringExtra("Height");
        weight = intent.getStringExtra("Weight");

        intHeight = Float.parseFloat(height);
        intWeight = Float.parseFloat(weight);

        intHeight = intHeight / 100;

        bmiResult = intWeight / (intHeight * intHeight);

        bmi = Float.toString(bmiResult);

        if(bmiResult < 16)
        {
            bmiCategory.setText("Severe Thinness");
            background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.cross);
        }
        else if(bmiResult < 16.9 && bmiResult > 16)
        {
            bmiCategory.setText("Modarate Thinness");
            background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.warning);
        }
        else if(bmiResult < 18.4 && bmiResult > 17)
        {
            bmiCategory.setText("Mild Thinness");
            background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.warning);
        }
        else if(bmiResult < 25 && bmiResult > 18.4)
        {
            bmiCategory.setText("Normal");
            //background.setBackgroundColor(Color.YELLOW);
            imageView.setImageResource(R.drawable.ok);
        }

        else if(bmiResult < 29.4 && bmiResult > 25)
        {
            bmiCategory.setText("Over Weight");
            background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.warning);
        }
        else
        {
            bmiCategory.setText("Obese Class");
            background.setBackgroundColor(Color.RED);
            imageView.setImageResource(R.drawable.warning);
        }

        gender.setText(intent.getStringExtra("Gender"));
        bmiDisplay.setText(bmi);


        recalculateBMI.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(BMIActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

        });


    }
}