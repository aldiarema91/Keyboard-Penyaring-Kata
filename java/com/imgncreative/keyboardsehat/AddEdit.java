package com.imgncreative.keyboardsehat;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.imgncreative.keyboardsehat.helper.DBHelper;

import org.w3c.dom.Text;

public class AddEdit extends AppCompatActivity {

    EditText txt_id, txt_text;
    TextView actTitle, txt_accuracy;
    Button btn_submit, btn_cancel;
    ImageView imgBack;
    Switch sw_status;
    SeekBar bar_accuracy;
    DBHelper SQLite = new DBHelper(this);
    String id, text, status, akurasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        imgBack = (ImageView) findViewById(R.id.imgBack);
        actTitle = (TextView) findViewById(R.id.actTitle);
        txt_accuracy = (TextView) findViewById(R.id.txt_accuracy);
        bar_accuracy = (SeekBar) findViewById(R.id.bar_accuracy);
        sw_status = (Switch) findViewById(R.id.sw_status);
        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_text = (EditText) findViewById(R.id.txt_text);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        text = getIntent().getStringExtra(MainActivity.TAG_TEXT);
        status = getIntent().getStringExtra(MainActivity.TAG_STATUS);
        akurasi = getIntent().getStringExtra(MainActivity.TAG_AKURASI);

        if (id == null || id == "") {
//            setTitle("Add Data");
            actTitle.setText("Tambah Kata");
            sw_status.setChecked(true);
        } else {
//            setTitle("Edit Data");
            actTitle.setText("Ubah Kata");
            txt_id.setText(id);
            txt_text.setText(text);
            txt_accuracy.setText("Akurasi : "+(akurasi.equals("0") ? "0" : akurasi+"0")+"%");
            bar_accuracy.setProgress(Integer.parseInt(akurasi));
            if (status.equals("Aktif")){
                sw_status.setChecked(true);
            }else{
                sw_status.setChecked(false);
            }
        }

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sw_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sw_status.setChecked(true);
                    Toast.makeText(getApplicationContext(),
                            "Aktif", Toast.LENGTH_SHORT).show();
                    status = "Aktif";
                }else{
                    sw_status.setChecked(false);
                    Toast.makeText(getApplicationContext(),
                            "Tidak aktif", Toast.LENGTH_SHORT).show();
                    status = "Tidak digunakan";
                }
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });

        bar_accuracy.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_accuracy.setText("Akurasi : "+(bar_accuracy.getProgress() != 0 ? bar_accuracy.getProgress()+"0" : "0")+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txt_accuracy.setText("Akurasi : "+(bar_accuracy.getProgress() != 0 ? bar_accuracy.getProgress()+"0" : "0")+"%");
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Make blank all Edit Text
    private void blank() {
        txt_text.requestFocus();
        txt_id.setText(null);
        txt_text.setText(null);
        txt_accuracy.setText("Akurasi : 0%");
        bar_accuracy.setProgress(0);
    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(txt_text.getText()).equals(null) || String.valueOf(txt_text.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Masukkan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        } else {
            if (sw_status.isChecked()){
                status = "Aktif";
            }else{
                status = "Tidak aktif";
            }
            SQLite.insert(txt_text.getText().toString().trim(), status, Integer.toString(bar_accuracy.getProgress()));
            blank();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(txt_text.getText()).equals(null) || String.valueOf(txt_text.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Masukkan tidak boleh kosong!", Toast.LENGTH_SHORT).show();
        } else {
            if (sw_status.isChecked()){
                status = "Aktif";
            }else{
                status = "Tidak aktif";
            }

            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_text.getText().toString().trim(),
                    status, bar_accuracy.getProgress());
            blank();
            finish();
        }
    }

}
