package com.imgncreative.keyboardsehat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Bantuan extends AppCompatActivity implements View.OnClickListener {
    private ViewFlipper viewFlipper;
    private Button btnPrev, btnNext;

    private ExpandAdapter ExpAdapter;
    private ArrayList<ExpandGroup> ExpListItems;
    private ExpandableListView ExpandList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bantuan);

        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ExpandList = (ExpandableListView) findViewById(R.id.expList);
        ExpListItems = SetStandardGroups();
        ExpAdapter = new ExpandAdapter(this, ExpListItems);
        ExpandList.setAdapter(ExpAdapter);

    }

    public ArrayList<ExpandGroup> SetStandardGroups() {
        ArrayList<ExpandGroup> list = new ArrayList<ExpandGroup>();
        ArrayList<ExpandChild> list2 = new ArrayList<ExpandChild>();
        ExpandGroup gru1 = new ExpandGroup();
        gru1.setName("(?) Pemasangan Keyboard");
        ExpandChild ch1_1 = new ExpandChild();
        ch1_1.setName("Pemasangan keyboard terdapat pada menu 'Pemasangan' > 'Ijinkan' > 'Aktifkan'");
        ch1_1.setTag(null);
        list2.add(ch1_1);
        ExpandChild ch1_2 = new ExpandChild();
		/*ch1_2.setName("Open");
		ch1_2.setTag(null);
		list2.add(ch1_2);
		ExpandChild ch1_3 = new ExpandChild();
		ch1_3.setName("Close");
		ch1_3.setTag(null);
		list2.add(ch1_3);*/
        gru1.setItems(list2);

        list2 = new ArrayList<ExpandChild>();
        ExpandGroup gru2 = new ExpandGroup();
        gru2.setName("(?) Data kata dicekal");
        ExpandChild ch2_1 = new ExpandChild();
        ch2_1.setName("Penambahan data dapat dilakukan pada menu 'DATA KATA' > klik tombol '+' pada pojok kanan bawah");
        ch2_1.setTag(null);
        list2.add(ch2_1);
        ExpandChild ch2_2 = new ExpandChild();
        ch2_2.setName("Untuk 'Mengubah' atau 'Menghapus' kata dapat dilakukan pada tampilan 'Data Kata' > Tekan dan tahan pada kata > pilih menu");
        ch2_2.setTag(null);
        list2.add(ch2_2);
        gru2.setItems(list2);

        list2 = new ArrayList<ExpandChild>();
        ExpandGroup gru3 = new ExpandGroup();
        gru3.setName("(?) Tentang Akurasi Penyaringan Kata");
        ExpandChild ch3_1 = new ExpandChild();
        ch3_1.setName("Jika akurasi kata yang di cekal 0% maka, penghapusan kata ketika mengetik hanya akan dihapus jika kata yang di inputkan 'sama' dengan kata yang dimaksud.");
        ch3_1.setTag(null);
        list2.add(ch3_1);
        ExpandChild ch3_2 = new ExpandChild();
        ch3_2.setName("Jika akurasi kata yang dicekal lebih dari 0% atau semakin besar, maka kemungkinan kata akan dihapus akan semakin besar");
        ch3_2.setTag(null);
        list2.add(ch3_2);
        gru3.setItems(list2);
        list.add(gru1);
        list.add(gru2);
        list.add(gru3);
        return list;
    }
    @Override
    public void onClick(View v) {

    }
}
