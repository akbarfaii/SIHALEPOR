package com.example.halepor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {

    TextView in_user, in_pass, register;
    Button btn_login;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_Name = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        in_user = findViewById(R.id.etxt_user_akun);
        in_pass = findViewById(R.id.etxt_password);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        //when activity first check shared preferences data available or not

        String name = sharedPreferences.getString(KEY_Name, null);

        if (name != null) {
            Intent intent = new Intent(login.this, Menu_page.class);
            startActivity(intent);
        }

        //click button login
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
            }
        });

        register = (TextView) findViewById(R.id.txt_signin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, registrasi.class));
            }
        });
    }

    public void login() {
        progressDialog = new ProgressDialog(login.this);
        progressDialog.setCancelable(false);
        progressDialog.show();


        AndroidNetworking.post("https://akbarfaii.000webhostapp.com/login_user.php")
                .addBodyParameter("petugas", in_user.getText().toString())
                .addBodyParameter("password", in_pass.getText().toString())
                .setTag(this).setPriority(Priority.MEDIUM).build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //mengambil pesan dari json
                            String pesan = response.getString("pesan");

                            if (pesan.equals("Data tersedia : Login")) {
                                JSONArray ja = response.getJSONArray("data"); // mengambil [data] dari json
                                Log.d("petugas", ja.getJSONObject(0).getString("petugas")); //mengambil data username dari json yg sudah diinput

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(KEY_Name, in_user.getText().toString());
                                editor.apply();

                                progressDialog.dismiss();
                                Intent intent = new Intent(login.this, Menu_page.class);
                                startActivity(intent);
                                finish();

                                Toast.makeText(login.this, "Login Succes", Toast.LENGTH_SHORT).show();
                            } else if (pesan.equals("password salah")) {
                                progressDialog.dismiss();
                                Toast.makeText(login.this, "Password Salah", Toast.LENGTH_SHORT).show();
                            } else if (pesan.equals("Data tidak tersedia : lakukan register")) {
                                progressDialog.dismiss();
                                Toast.makeText(login.this, "Data Tidak Tersedia,Lakukan Registrasi terlebih dahulu", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(login.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        //handler error
                        progressDialog.dismiss();
                        Toast.makeText(login.this, "Tidak ada Koneksi", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}