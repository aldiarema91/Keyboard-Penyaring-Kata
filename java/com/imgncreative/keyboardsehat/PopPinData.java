package com.imgncreative.keyboardsehat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.imgncreative.keyboardsehat.helper.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class PopPinData extends AppCompatActivity {
    DBHelper SQLite = new DBHelper(this);

    TextView err;
    EditText pin;
    Button submit;

    private String my_pin, izin, izin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poppin);

        ArrayList<HashMap<String, String>> row = SQLite.getMyPin();
        my_pin = row.get(0).get("mypin");

        err = findViewById(R.id.err2);
        pin = findViewById(R.id.pin2);
        submit = findViewById(R.id.submit);

        izin = getIntent().getStringExtra(Pemasangan.TAG_ID);
        izin2 = getIntent().getStringExtra(Home.TAG_ID);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pin.getText().length() < 6){
                    err.setVisibility(View.VISIBLE);
                    if (pin.getText().toString().equals("")){
                        err.setText("PIN tidak boleh kosong!");
                    }else{
                        err.setText("PIN harus 6 angka");
                    }
                }else if (pin.getText().length() > 5){
                    err.setVisibility(View.VISIBLE);
                    if (pin.getText().toString().equals(my_pin)){
                        err.setVisibility(View.GONE);
                        Intent moveIntent1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(moveIntent1);
                        finish();
                    }else{
                        err.setText("PIN salah");
                    }
                }
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.3));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);



    }
}

