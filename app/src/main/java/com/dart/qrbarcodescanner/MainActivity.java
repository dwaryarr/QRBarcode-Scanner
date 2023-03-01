package com.dart.qrbarcodescanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class MainActivity extends AppCompatActivity {
private CardView btnHistory,btnGenerate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHistory = findViewById(R.id.btnHistory);
        btnGenerate = findViewById(R.id.btnGenerate);

        findViewById(R.id.btnScan).setOnClickListener(view -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
            //Sett Prompt Text
            intentIntegrator.setPrompt("For flash use volume up key");
            //Set Beep
            intentIntegrator.setBeepEnabled(true);
            //Locked orientation
            intentIntegrator.setOrientationLocked(false);
            //Set Capture Activity
            intentIntegrator.setCaptureActivity(CaptureActivity.class);
            //Initiate Scan
            intentIntegrator.initiateScan();
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ScanHistoryActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GenerateActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        String hasil = intentResult.getContents();
        if (hasil!= null){
            Toast.makeText(this, hasil, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ResultScanActivity.class).putExtra("hasil",hasil));
        }else {
            Toast.makeText(this,"OOPS, You did not scan anything",Toast.LENGTH_SHORT).show();
        }
    }
}