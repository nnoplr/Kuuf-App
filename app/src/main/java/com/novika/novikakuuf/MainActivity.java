package com.novika.novikakuuf;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_REPLY = 1;
    final static public String DATAS_CODE = "DATAS";
    private final String KEY = "KEY";
    EditText loginUsername, loginPassword;
    Button loginBtn, regisBtn;

    ArrayList<DataUser> userList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUsername = findViewById(R.id.LoginIsiUsername);
        loginPassword = findViewById(R.id.LoginIsiPassword);
        loginBtn = findViewById(R.id.BtnLogin);
        regisBtn = findViewById(R.id.BtnRegister);

//        if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(SecondActivity.USERS_KEY1)) {
//            userList = (ArrayList<DataUser>) getIntent().getSerializableExtra(SecondActivity.USERS_KEY1);
//        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        int size = sp.getInt(KEY+"size",0);
        for (int i=0;i<size;i++){
            SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            String usernamee = sp.getString(KEY+"USERNAME"+i,"");
            String passwordd = sp.getString(KEY+"PASSWORD"+i,"");
            String phonee = sp.getString(KEY+"PHONE"+i,"");
            String dobb = sp.getString(KEY+"DOB"+i,"");
            String genderr = sp.getString(KEY+"GENDER"+i,"");
            int IDD = sp.getInt(KEY+"ID"+i,0);
            int wallett = sp.getInt(KEY+"WALLET"+i,0);

            DataUser data = new DataUser(usernamee, passwordd,phonee,dobb,genderr,IDD,wallett);
            userList.add(data);

        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();

                if (username.equals("")) {
                    Toast.makeText(getApplicationContext(), "Username field must be filled", Toast.LENGTH_SHORT).show();
                }
                else if (password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Password field must be filled", Toast.LENGTH_SHORT).show();
                }
                // VALIDASI ADA DI DATABASE ATAU TDK

                else {
                    int size = userList.size();
                    boolean cek = false;
                    int i=0;
                    for (i=0; i < size; i++) {
                        if (username.equals(userList.get(i).getUsername()) && password.equals(userList.get(i).getPassword())) {
                            cek = true;
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);

                            SharedPreferences.Editor spp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                            spp.putInt("SELECTEDID", userList.get(i).getID());

                            spp.apply();

                            SharedPreferences.Editor sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                            sp.putInt(KEY+"size", userList.size());
                            sp.apply();
                            for (int j=0;j<userList.size();j++){
                                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                                edit.putString(KEY+"USERNAME"+j, userList.get(j).getUsername());
                                edit.putString(KEY+"PASSWORD"+j, userList.get(j).getPassword());
                                edit.putString(KEY+"PHONE"+j, userList.get(j).getPhoneNumber());
                                edit.putString(KEY+"DOB"+j, userList.get(j).getDateOfBirth());
                                edit.putString(KEY+"GENDER"+j, userList.get(j).getGender());
                                edit.putInt(KEY+"ID"+j, userList.get(j).getID());
                                edit.putInt(KEY+"WALLET"+j, userList.get(j).getWn());
                                edit.apply();

                            }

                            finish();
                            startActivity(intent);

                        }
                    }

                    if(cek==false){
                        Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

//                intent.putExtra(DATAS_CODE, userList);
                SharedPreferences.Editor sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                sp.putInt(KEY+"size", userList.size());
                sp.apply();
                for (int i=0;i<userList.size();i++){
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                    edit.putString(KEY+"USERNAME"+i, userList.get(i).getUsername());
                    edit.putString(KEY+"PASSWORD"+i, userList.get(i).getPassword());
                    edit.putString(KEY+"PHONE"+i, userList.get(i).getPhoneNumber());
                    edit.putString(KEY+"DOB"+i, userList.get(i).getDateOfBirth());
                    edit.putString(KEY+"GENDER"+i, userList.get(i).getGender());
                    edit.putInt(KEY+"ID"+i, userList.get(i).getID());
                    edit.putInt(KEY+"WALLET"+i, userList.get(i).getWn());
                    edit.apply();

                }
                finish();
                startActivity(intent);
            }
        });

    }

}
