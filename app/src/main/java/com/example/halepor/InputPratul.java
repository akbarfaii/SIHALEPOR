package com.example.halepor;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.halepor.adapter.PratulAdapter;

import java.util.Calendar;

public class InputPratul extends AppCompatActivity {
    EditText idpel_pratul,namapel_pratul,norbm_pratul,alamatpel_pratul,trfdaya_pratul,rptag_pratul,nohp_pratul;
    TextView tgl_pratul;
    Spinner status_pratul;
    CardView cv_pratul;
    ImageView poto_pratul;
    PratulAdapter pratulAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pratul);

        int hari,bulan,tahun;
        Calendar calendar = Calendar.getInstance();
        hari = calendar.get(calendar.DAY_OF_MONTH);
        bulan = calendar.get(calendar.MONTH);
        tahun = calendar.get(calendar.YEAR);
        String finalTanggal = hari+"-"+bulan+"-"+tahun;

        recyclerView = findViewById(R.id.rv_pratul);
        idpel_pratul = findViewById(R.id.idpel_pratul);
        namapel_pratul = findViewById(R.id.namapel_pratul);
        norbm_pratul = findViewById(R.id.norbm_pratul);
        alamatpel_pratul = findViewById(R.id.alamatpel_pratul);
        trfdaya_pratul = findViewById(R.id.trfdaya_pratul);
        rptag_pratul = findViewById(R.id.rptag_pratul);
        status_pratul = findViewById(R.id.status_pratul);
        tgl_pratul = findViewById(R.id.tgl_pratul);
        poto_pratul = findViewById(R.id.poto_pratul);
        nohp_pratul = findViewById(R.id.nohp_pratul);

        idpel_pratul.setText(getIntent().getStringExtra("Idpel_pratul"));
        namapel_pratul.setText(getIntent().getStringExtra("Namapel_pratul"));
        norbm_pratul.setText(getIntent().getStringExtra("Norbm_pratul"));
        alamatpel_pratul.setText(getIntent().getStringExtra("Alamatpel_pratul"));
        tgl_pratul.setText(finalTanggal);

    }
}