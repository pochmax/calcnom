package com.example.calcnom1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText text1, text2;
    private TextView resultat;
    private RadioGroup radioGroup;
    private RadioButton plus, moins, multiplier, diviser;
    private Button egal, raz, quitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.calcnom_paysage); // Utilise calcom_paysage.xml pour paysage
        } else {
            setContentView(R.layout.calcnom); // Utilise activity_main.xml pour portrait
        }

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        resultat = findViewById(R.id.Résultat);
        radioGroup = findViewById(R.id.radioGroup);
        plus = findViewById(R.id.Plus);
        moins = findViewById(R.id.Moins);
        multiplier = findViewById(R.id.Multiplier);
        diviser = findViewById(R.id.Diviser);
        egal = findViewById(R.id.égal);
        raz = findViewById(R.id.Raz);
        quitter = findViewById(R.id.quitter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        egal.setOnClickListener(v -> calculate());
        raz.setOnClickListener(v -> resetFields());
        quitter.setOnClickListener(v -> finish());
    }

    private void calculate() {
        String input1 = text1.getText().toString();
        String input2 = text2.getText().toString();

        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer les deux valeurs", Toast.LENGTH_SHORT).show();
            return;
        }

        double number1 = Double.parseDouble(input1);
        double number2 = Double.parseDouble(input2);
        double result = 0;

        int selectedOperation = radioGroup.getCheckedRadioButtonId();
        if (selectedOperation == R.id.Plus) {
            result = number1 + number2;
        } else if (selectedOperation == R.id.Moins) {
            result = number1 - number2;
        } else if (selectedOperation == R.id.Multiplier) {
            result = number1 * number2;
        } else if (selectedOperation == R.id.Diviser) {
            if (number2 != 0) {
                result = number1 / number2;
            } else {
                Toast.makeText(this, "Erreur - division par 0", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {
            Toast.makeText(this, "Sélectionnez un opérateur", Toast.LENGTH_SHORT).show();
            return;
        }

        resultat.setText("Résultat : " + result);
    }

    private void resetFields() {
        text1.setText("");
        text2.setText("");
        resultat.setText("Résultat");
        radioGroup.clearCheck();
    }
}