package com.example.halepor.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.halepor.R;
import com.example.halepor.adapter.Tul2Adapter;
import com.example.halepor.model.Datatul2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class fragmentTul2 extends Fragment {

    public RecyclerView rvDataTul2;
    SharedPreferences sharedPreferences;

    ProgressDialog progressDialog;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_Name = "name";

    private final static String STATUS = "Data Tul2Lembar";
    public ArrayList<Datatul2> dataTul2;
    public RecyclerView.Adapter adapter;

    public RecyclerView.LayoutManager layoutManager;

    public fragmentTul2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tul2, container, false);

        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setTitle("Mohon Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Sedang Menampilkan Data");

        rvDataTul2 = view.findViewById(R.id.rv_tul2);
        rvDataTul2.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvDataTul2.setHasFixedSize(true);
        dataTul2 = new ArrayList<>();

        getDatatul2();

        layoutManager = new LinearLayoutManager(this.getContext(), RecyclerView.VERTICAL, false);
        rvDataTul2.setLayoutManager(layoutManager);
        rvDataTul2.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));

        adapter = new Tul2Adapter(this.getContext(), dataTul2);
        rvDataTul2.setAdapter(adapter);

        return view;
    }

    public void getDatatul2() {
        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Memuat Data");
        progressDialog.show();
        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String petugas = sharedPreferences.getString(KEY_Name, null);

        AndroidNetworking.post("https://akbarfaii.000webhostapp.com/rv_tul2lembar.php")
                .addBodyParameter("petugas", petugas)
                .setTag(this)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
        @Override
        public void onResponse(JSONObject response) {
                    try {
            JSONArray ja = response.getJSONArray("data");

            for (int i = 0; i < ja.length(); i++) {
            JSONObject data = ja.getJSONObject(i);
            Datatul2 item = new Datatul2
                    (data.getString("petugas"), data.getString("idpel_tul2"),
                            data.getString("namapel_tul2"), data.getString("alamat_tul2"));
                dataTul2.add(item);
            progressDialog.dismiss();
        }
            } catch (JSONException e) {
                e.printStackTrace();
                progressDialog.dismiss();
            }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(ANError anError) {
                Toast.makeText(getContext(), "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
