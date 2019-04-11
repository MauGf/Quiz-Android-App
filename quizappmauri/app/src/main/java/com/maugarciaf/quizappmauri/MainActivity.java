package com.maugarciaf.quizappmauri;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;

    private TextView textViewResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResultados = findViewById(R.id.text_view_Nota);
        Button btnSalir = (Button) findViewById (R.id.button_salir) ;

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });

        btnSalir.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(MainActivity.this, QuizActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score >= 6) {
                    textViewResultados.setText("Nota obtenida: " + score +"\n\nEstado: Aprobado\n");
                    textViewResultados.setBackgroundColor(Color.parseColor("#008577")) ;
                }else{
                    textViewResultados.setText("Nota obtenida: " + score +"\n\nEstado: Reprobado\n");
                    textViewResultados.setBackgroundColor(Color.parseColor("#AA3456")) ;
                }
            }
        }
    }
}