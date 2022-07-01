package com.example.halepor.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.halepor.R;
import com.example.halepor.login;

public class fragmentAkun extends Fragment {

    TextView txt_UserAkun;
    Button btn_keluar;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_Name = "name";

    public fragmentAkun() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        txt_UserAkun = view.findViewById(R.id.txt_user_akun);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String username = sharedPreferences.getString(KEY_Name,null);

        if (username != null) {
            txt_UserAkun.setText(username);

        }

        //click logout(keluar) akun
        btn_keluar = view.findViewById(R.id.btn_keluar);
        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                startActivity(new Intent(getActivity(), login.class));
                getActivity().finish();
                Toast.makeText(getActivity(), "Logout Succes", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
}