package com.example.laboratory_work_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<CoupleWords> archiveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle arguments = getIntent().getExtras();
        if(arguments != null){
            archiveList = arguments.getParcelableArrayList("archiveList");
        }

        Button btn_goToWorkWithDatabaseActivity = findViewById(R.id.btn_goToWorkWithDatabaseActivity);
        Button btn_goToQuizActivity = findViewById(R.id.btn_goToQuizActivity);
        Button btn_goToAchiveActivity = findViewById(R.id.btn_goToArchiveActivity);

        btn_goToWorkWithDatabaseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorkWithDatabaseActivity.class);
                startActivity(intent);
            }
        });

        btn_goToQuizActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                archiveList = new ArrayList<CoupleWords>();
                startActivity(intent);
            }
        });

        btn_goToAchiveActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (archiveList != null) {
                    Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
                    intent.putExtra("archiveList", archiveList);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "ОШИБКА ДОСТУПА", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}