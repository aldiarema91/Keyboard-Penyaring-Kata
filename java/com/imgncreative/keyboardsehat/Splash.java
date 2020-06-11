package com.imgncreative.keyboardsehat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splah_screen);

        final ImageView imgSwitch = findViewById(R.id.images);


        ((ViewSwitcher) findViewById(R.id.switcher)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ViewSwitcher switcher = (ViewSwitcher) v;

                if (switcher.getDisplayedChild() == 0) {
                    switcher.showNext();
                } else {
                    switcher.showPrevious();
                }
            }
        });
        final ScaleAnimation grow = new ScaleAnimation(1, 1.2f, 1, 1.2f,
                Animation.ABSOLUTE, 0.5f,
                Animation.ABSOLUTE, 0.5f);
        final AlphaAnimation fade = new AlphaAnimation(1.0f, 0.0f);
        final Thread timer = new Thread() {
            public void run() {
                while (!isInterrupted()) {

                    try {
                        //berapalama splashscreen akan ditampilkan dalam milisecond
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                count++;
                                if (count % 2 == 0) {
                                    imgSwitch.setImageResource(R.drawable.stiki);
                                    imgSwitch.setAnimation(grow);
                                }else if(count == 3){
                                    //activity yang akan dijalankan setelah splashscreen selesai
                                    Intent intent = new Intent(Splash.this, Home.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.start();
    }
}
