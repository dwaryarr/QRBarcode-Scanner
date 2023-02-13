package com.dart.qrbarcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GenerateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        findViewById(R.id.btnKembali).setOnClickListener(view -> onBackPressed());
    }
}