package com.example.insulin_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextHeight, editTextWeight, editTextAge;
    private int weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextAge = findViewById(R.id.editTextAge);

        Button buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateInsulinDose();
            }
        });
    }

    public void myCalendar(View v){
        Intent intent = new Intent(this, CalendarActivity.class);
        startActivity(intent);
    }

    private void calculateInsulinDose() {
        // Отримання даних користувача
        int height = Integer.parseInt(editTextHeight.getText().toString());
        weight = Integer.parseInt(editTextWeight.getText().toString());
        int age = Integer.parseInt(editTextAge.getText().toString());

        // Розрахунок добової норми інсуліну
        double insulinNorm = calculateInsulinNorm(weight);

        // Розрахунок долі інсуліну довгої та короткої дії
        double longInsulinRatio = calculateLongInsulinRatio();
        double shortInsulinRatio = 1 - longInsulinRatio;

        // Розрахунок ранкової та вечірньої дози інсуліну довгої дії
        double morningDose = calculateMorningLongDose(longInsulinRatio);
        double eveningDose = calculateEveningLongDose(longInsulinRatio);

        // Виведення результатів на екран
        String resultText = "Добова норма інсуліну: " + insulinNorm + " од.\n"
                + "Доля інсуліну довгої дії: " + longInsulinRatio + "\n"
                + "Доля інсуліну короткої дії: " + shortInsulinRatio + "\n"
                + "Ранкова доза інсуліну довгої дії: " + morningDose + " од.\n"
                + "Вечірня доза інсуліну довгої дії: " + eveningDose + " од.\n";
        Toast.makeText(this, resultText, Toast.LENGTH_LONG).show();
    }

    private double calculateMorningLongDose(double longInsulinRatio) {
        // Ранкова доза інсуліну довгої дії обчислюється як 2/3 від добової норми інсуліну довгої дії
        return calculateLongInsulinRatio() * 0.67;
    }

    private double calculateEveningLongDose(double longInsulinRatio) {
        // Вечірня доза інсуліну довгої дії обчислюється як 1/3 від добової норми інсуліну довгої дії
        return calculateLongInsulinRatio() * 0.33;
    }

    private double calculateInsulinNorm(int weight) {
        // Формула для розрахунку добової норми інсуліну
        return weight * 0.5;
    }

    private double calculateLongInsulinRatio() {
        // Зазвичай доля інсуліну довгої дії становить 40-50% від загальної добової потреби в інсуліні
        return calculateInsulinNorm(weight) * 0.45;
    }
}