package com.example.halepor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.halepor.fragment.fragmentAbsensi;
import com.example.halepor.fragment.fragmentAkun;
import com.example.halepor.fragment.fragmentHome;
import com.example.halepor.fragment.fragmentPratul;
import com.example.halepor.fragment.fragmentTul2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Menu_page extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    TextView txt_page;
    ImageView ic_info;
    CardView cv_pratul;
    CardView cv_tul2;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_Name = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        txt_page = findViewById(R.id.txt_page);
        txt_page.setText("HOME");

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new fragmentHome()).commit();
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new fragmentHome();
                        txt_page.setText("Home");
                        break;
                    case R.id.absen:
                        fragment = new fragmentAbsensi();
                        txt_page.setText("Absen");
                        break;
                    case R.id.menu_pratul:
                        fragment = new fragmentPratul();
                        txt_page.setText("Pratul");
                        break;
                    case R.id.menu_tul2:
                        fragment = new fragmentTul2();
                        txt_page.setText("Tul 2 Lembar");
                        break;
                    case R.id.menu_akun:
                        fragment = new fragmentAkun();
                        txt_page.setText("Akun");
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                return true;
            }
        });
        ic_info = findViewById(R.id.ic_info);
        ic_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog info_dialog = new BottomSheetDialog(Menu_page.this, R.style.BottomDialogTheme);

                View viewInfo = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.info_about_dialog, (LinearLayout) findViewById(R.id.about_dialog_container));

                viewInfo.findViewById(R.id.ic_down_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        info_dialog.dismiss();
                    }
                });
                info_dialog.setContentView(viewInfo);
                info_dialog.show();
            }
        });
    }

        @Override
        public void onBackPressed() {
            finishAffinity();
            finish();
    }
}