package com.example.laboratory_work_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WorkWithDatabaseActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button addWordButton;
    EditText wordEditText, translateEditText;
    ArrayList<CoupleWords> wordList = new ArrayList<CoupleWords>();
    TextView idTextView, wordTextView, translateTextView, isPassedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_with_database);
        db = new DatabaseHelper(this);
        addWordButton = (Button)findViewById(R.id.addWordButton);
        wordEditText = (EditText)findViewById(R.id.wordEditText);
        translateEditText = (EditText)findViewById(R.id.translateEditText);
        wordList = db.getAllWords();
        idTextView = (TextView)findViewById(R.id.idTextView);
        wordTextView = (TextView)findViewById(R.id.wordTextView);
        translateTextView = (TextView)findViewById(R.id.translateTextView);
        isPassedTextView = (TextView)findViewById(R.id.isPassedTextView);

        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = wordEditText.getText().toString();
                String translate = translateEditText.getText().toString();
                long result;
                result = db.addWord(word, translate);
                if (result > 0) {
                    Toast.makeText(WorkWithDatabaseActivity.this, "Слово добавлено", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WorkWithDatabaseActivity.this, "Ошибка добавления", Toast.LENGTH_SHORT).show();
                }
                wordEditText.setText("");
                translateEditText.setText("");
            }
        });

        showDatabaseInList();
    }


    private void showDatabaseInList() {
        for (int i = 0; i < wordList.size(); i++) {
            idTextView.append(wordList.get(i).getWordID() + "\n");
            wordTextView.append(wordList.get(i).getWord() + "\n");
            translateTextView.append(wordList.get(i).getTranslation() + "\n");
        }
    }

}