package com.example.halepor.Detail;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.halepor.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class DetailPratul extends AppCompatActivity {
    EditText et_petugas, et_idpel_pratul, et_namapel_pratul, norbm_pratul, et_alamatpel_pratul, trfdaya_pratul,
            rptag_pratul;
    EditText txt_EditNoHpPratul;
    MaterialCardView layoutEditPotoPratul;
    TextView tgl_pratul;
    Spinner status_pratul;
    CardView cv_pratul;
    ImageView img_EditPotoPratul;
    Button btnCetakPdf, btnEdit, btnBatal, btnSimpan;
    LinearLayout groupBtnEdit, groupBtnCetak;
    TextInputLayout layoutstatus_pratul, layoutEditNoHpPratul;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_Name = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pratul);

        ImageView icn_kembali = (ImageView) findViewById(R.id.icn_kembali);
        icn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        int hari, bulan, tahun;
        Calendar calendar = Calendar.getInstance();
        hari = calendar.get(calendar.DAY_OF_MONTH);
        bulan = calendar.get(calendar.MONTH);
        tahun = calendar.get(calendar.YEAR);
        String finalTanggal = hari + "-" + bulan + "-" + tahun;

        et_petugas = findViewById(R.id.et_petugas);
        et_idpel_pratul = findViewById(R.id.et_idpel_pratul);
        et_namapel_pratul = findViewById(R.id.et_namapel_pratul);
        norbm_pratul = findViewById(R.id.et_norbm_pratul);
        et_alamatpel_pratul = findViewById(R.id.et_alamatpel_pratul);
        trfdaya_pratul = findViewById(R.id.et_trfdaya_pratul);
        rptag_pratul = findViewById(R.id.et_rptag_pratul);
        status_pratul = findViewById(R.id.status_pratul);
        tgl_pratul = findViewById(R.id.tgl_pratul);

        btnEdit = findViewById(R.id.btn_Edit);
        btnBatal = findViewById(R.id.btn_Batal);
        btnSimpan = findViewById(R.id.btn_Simpan);
        btnCetakPdf = findViewById(R.id.btn_cetakPdf);

        groupBtnEdit = findViewById(R.id.group_btnEdit);
        groupBtnCetak = findViewById(R.id.group_Btncetak);


