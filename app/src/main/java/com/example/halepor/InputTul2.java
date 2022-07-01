package com.example.halepor;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class InputTul2 extends AppCompatActivity {
    EditText idpel_tul2,namapel_tul2,norbm_tul2,alamatpel_tul2,trfdaya_tul2,rptag_tul2,nohp_tul2;
    TextView tgl_tul2;
    Spinner status_tul2;
    CardView cv_tul2;
    ImageView poto_tul2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_tul2);

        idpel_tul2 = findViewById(R.id.idpel_tul2);
        namapel_tul2 = findViewById(R.id.namapel_tul2);
        norbm_tul2 = findViewById(R.id.norbm_tul2);
        alamatpel_tul2 = findViewById(R.id.alamatpel_tul2);
        trfdaya_tul2 = findViewById(R.id.trfdaya_tul2);
        rptag_tul2 = findViewById(R.id.rptag_tul2);
        status_tul2 = findViewById(R.id.status_tul2);
        tgl_tul2 = findViewById(R.id.tgl_tul2);
        poto_tul2 = findViewById(R.id.poto_tul2);
        nohp_tul2 = findViewById(R.id.nohp_tul2);
    }
}