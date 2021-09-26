package com.example.bmicalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    // variables of widgets
    android.widget.Button calculateBMIButton;
    TextView currentHeight;
    TextView currentWeight, currentAge;
    ImageView incrementAge, decrementAge;
    ImageView incrementWeight, decrementWeight;
    SeekBar heightSeekBar;
    RelativeLayout maleFocus, femaleFocus;

    int weightText = 55;
    int ageText = 25;
    int currentProgress;
    String intProgress = "170";
    String typeofUser = "0";
    String weightText2 = "55";
    String ageText2 = "25";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide actionbar
        getSupportActionBar().hide();
        // set statusbar color
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorBlack));

        // reference widgets
        calculateBMIButton = (android.widget.Button) findViewById(R.id.calculate_bmi);
        currentAge = (TextView) findViewById(R.id.current_age);
        currentWeight = (TextView) findViewById(R.id.current_weight);
        currentHeight = (TextView) findViewById(R.id.current_height);
        incrementAge = (ImageView) findViewById(R.id.increment_age);
        decrementAge = (ImageView) findViewById(R.id.decrement_age);
        incrementWeight = (ImageView) findViewById(R.id.increment_weight);
        decrementWeight = (ImageView) findViewById(R.id.decrement_weight);
        heightSeekBar = (SeekBar) findViewById(R.id.height_seekbar);
        maleFocus = (RelativeLayout) findViewById(R.id.male_focus);
        femaleFocus = (RelativeLayout) findViewById(R.id.female_focus);

        // add event to the male focus
        maleFocus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                maleFocus.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.male_female_focus));
                femaleFocus.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.male_female_notfocus));

                typeofUser = "Male";


            }
        });

        // add event to the female focus
        femaleFocus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                femaleFocus.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.male_female_focus));
                maleFocus.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.male_female_notfocus));

                typeofUser = "Female";
            }
        });

        heightSeekBar.setMax(300);
        heightSeekBar.setProgress(170);
        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                currentProgress = progress;
                intProgress = String.valueOf(currentProgress);

                currentHeight.setText(intProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }

        });

        // increment  and decrement age
        incrementAge.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ageText = ageText + 1;
                ageText2 = String.valueOf(ageText);
                currentAge.setText(ageText2);
            }
        });

        decrementAge.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ageText = ageText - 1;
                ageText2 = String.valueOf(ageText);
                currentAge.setText(ageText2);
            }
        });


        // increment and decrement weight here
        incrementWeight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                weightText = weightText + 1;
                weightText2 = String.valueOf(weightText);
                currentWeight.setText(weightText2);
            }
        });

        decrementWeight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                weightText = weightText - 1;
                weightText2 = String.valueOf(weightText);
                currentWeight.setText(weightText2);
            }
        });

        // add event to the button
        calculateBMIButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(typeofUser.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "First Select Your Gender", Toast.LENGTH_SHORT).show();
                }
                else if(intProgress.equals("0"))
                {
                    Toast.makeText(getApplicationContext(), "First Select Your Height", Toast.LENGTH_SHORT).show();
                }
                else if(weightText == 0 || weightText < 0)
                {
                    Toast.makeText(getApplicationContext(), "Incorrect Weight", Toast.LENGTH_SHORT).show();
                }
                else if(ageText == 0 || ageText < 0)
                {
                    Toast.makeText(getApplicationContext(), "Incorrect Age", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, BMIActivity.class);
                    intent.putExtra("Gender", typeofUser);
                    intent.putExtra("Height", intProgress);
                    intent.putExtra("Weight", weightText2);
                    intent.putExtra("Age", ageText2);

                    startActivity(intent);
                }


            }
        });
    }

}