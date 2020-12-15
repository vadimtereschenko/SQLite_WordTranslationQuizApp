package com.example.laboratory_work_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArchiveActivity extends AppCompatActivity {
    ArrayList<CoupleWords> archiveList;
    TextView idTextView, wordTextView, translateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            archiveList = arguments.getParcelableArrayList("archiveList");
        }

        idTextView = (TextView)findViewById(R.id.idTextView);
        wordTextView = (TextView)findViewById(R.id.wordTextView);
        translateTextView = (TextView)findViewById(R.id.translateTextView);
        if(archiveList.size() > 0 ) {
            for (int i = 0; i < archiveList.size(); i++) {
                idTextView.append(archiveList.get(i).getWordID() + "\n");
                wordTextView.append(archiveList.get(i).getWord() + "\n");
                translateTextView.append(archiveList.get(i).getTranslation() + "\n");
            }
        }
        else {
            Toast.makeText(this, "ОШИБКА ДОСТУПА", Toast.LENGTH_SHORT).show();
        }
    }
}