package com.imgncreative.keyboardsehat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);

        Button Absen= findViewById(R.id.btnProduk);
        Absen.setOnClickListener(this);

        Button Tentang= findViewById(R.id.btnAbout);
        Tentang.setOnClickListener(this);

        Button Data= findViewById(R.id.btnListData);
        Data.setOnClickListener(this);

        Button Help= findViewById(R.id.btnHelp);
        Help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnProduk:
                Intent moveIntent = new Intent(Home.this, Pemasangan.class);
                startActivity(moveIntent);
                break;
            case R.id.btnListData:
                Intent moveIntent1 = new Intent(Home.this, MainActivity.class);
                startActivity(moveIntent1);
                break;
        }
    }
}
