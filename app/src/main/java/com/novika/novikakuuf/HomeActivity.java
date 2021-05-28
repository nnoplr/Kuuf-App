package com.novika.novikakuuf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomeActivity extends AppCompatActivity {

    private boolean tes;
    private final String KEY = "KEY";
    ArrayList<Products> productlist = new ArrayList<>();
    ArrayList<Transaction> translist = new ArrayList<>();
    ArrayList<DataUser> userList = new ArrayList<>();

    RecyclerView rvTrans;
    TextView greetings, walletNominal, noTrans;
    private final String KEYPROD = "KEYPROD";
    private final String KEYPRODTRANS = "KEYPRODTRANS";


    //MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.Home:
                intent = new Intent(this,HomeActivity.class);
                finish();
                break;
            case R.id.Store:
                intent = new Intent(this,StoreActivity.class);
//                finish();
                SharedPreferences test = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
                tes = test.getBoolean("TES", true);

                if (tes==false){
                    finish();
                }
                break;
            case R.id.Profile:
                intent = new Intent(this, ProfileActivity.class);
                finish();
                break;
            case R.id.Logout:
                intent = new Intent(this,MainActivity.class);
                finish();
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.i("lifecycle", "onCreate:");

        tes = true;
        SharedPreferences.Editor test = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this).edit();
        test.putBoolean("TES", tes);
        test.apply();

        //Simpan data Product ke database aplikasi
        int id = productlist.size() + 1;
        Products p = new Products(id, "Exploding Kitten", 2, 5, 250000, 106.265139, -6.912035);
        productlist.add(p);
        id = productlist.size() + 1;
        p = new Products(id,"Card Against Humanity", 2, 4, 182500, 108.126810, -7.586037);
        productlist.add(p);

        //Ambil data user yang login
        SharedPreferences idSelUser = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        int idd = idSelUser.getInt("SELECTEDID", 0);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        int size = sp.getInt(KEY + "size", 0);
        for (int i = 0; i < size; i++) {
            SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
            String usernamee = sp1.getString(KEY + "USERNAME" + i, "");
            String passwordd = sp1.getString(KEY + "PASSWORD" + i, "");
            String phonee = sp1.getString(KEY + "PHONE" + i, "");
            String dobb = sp1.getString(KEY + "DOB" + i, "");
            String genderr = sp1.getString(KEY + "GENDER" + i, "");
            int IDD = sp1.getInt(KEY + "ID" + i, 0);
            int wallett = sp1.getInt(KEY + "WALLET" + i, 0);

            DataUser data = new DataUser(usernamee, passwordd, phonee, dobb, genderr, IDD, wallett);
            userList.add(data);

        }

        //ID user disimpan agar bisa digunakan di activity lain
        SharedPreferences.Editor sendUserID = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this).edit();
        sendUserID.putInt("SELECTEDID", idd);
        sendUserID.apply();


        //Ambil Data Transaction
        SharedPreferences spTrans = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        int sizeTrans = spTrans.getInt(KEYPRODTRANS + idd + "size", 0);
        for (int i = 0; i < sizeTrans; i++) {
            SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
            int iddTrans = sp1.getInt(KEYPRODTRANS + idd +"ID" + i, 0);
            String namee = sp1.getString(KEYPRODTRANS + idd +"NAME" + i, "");
            String datee = sp1.getString(KEYPRODTRANS + idd +"DATE" + i, "");
            int prodPricee = sp1.getInt(KEYPRODTRANS + idd +"PRICE" + i, 0);

            Transaction data = new Transaction(iddTrans, namee, datee, prodPricee);
            translist.add(data);
        }

        //Simpan Arraylist Transaction
        SharedPreferences.Editor spp1 = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this).edit();
        spp1.putInt(KEYPRODTRANS + idd + "size", translist.size());
        spp1.apply();
        for (int i = 0; i < translist.size(); i++) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this).edit();
            edit.putInt(KEYPRODTRANS + idd + "ID" + i, translist.get(i).getId());
            edit.putString(KEYPRODTRANS + idd + "NAME" + i, translist.get(i).getName());
            edit.putString(KEYPRODTRANS + idd + "DATE" + i, translist.get(i).getDate());
            edit.putInt(KEYPRODTRANS + idd + "PRICE" + i, translist.get(i).getPrice());
            edit.apply();
        }

        //Simpan Arraylist Product
        SharedPreferences.Editor spp = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this).edit();
        spp.putInt(KEYPROD + "size", productlist.size());
        spp.apply();
        for (int i = 0; i < productlist.size(); i++) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this).edit();
            edit.putInt(KEYPROD + "ID" + i, productlist.get(i).getIDprod());
            edit.putString(KEYPROD + "NAME" + i, productlist.get(i).getName());
            edit.putInt(KEYPROD + "MINPLAYER" + i, productlist.get(i).getMinPlayer());
            edit.putInt(KEYPROD + "MAXPLAYER" + i, productlist.get(i).getMaxPlayer());
            edit.putInt(KEYPROD + "PRICE" + i, productlist.get(i).getPrice());
            edit.putFloat(KEYPROD + "LONGITUDE" + i, (float)productlist.get(i).getLongitude());
            edit.putFloat(KEYPROD + "LATITUDE" + i, (float)productlist.get(i).getLatitude());
            edit.apply();
        }

        greetings = findViewById(R.id.greetings);
        walletNominal = findViewById(R.id.walletNominal);
        noTrans = findViewById(R.id.noTrans);
        greetings.setText(String.valueOf(userList.get(idd - 1).getUsername()));
        walletNominal.setText(String.valueOf(userList.get(idd - 1).getWn()));


        rvTrans = findViewById(R.id.rvTrans);

        // Adapter History Transaction
        TransAdapter transAdapter = new TransAdapter();
        transAdapter.setArrayListData(translist);

        if (translist.size()==0){
            noTrans.setText("All History Transactions\nThere is no transaction");
        }

        rvTrans.setAdapter(transAdapter);
        rvTrans.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("lifecycle", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecycle", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();



        Log.i("lifecycle", "onRestart");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifecycle", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecycle", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle", "onDestroy");
    }
}
