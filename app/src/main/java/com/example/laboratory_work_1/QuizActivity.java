package com.example.laboratory_work_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    Button firstButton, secondButton, thirthButton, endQuizButton;
    TextView currentQuizWord;
    ArrayList<CoupleWords> wordList = new ArrayList<CoupleWords>();
    ArrayList<CoupleWords> archiveList = new ArrayList<CoupleWords>();
    List<CoupleWords> quizList;
    int buttonWin = 0;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Bundle arguments = getIntent().getExtras();

        currentQuizWord = (TextView)findViewById(R.id.currentQuizWord);
        firstButton = (Button)findViewById(R.id.firstButton);
        secondButton = (Button)findViewById(R.id.secondButton);
        thirthButton = (Button)findViewById(R.id.thirthButton);
        endQuizButton = (Button)findViewById(R.id.endQuizButton);

        firstButton.setOnClickListener(buttonsListener);
        secondButton.setOnClickListener(buttonsListener);
        thirthButton.setOnClickListener(buttonsListener);

        db = new DatabaseHelper(this);
        wordList = db.getAllWords();
        startQuiz();

        endQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("archiveList", archiveList);
                startActivity(intent);
            }
        });
    }

    View.OnClickListener buttonsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast toast;
            String checkMessage = "-";
            Button b = (Button)v;
            switch (b.getId()) {
                case R.id.firstButton:
                    if (buttonWin == 1) {
                        checkMessage = "+";
                        db.updateData(Integer.toString(wordList.get(buttonWin-1).getWordID()));
                    }
                    break;
                case R.id.secondButton:
                    if (buttonWin == 2) {
                        checkMessage = "+";
                        db.updateData(Integer.toString(wordList.get(buttonWin-1).getWordID()));
                    }
                    break;
                case R.id.thirthButton:
                    if (buttonWin == 3) {
                        checkMessage = "+";
                        db.updateData(Integer.toString(wordList.get(buttonWin-1).getWordID()));
                    }
                    break;
            }
            toast = Toast.makeText(QuizActivity.this, checkMessage, Toast.LENGTH_SHORT);
            toast.show();
            if(wordList.size() > 3) {
                startQuiz();
            } else {
                toast = Toast.makeText(QuizActivity.this, "Конец", Toast.LENGTH_SHORT);
                toast.show();
                firstButton.setEnabled(false);
                secondButton.setEnabled(false);
                thirthButton.setEnabled(false);
            }
        }
    };

    public void startQuiz() {
        Collections.shuffle(wordList);
        quizList = wordList.subList(0, 3);
        int buttonRandom = (int)(Math.random() * 3);
        currentQuizWord.setText(wordList.get(buttonRandom).getWord());
        buttonWin = buttonRandom + 1;
        firstButton.setText(quizList.get(0).getTranslation());
        secondButton.setText(quizList.get(1).getTranslation());
        thirthButton.setText(quizList.get(2).getTranslation());
    }
}