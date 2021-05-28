package com.novika.novikakuuf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ProductDetailActivity extends AppCompatActivity {

    TextView tvName, tvMinPlay, tvMaxPlay, tvPrice;
    Button btnBuy;
    private final String KEYPROD = "KEYPROD";
    private final String KEYCHOSEN = "KEYCHOSEN";
    private final String KEYPRODTRANS = "KEYPRODTRANS";
    private final String KEY = "KEY";

    ArrayList<Products> productlist = new ArrayList<>();
    ArrayList<Transaction> translist = new ArrayList<>();
    ArrayList<DataUser> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        tvName = findViewById(R.id.textName);
        tvMinPlay = findViewById(R.id.textMinPlay);
        tvMaxPlay = findViewById(R.id.textMaxPlay);
        tvPrice = findViewById(R.id.textProdPrice);
        btnBuy = findViewById(R.id.btnBuy);

        //Dapatin ID user yang login
        SharedPreferences idSelUser = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this);
        int idd = idSelUser.getInt("SELECTEDID", 0);

        //Mendapatkan Arraylist User
        SharedPreferences spUser = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this);
        int size = spUser.getInt(KEY + "size", 0);
        for (int i = 0; i < size; i++) {
            SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this);
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

        //Dapatin data transaksi user yang login
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this);
        int sizeTrans = sp.getInt(KEYPRODTRANS + idd + "size", 0);
        for (int i = 0; i < sizeTrans; i++) {
            SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this);
            int idTrans = sp1.getInt(KEYPRODTRANS + idd + "ID" + i, 0);
            String namee = sp1.getString(KEYPRODTRANS + idd + "NAME" + i, "");
            String datee = sp1.getString(KEYPRODTRANS + idd + "DATE" + i, "");
            int prodPricee = sp1.getInt(KEYPRODTRANS + idd + "PRICE" + i, 0);

            Transaction data = new Transaction(idTrans, namee, datee, prodPricee);
            translist.add(data);
        }

        //Dapatin data Products
        SharedPreferences spp = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this);
        int sizee = spp.getInt(KEYPROD + "size", 0);
        for (int i = 0; i < sizee; i++) {
            SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this);
            int idProd = sp1.getInt(KEYPROD + "ID" + i, 0);
            String namee = sp1.getString(KEYPROD + "NAME" + i, "");
            int minPlayerr = sp1.getInt(KEYPROD + "MINPLAYER" + i, 0);
            int maxPlayerr = sp1.getInt(KEYPROD + "MAXPLAYER" + i, 0);
            int prodPricee = sp1.getInt(KEYPROD + "PRICE" + i, 0);
            float longitudee = sp1.getFloat(KEYPROD + "LONGITUDE" + i, 0);
            float latitudee = sp1.getFloat(KEYPROD + "LATITUDE" + i, 0);

            Products data = new Products(idProd, namee, minPlayerr, maxPlayerr, prodPricee, longitudee, latitudee);
            productlist.add(data);
        }

        //Dapat id produk yang ditunjukin
        SharedPreferences spp1 = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this);
        int chosenid = spp1.getInt(KEYCHOSEN + "ID", 0);

        tvName.setText(String.valueOf(productlist.get(chosenid).getName()));
        tvMinPlay.setText(String.valueOf(productlist.get(chosenid).getMinPlayer()));
        tvMaxPlay.setText(String.valueOf(productlist.get(chosenid).getMaxPlayer()));
        tvPrice.setText(String.valueOf(productlist.get(chosenid).getPrice()));

        Calendar calendar = Calendar.getInstance();
        String datee = DateFormat.getDateInstance().format(calendar.getTime());


        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (productlist.get(chosenid).getPrice()>userList.get(idd-1).getWn()){
                    Toast.makeText(getApplicationContext(),"Your wallet is less than product price", Toast.LENGTH_SHORT).show();
                }

                else {

                    Intent intent = new Intent(ProductDetailActivity.this, HomeActivity.class);
                    int idTrans = translist.size();

                    Transaction data = new Transaction(idTrans + 1, String.valueOf(productlist.get(chosenid).getName()), datee, productlist.get(chosenid).getPrice());
                    translist.add(data);

                    //Kirim Trans
                    SharedPreferences.Editor spp1 = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this).edit();
                    spp1.putInt(KEYPRODTRANS + idd + "size", translist.size());
                    spp1.apply();
                    for (int i = 0; i < translist.size(); i++) {
                        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this).edit();
                        edit.putInt(KEYPRODTRANS + idd + "ID" + i, translist.get(i).getId());
                        edit.putString(KEYPRODTRANS + idd + "NAME" + i, translist.get(i).getName());
                        edit.putString(KEYPRODTRANS + idd + "DATE" + i, translist.get(i).getDate());
                        edit.putInt(KEYPRODTRANS + idd + "PRICE" + i, translist.get(i).getPrice());
                        edit.apply();
                    }

                    int nominal = userList.get(idd-1).getWn();
                    int price = productlist.get(chosenid).getPrice();

                    nominal = nominal-price;

                    userList.get(idd-1).setWn(nominal);

                    //Simpan Arraylist User
                    SharedPreferences.Editor sp = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this).edit();
                    sp.putInt(KEY + "size", userList.size());
                    sp.apply();
                    for (int i = 0; i < userList.size(); i++) {
                        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this).edit();
                        edit.putString(KEY + "USERNAME" + i, userList.get(i).getUsername());
                        edit.putString(KEY + "PASSWORD" + i, userList.get(i).getPassword());
                        edit.putString(KEY + "PHONE" + i, userList.get(i).getPhoneNumber());
                        edit.putString(KEY + "DOB" + i, userList.get(i).getDateOfBirth());
                        edit.putString(KEY + "GENDER" + i, userList.get(i).getGender());
                        edit.putInt(KEY + "ID" + i, userList.get(i).getID());
                        edit.putInt(KEY + "WALLET" + i, userList.get(i).getWn());
                        edit.apply();

                    }

                    SharedPreferences.Editor test = PreferenceManager.getDefaultSharedPreferences(ProductDetailActivity.this).edit();
                    test.putBoolean("TES", false);
                    test.putBoolean("TES1", false);
                    test.apply();

                    finish();
                    startActivity(intent);
                }
            }
        });

    }
}