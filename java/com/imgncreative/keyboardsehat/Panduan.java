package com.imgncreative.keyboardsehat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Panduan extends AppCompatActivity implements View.OnClickListener {
    private ViewFlipper viewFlipper;
    private Button btnPrev, btnNext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panduan);

        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        viewFlipper = findViewById(R.id.view_flipper);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        /*viewFlipper.setFlipInterval(4000);
        viewFlipper.startFlipping();*/
        btnPrev.setVisibility(View.GONE);
    }

    public void previousView(View v) {
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        if (viewFlipper.getDisplayedChild() == 1){
            btnPrev.setVisibility(View.GONE);
            viewFlipper.showPrevious();
        }else{
            btnNext.setText("Lanjut");
            btnPrev.setVisibility(View.VISIBLE);
            viewFlipper.showPrevious();
        }
    }
    public void nextView(View v) {
        viewFlipper.setInAnimation(this, R.anim.slide_in_right);
        viewFlipper.setOutAnimation(this, R.anim.slide_out_left);

        if (viewFlipper.getDisplayedChild() == 2){
            btnNext.setText("Mulai");
            viewFlipper.showNext();
        }else if(viewFlipper.getDisplayedChild() == 3){
            Intent moveIntent = new Intent(this, Pemasangan.class);
            startActivity(moveIntent);
            finish();
        }else{
            btnNext.setText("Lanjut");
            btnPrev.setVisibility(View.VISIBLE);
            viewFlipper.showNext();
        }
    }

    public void installKeyboard(View v){
        Intent moveIntent = new Intent(this, Pemasangan.class);
        startActivity(moveIntent);
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
