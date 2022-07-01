package com.example.halepor.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.halepor.R;
import com.example.halepor.adapter.PratulAdapter;
import com.example.halepor.model.Datapratul;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class fragmentPratul extends Fragment {

    SharedPreferences sharedPreferences;
    StringBuffer sb = null;

    ProgressBar progressBar;
    ProgressDialog progressDialog;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_Name = "name";

    private final static String STATUS = "Data Pratul";

    public ArrayList<Datapratul> dataPratul;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    public RecyclerView rv_pratul;

    TextView txt_idpel_pratul,txt_namapel_pratul,txt_alamat_pratul,txt_trfdaya_pratul,txt_rptag_pratul,txt_norbm_pratul;
    TextView txt_riwayat_pratul;
    ImageView img_noData;

    FloatingActionButton floatingActionButton;
    public CardView cv_pratul;

    public fragmentPratul() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pratul, container, false);

        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang Menampilkan Data");

        rv_pratul = view.findViewById(R.id.rv_pratul);
        rv_pratul.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv_pratul.setHasFixedSize(true);
        dataPratul = new ArrayList<>();

        getDataPratul();

        layoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        rv_pratul.setLayoutManager(layoutManager);
        rv_pratul.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));

        adapter = new PratulAdapter(this.getContext(), dataPratul);
        rv_pratul.setAdapter(adapter);

//        floatingActionButton = view.findViewById(R.id.fabButtonPdf);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (dataPratul.isEmpty()){
//                    txt_riwayat_pratul.setVisibility(view.VISIBLE);
//                    txt_riwayat_pratul.setText("Belum ada Idpel");
//                    floatingActionButton.setVisibility(View.GONE);
//                    Toast.makeText(getActivity(),"Tidak Ada Idpel yang bisa di cetak", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(getActivity(),"Floating Action Button berhasil dibuat",Toast.LENGTH_SHORT).show();
//            }
//        });

       return view;
    }


    public void getDataPratul(){
        progressDialog=new ProgressDialog(this.getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();
//        progressBar.setVisibility(View.VISIBLE);
        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String petugas = sharedPreferences.getString(KEY_Name, null);

        AndroidNetworking.post("https://akbarfaii.000webhostapp.com/tampil_rv_pratul.php")
                .addBodyParameter("Petugas", petugas)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response){
                try {
                    // mengambil pesan dari json
                    JSONArray ja = response.getJSONArray("data");

                    for (int i=0; i<ja.length(); i++) {
                        JSONObject data = ja.getJSONObject(i);
                        Datapratul item = new Datapratul(
                                data.getString("Petugas"),
                                data.getString("idpel_pratul"),
                                data.getString("norbm"),
                                data.getString("namapelanggan"),
                                data.getString("alamatpelanggan"),
                                data.getString("tarifdaya"),
                                data.getString("rptagihan"));

                        dataPratul.add(item);
                        progressDialog.dismiss();
//                        floatingActionButton.setVisibility(View.VISIBLE);
//                        progressBar.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
//                    progressBar.setVisibility(View.GONE);

//                    txt_riwayat_pratul.setText("Belum Ada Riwayat");
//                    txt_riwayat_pratul.setVisibility(getView().VISIBLE);

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onError(ANError anError) {
                progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
//                txt_riwayat_pratul.setText("Koneksi Gagal");
//                txt_riwayat_pratul.setVisibility(getView().VISIBLE);
                Toast.makeText(getContext(),"Koneksi Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
