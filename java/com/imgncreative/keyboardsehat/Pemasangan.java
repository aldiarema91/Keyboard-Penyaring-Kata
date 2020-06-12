package com.imgncreative.keyboardsehat;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Pemasangan extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pemasangan);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*InputMethodManager im = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        String list = im.getEnabledInputMethodList().toString();
        String id = Settings.Secure.getString(
                getContentResolver(),
                Settings.Secure.DEFAULT_INPUT_METHOD);
        Log.d(TAG, "onCreate: "+id);
        if(list.contains("com.imgncreative.keyboardsehat")){
            btn.setBackgroundResource(R.drawable.mybutton_enabled);
            btn.setEnabled(false);
        }*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                Intent goSettingKeyboard = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                startActivity(goSettingKeyboard);
                break;
            case R.id.btn2:
                InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                imeManager.showInputMethodPicker();
                break;
            case R.id.btn3:
                Intent moveIntent = new Intent(this, AddEdit.class);
                startActivity(moveIntent);
                break;
        }
    }
}
