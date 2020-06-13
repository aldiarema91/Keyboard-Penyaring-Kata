package com.imgncreative.keyboardsehat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.imgncreative.keyboardsehat.helper.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.imgncreative.keyboardsehat.MainActivity.TAG_ID;

public class Pemasangan extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG_ID = "";
    private static final String TAG = "";
    DBHelper SQLite = new DBHelper(this);
    private  String list;
    private String my_pin;
    private boolean keysehat = false;
    private InputMethodInfo imi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pemasangan);
        getMy_pin();

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

        InputMethodManager im = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        list = im.getEnabledInputMethodList().toString();
        if (list.contains("com.imgncreative.keyboardsehat")){
            btn.setText("Sudah di ijinkan");
            btn.setBackgroundResource(R.drawable.mybutton_enabled);
            btn.setEnabled(false);
        }

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        List<InputMethodInfo> mInputMethodProperties = imm.getEnabledInputMethodList();
        final int N = mInputMethodProperties.size();

        for (int i = 0; i < N; i++) {

            imi = mInputMethodProperties.get(i);

            if (imi.getId().equals(Settings.Secure.getString(getContentResolver(), Settings.Secure.DEFAULT_INPUT_METHOD))) {

                Log.d(TAG, "Keyboasr info: "+imi.getServiceName());
                if (imi.getServiceName().equals("com.imgncreative.keyboardsehat.SoftKeyboard")){
                    keysehat = true;
                }
                break;
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                Intent goSettingKeyboard = new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS);
                startActivity(goSettingKeyboard);
                break;
            case R.id.btn2:
                if(keysehat){
//                    Toast.makeText(getApplicationContext(),"MYPIN: "+my_pin, Toast.LENGTH_SHORT).show();
                    Intent poppin = new Intent(getApplicationContext(), PopActivty.class);
                    poppin.putExtra(TAG_ID, "ijin");
                    startActivity(poppin);
                }else{
                    InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
                    imeManager.showInputMethodPicker();
                }
                break;
            case R.id.btn3:
                Intent moveIntent = new Intent(this, AddEdit.class);
                startActivity(moveIntent);
                break;
        }
    }

    private String getMy_pin() {
        ArrayList<HashMap<String, String>> row = SQLite.getMyPin();
        my_pin = row.get(0).get("mypin");

        return my_pin;
    }
}
