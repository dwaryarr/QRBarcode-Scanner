package com.dart.qrbarcodescanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

public class MainActivity extends AppCompatActivity {
//    private Button btnScan;
private LinearLayout btnScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScan = findViewById(R.id.btnScan);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Initialize intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        //Check condition
        String hasil = intentResult.getContents();
//        ambil_data(hasil);

        if (hasil!= null){
            Toast.makeText(this, hasil, Toast.LENGTH_SHORT).show();
        }else {
            // when result content is null
            //display toast
            Toast.makeText(this,"OOPS, You did not scan anything",Toast.LENGTH_SHORT).show();
        }
    }
}