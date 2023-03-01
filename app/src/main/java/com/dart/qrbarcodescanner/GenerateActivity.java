package com.dart.qrbarcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class GenerateActivity extends AppCompatActivity {

    private ImageView ivQR;
    private Spinner spType;
    private String[] type = {"QRCODE", "BARCODE"};
    private EditText etContent;
    private String content;
    private Integer width,height;
    private BarcodeFormat encodeType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        ivQR = findViewById(R.id.ivQR);
        etContent = findViewById(R.id.etContent);
        spType = findViewById(R.id.spType);

        findViewById(R.id.btnKembali).setOnClickListener(view -> onBackPressed());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,type);
        spType.setAdapter(adapter);

        //
        etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null) {
                    if(spType.getSelectedItem().toString().equals("BARCODE")){
                        encodeType = BarcodeFormat.CODE_128;
                        width = 350;
                        height = 150;
                    } else {
                        encodeType = BarcodeFormat.QR_CODE;
                        width = 400;
                        height = 400;
                    }
                    try {
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.encodeBitmap(String.valueOf(editable), encodeType, width, height);
                        ImageView imageViewQrCode = ivQR;
                        imageViewQrCode.setImageBitmap(bitmap);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //

//        findViewById(R.id.btnGenerate).setOnClickListener(view -> {
//            content = etContent.getText().toString();
//            if(spType.getSelectedItem().toString().equals("BARCODE")){
//               encodeType = BarcodeFormat.CODE_128;
//               width = 350;
//               height = 150;
//            } else {
//               encodeType = BarcodeFormat.QR_CODE;
//               width = 400;
//               height = 400;
//            }
//            try {
//                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//                Bitmap bitmap = barcodeEncoder.encodeBitmap(content, encodeType, width, height);
//                ImageView imageViewQrCode = ivQR;
//                imageViewQrCode.setImageBitmap(bitmap);
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        });
    }
}