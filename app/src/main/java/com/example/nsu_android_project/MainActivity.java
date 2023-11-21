package com.example.nsu_android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private Button go_train_page_btn;
    private Button go_mark_page_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go_train_page_btn =(Button) findViewById(R.id.go_train_page_btn);
        go_mark_page_btn =(Button) findViewById(R.id.go_mark_page_btn);

        go_train_page_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LineSearchActivity.class);
                startActivity(intent);
            }
        });

        go_mark_page_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookMarkActivity.class);
                startActivity(intent);
            }
        });
    }
}


