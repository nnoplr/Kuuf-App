package com.novika.novikakuuf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    TextView tvUsername, tvGender, tvDOB, tvPhone, tvNominal;
    RadioGroup rgTopUp;
    RadioButton selectedTopUp;
    EditText etPassword;
    Button btnConfirm;
    private final String KEY = "KEY";
    ArrayList<DataUser> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUsername = findViewById(R.id.textUsername);
        tvGender = findViewById(R.id.textUserGender);
        tvDOB = findViewById(R.id.textUserDOB);
        tvPhone = findViewById(R.id.textUserPhone);
        tvNominal = findViewById(R.id.textUserWallet);
        etPassword = findViewById(R.id.etPassword);
        btnConfirm = findViewById(R.id.confirmBtn);
        rgTopUp = findViewById(R.id.RadioGroupTopUp);


        //Ambil data user yang login
        SharedPreferences idSelUser = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
        int idd = idSelUser.getInt("SELECTEDID", 0);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
        int size = sp.getInt(KEY + "size", 0);
        for (int i = 0; i < size; i++) {
            SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this);
            String usernamee = sp.getString(KEY + "USERNAME" + i, "");
            String passwordd = sp.getString(KEY + "PASSWORD" + i, "");
            String phonee = sp.getString(KEY + "PHONE" + i, "");
            String dobb = sp.getString(KEY + "DOB" + i, "");
            String genderr = sp.getString(KEY + "GENDER" + i, "");
            int IDD = sp.getInt(KEY + "ID" + i, 0);
            int wallett = sp.getInt(KEY + "WALLET" + i, 0);

            DataUser data = new DataUser(usernamee, passwordd, phonee, dobb, genderr, IDD, wallett);
            userList.add(data);

        }

        tvUsername.setText(String.valueOf(userList.get(idd-1).getUsername()));
        tvGender.setText(String.valueOf(userList.get(idd-1).getGender()));
        tvDOB.setText(String.valueOf(userList.get(idd-1).getDateOfBirth()));
        tvPhone.setText(String.valueOf(userList.get(idd-1).getPhoneNumber()));
        tvNominal.setText(String.valueOf(userList.get(idd-1).getWn()));


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = etPassword.getText().toString();
                String topUp = selectedTopUp.getText().toString();
                int nominalAwal = userList.get(idd-1).getWn();
                int nominal = 0;


                if (selectedTopUp == null) {
                    Toast.makeText(getApplicationContext(), "Top up nominal must be selected", Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(String.valueOf(userList.get(idd-1).getPassword()))){
                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (topUp.equals("Rp.250.000")){
                        nominal = 250000;
                    }
                    else if (topUp.equals("Rp.500.000")){
                        nominal = 500000;
                    }
                    else if (topUp.equals("Rp.1.000.000")){
                        nominal = 1000000;
                    }

                    nominal = nominal+nominalAwal;

                    userList.get(idd-1).setWn(nominal);



                    Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);

                    SharedPreferences.Editor sp = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this).edit();
                    sp.putInt(KEY + "size", userList.size());
                    sp.apply();
                    for (int i = 0; i < userList.size(); i++) {
                        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ProfileActivity.this).edit();
                        edit.putString(KEY + "USERNAME" + i, userList.get(i).getUsername());
                        edit.putString(KEY + "PASSWORD" + i, userList.get(i).getPassword());
                        edit.putString(KEY + "PHONE" + i, userList.get(i).getPhoneNumber());
                        edit.putString(KEY + "DOB" + i, userList.get(i).getDateOfBirth());
                        edit.putString(KEY + "GENDER" + i, userList.get(i).getGender());
                        edit.putInt(KEY + "ID" + i, userList.get(i).getID());
                        edit.putInt(KEY + "WALLET" + i, userList.get(i).getWn());

                        edit.apply();
                    }

                    finish();
                    startActivity(intent);
                }
            }
        });



        rgTopUp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedTopUp = group.findViewById(checkedId);
            }
        });


    }
}