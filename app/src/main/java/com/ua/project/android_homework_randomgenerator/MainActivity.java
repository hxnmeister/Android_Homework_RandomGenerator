package com.ua.project.android_homework_randomgenerator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button randomNumberGeneratorBtn = findViewById(R.id.btnGenerateRnd);

        randomNumberGeneratorBtn.setOnClickListener(view -> {
            TextInputEditText etMinRndNumber = findViewById(R.id.etMinRndNumber);
            TextInputEditText etMaxRndNumber = findViewById(R.id.etMaxRndNumber);
            TextView tvRndNumberDisplay = findViewById(R.id.tvRndNumberDisplay);
            String unparsedMin = etMinRndNumber.getText().toString();
            String unparsedMax = etMaxRndNumber.getText().toString();


            try {
                int min = parseIntegerOrDefault(unparsedMin, 0);
                int max = parseIntegerOrDefault(unparsedMax, Integer.MAX_VALUE);

                if(min > max || min == max) {
                    throw new RuntimeException("Invalid numbers for random!");
                }

//                if(unparsedMin.isEmpty()) {
//                    etMinRndNumber.setText(String.valueOf(min));
//                }
//                else if(unparsedMax.isEmpty()) {
//                    etMaxRndNumber.setText(String.valueOf(max));
//                }

                tvRndNumberDisplay.setText(String.valueOf(ThreadLocalRandom.current().nextInt(min, max)));
            } catch (Exception ex) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Warning!")
                        .setMessage("An exception occurred during execution: \n" + ex.getMessage())
                        .setPositiveButton("OK", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
        });
    }

    private static int parseIntegerOrDefault(String unparsedNumber, int defaultValue){
        return unparsedNumber.isEmpty() ? defaultValue : Integer.parseInt(unparsedNumber);
    }
}