//        Datapratul dataPratul = getIntent().getParcelableExtra(ITEM_EXTRA);
//        et_petugas.setText(getPetugas());
//        et_idpel_pratul.setText(getIntent().getStringExtra("Idpel_pratul"));
//        tgl_pratul.setText(finalTanggal);

        sharedPreferences = this.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String Petugas = sharedPreferences.getString(KEY_Name, null);

        getDetailPratul();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupBtnEdit.setVisibility(View.VISIBLE);
                groupBtnCetak.setVisibility(View.GONE);
                layoutstatus_pratul.setVisibility(View.VISIBLE);
                layoutEditPotoPratul.setVisibility(View.VISIBLE);
                layoutEditNoHpPratul.setVisibility(View.VISIBLE);
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupBtnEdit.setVisibility(View.GONE);
                groupBtnCetak.setVisibility(View.VISIBLE);
                layoutstatus_pratul.setVisibility(View.GONE);
                layoutEditPotoPratul.setVisibility(View.GONE);
                layoutEditNoHpPratul.setVisibility(View.GONE);
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_EditNoHpPratul.getText().toString().isEmpty()) {
                Toast.makeText(DetailPratul.this, "no hp tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }else {
                    showDialogEditDetailPratul();
                }
            }
        });
    }

    private void getDetailPratul() {
        String petugas = sharedPreferences.getString(KEY_Name, null);
        String idpel_pratul = getIntent().getStringExtra("idpel_pratul");

        AndroidNetworking.post("https://akbarfaii.000webhostapp.com/detail_pratul.php")
                .addBodyParameter("petugas", petugas)
                .addBodyParameter("idpel_pratul", idpel_pratul)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String pesan = response.getString("pesan");

                            if (pesan.equals("Data Tersedia")) {
                                JSONArray jsonArray = response.getJSONArray("data"); // mengambil data dari json
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject data = jsonArray.getJSONObject(0);
//                                    tgl_pratul.setText("Tanggal: " + data.getString("Tanggal Pratul"));
                                    et_petugas.setText("petugas" +data.getString("petugas"));
                                    et_idpel_pratul.setText("idpel_pratul" +data.getString("idpel_pratul"));
                                    norbm_pratul.setText("norbm" +data.getString("norbm"));
                                    et_namapel_pratul.setText("namapelanggan" +data.getString("namapelanggan"));
                                    et_alamatpel_pratul.setText("alamatpelanggan"+data.getString("alamatpelanggan"));
                                    trfdaya_pratul.setText("tarifdaya" +data.getString("tarif_daya"));
                                    rptag_pratul.setText("rptagihan" +data.getString("rptagihan"));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(DetailPratul.this, "Maaf Gagal Terhubung Kedatabase", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(DetailPratul.this, "koneksi Gagal: Gagal Menampilkan Detail", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        private void editDetailPratul(){
            String petugas = sharedPreferences.getString(KEY_Name, null);
            String Idpel = getIntent().getStringExtra("Idpel");

            AndroidNetworking.post("https://akbarfaii.000webhostapp.com/edit_pratul.php")
                    .addBodyParameter("petugas", petugas)
                    .addBodyParameter("Idpel", Idpel)
//                    .addBodyParameter("status", status_pratul.getOnItemSelectedListener().toString())
                    .addBodyParameter("NoHp", txt_EditNoHpPratul.getText().toString())
                    .setPriority(Priority.MEDIUM)
                    .setTag(this)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String pesan = response.getString("pesan");

                                if (pesan.equals("Sukses Edit Pratul")){
                                    getDetailPratul();
                                    Toast.makeText(DetailPratul.this,"Pratul Berhasil Diubah", Toast.LENGTH_SHORT).show();
                                    groupBtnEdit.setVisibility(View.GONE);
                                    groupBtnCetak.setVisibility(View.VISIBLE);
//                                    layoutstatus_pratul.setVisibility(View.GONE);
//                                    layoutEditPotoPratul.setVisibility(View.GONE);
                                    layoutEditNoHpPratul.setVisibility(View.GONE);
                                }else {
                                    Toast.makeText(DetailPratul.this,"Pratul Gagal Diubah", Toast.LENGTH_SHORT).show();
                                }
                            }catch (JSONException e){
                                e.printStackTrace();
                                //Gagal Terhubung kedatabase
                                Toast.makeText(DetailPratul.this,"Pratul Gagal konek ke database", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(DetailPratul.this, "Koneksi Gagal : Gagal Menampilkan Detail", Toast.LENGTH_SHORT).show();
                        }
                    });
        }


    private void showDialogEditDetailPratul(){
        AlertDialog alertDialog = new AlertDialog.Builder(DetailPratul.this).create();
        alertDialog.setTitle("Simpan Perubahan Data");
        alertDialog.setMessage("Message");

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SIMPAN"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editDetailPratul();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        alertDialog.show();

        Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button btnNegative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        LinearLayout.LayoutParams PositiveButton = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        PositiveButton.weight = 100;
        PositiveButton.gravity = Gravity.CENTER;
        btnPositive.setLayoutParams(PositiveButton);

        LinearLayout.LayoutParams NegativeButton = (LinearLayout.LayoutParams) btnPositive.getLayoutParams();
        NegativeButton.weight = 100;
        NegativeButton.gravity = Gravity.CENTER;
        btnNegative.setLayoutParams(NegativeButton);

    }

//    @Override
//    public boolean onSupportNavigateUp(){
//        finish();
//        return true;
//    }
    }
