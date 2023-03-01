package com.dart.qrbarcodescanner;

import static android.webkit.URLUtil.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultScanActivity extends AppCompatActivity {
    private String hasilscan;
    private CardView btnWebSearch,btnCopy,btnShare;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private TextView etHasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_scan);
        etHasil = findViewById(R.id.etHasil);
        btnWebSearch = findViewById(R.id.btnWebSearch);
        btnCopy = findViewById(R.id.btnCopy);
        btnShare = findViewById(R.id.btnShare);
        hasilscan = getIntent().getStringExtra("hasil");
        etHasil.setText(hasilscan);

        findViewById(R.id.btnKembali).setOnClickListener(view -> onBackPressed());

        btnWebSearch.setOnClickListener(view -> {
            if (isValidUrl(hasilscan)){
                Uri uri = Uri.parse(hasilscan);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
            else {
                Uri uri = Uri.parse("https://www.google.com/search?q="+ hasilscan);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        btnCopy.setOnClickListener(view -> {
            myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            myClip = ClipData.newPlainText("Scan Result",hasilscan);
            myClipboard.setPrimaryClip(myClip);
            Toast.makeText(getApplicationContext(), "Copied",Toast.LENGTH_SHORT).show();
        });

        btnShare.setOnClickListener(view -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TITLE, hasilscan);
            sendIntent.putExtra(Intent.EXTRA_TEXT, hasilscan);
            sendIntent.setType("*/*");
            startActivity(sendIntent);
        });
    }
}