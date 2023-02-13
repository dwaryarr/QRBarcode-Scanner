package com.dart.qrbarcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class HasilScanActivity extends AppCompatActivity {
    private String hasilscan;
    private Button btnKembali;
    private TextView etHasil,etHasil2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_scan);
        etHasil = findViewById(R.id.etHasil);
        etHasil2 = findViewById(R.id.etHasil2);
        hasilscan = getIntent().getStringExtra("hasil");
        etHasil.setText(hasilscan);
        etHasil2.setText(hasilscan);

        findViewById(R.id.btnKembali).setOnClickListener(view -> onBackPressed());
    }
}