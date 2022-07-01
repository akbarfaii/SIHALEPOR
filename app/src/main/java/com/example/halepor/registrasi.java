package com.example.halepor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class registrasi extends AppCompatActivity {

    EditText petugas,email,password,handphone,perusahaan,unitpel;
    Button simpan;
    ImageView icn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        petugas = findViewById(R.id.et_petugas);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_pass);
        handphone = findViewById(R.id.et_hp);
        perusahaan = findViewById(R.id.et_pt);
        unitpel = findViewById(R.id.et_unit);

        //
        simpan = findViewById(R.id.btn_simpan);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasi_data();
            }
        });
        icn_back = findViewById(R.id.icn_kembali);
        icn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
    public void validasi_data () {
        petugas = findViewById(R.id.et_petugas);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_pass);
        handphone = findViewById(R.id.et_hp);
        perusahaan = findViewById(R.id.et_pt);
        unitpel = findViewById(R.id.et_unit);

        String get_petugas = petugas.getText().toString();
        String get_email= email.getText().toString();
        String get_password= password.getText().toString();
        String get_handphone= handphone.getText().toString();
        String get_perusahaan= perusahaan.getText().toString();
        String get_unitpel= unitpel.getText().toString();


        if(TextUtils.isEmpty(get_petugas)) {
            petugas.setError("Username Tidak Boleh Kosong");
        }
        if(TextUtils.isEmpty(get_email)) {
            email.setError("Email Tidak Boleh Kosong");
        }
        if(TextUtils.isEmpty(get_password)) {
            password.setError("Password Tidak Boleh kosong");
        }
        if (TextUtils.isEmpty(get_handphone)){
            handphone.setError("Nomor Handphone Tidak Boleh Kosong");
        }
        if (TextUtils.isEmpty(get_perusahaan)){
            perusahaan.setError("Nama Perusahaan Tidak Boleh Kosong");
        }
        if (TextUtils.isEmpty(get_unitpel)){
            unitpel.setError("Unit Pelayanan Tidak Boleh Kosong");
        }

        if(TextUtils.isEmpty(get_petugas) || TextUtils.isEmpty(get_email) || TextUtils.isEmpty(get_password)
                || TextUtils.isEmpty(get_handphone)|| TextUtils.isEmpty(get_perusahaan) || TextUtils.isEmpty(get_unitpel)){
            Toast.makeText(registrasi.this, "Semua Kolom Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }
    }
}