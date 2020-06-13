package com.imgncreative.keyboardsehat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.imgncreative.keyboardsehat.helper.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class AturPin extends AppCompatActivity {
    private static final String TAG = "";
    DBHelper SQLite = new DBHelper(this);

    private int my_pin;
    private EditText pin1, pin2;
    private TextView err1, err2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atur_pin);

        pin1 = findViewById(R.id.pin1);
        pin2 = findViewById(R.id.pin2);
        err1 = findViewById(R.id.err1);
        err2 = findViewById(R.id.err2);

        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pin1.getText().length() < 6){
                    err1.setVisibility(View.VISIBLE);
                    if (pin1.getText().toString().equals("")){
                        err1.setText("PIN tidak boleh kosong!");
                    }else{
                        err1.setText("PIN harus 6 angka");
                    }

                }else if (pin2.getText().toString().equals("") || !pin2.getText().toString().equals(pin1.getText().toString())){
                    err1.setVisibility(View.GONE);
                    err2.setVisibility(View.VISIBLE);
                    if (pin2.getText().toString().equals("")){
                        err2.setText("Tulis ulang PIN!");
                    }else if (pin2.getText().length() < 6){
                        err2.setText("PIN harus 6 angka");
                    }else{
                        err2.setText("PIN Tidak sama!");
                    }
                }else if(pin1.getText() != null && pin2.getText() != null){
                    save(pin2.getText().toString().trim());
                }
            }
        });

    }

    private void save(String pin){
        SQLite.updateMyPin(1, pin);
        Intent moveIntent = new Intent(this, Home.class);
        startActivity(moveIntent);
        finish();
    }

}

