package com.novika.novikakuuf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class StoreActivity extends AppCompatActivity {

    ArrayList<Products> productlist = new ArrayList<>();

    RecyclerView rvProduct;

    private final String KEYPROD = "KEYPROD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);


        rvProduct = findViewById(R.id.rvProduct);

        //Dapat data Products
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(StoreActivity.this);
        int size = sp.getInt(KEYPROD + "size", 0);
        for (int i = 0; i < size; i++) {
            SharedPreferences sp1 = PreferenceManager.getDefaultSharedPreferences(StoreActivity.this);
            int idd = sp.getInt(KEYPROD + "ID" + i, 0);
            String namee = sp.getString(KEYPROD + "NAME" + i, "");
            int minPlayerr = sp.getInt(KEYPROD + "MINPLAYER" + i, 0);
            int maxPlayerr = sp.getInt(KEYPROD + "MAXPLAYER" + i, 0);
            int prodPricee = sp.getInt(KEYPROD + "PRICE" + i, 0);
            float longitudee = sp.getFloat(KEYPROD + "LONGITUDE" + i, 0);
            float latitudee = sp.getFloat(KEYPROD + "LATITUDE" + i, 0);

            Products data = new Products(idd, namee, minPlayerr, maxPlayerr, prodPricee, longitudee, latitudee);
            productlist.add(data);
        }

        // Adapter Products
        ProductAdapter productAdapter = new ProductAdapter();
        productAdapter.setArrayListData(productlist);

        rvProduct.setAdapter(productAdapter);
        rvProduct.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onRestart() {
        super.onRestart();


        Log.i("lifecycle", "onRestart");

    }
}