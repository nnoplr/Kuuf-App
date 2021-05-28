package com.novika.novikakuuf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    Context ctx;

    ArrayList<Products> productlist;

    private final String KEYCHOSEN = "KEYCHOSEN";

    private boolean tes1;


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflater
        ctx = parent.getContext();
        View view = LayoutInflater.from(ctx).inflate(R.layout.products_item, parent, false);

        return new ProductAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

//        tes = true;
        SharedPreferences.Editor test = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
        test.putBoolean("TES1", tes1);
        test.apply();

        //inisialisasi data
        holder.tvName.setText(productlist.get(position).getName());
        holder.tvMinPlay.setText(String.valueOf(productlist.get(position).getMinPlayer()));
        holder.tvMaxPlay.setText(String.valueOf(productlist.get(position).getMaxPlayer()));
        holder.tvPrice.setText(String.valueOf(productlist.get(position).getPrice()));

        holder.frProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, ProductDetailActivity.class);

                SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
                edit.putInt(KEYCHOSEN + "ID", position);
                edit.apply();

                SharedPreferences test = PreferenceManager.getDefaultSharedPreferences(ctx);
                tes1 = test.getBoolean("TES1", true);

                if (tes1==false){
                    ((Activity)ctx).finish();
                }
//                ((Activity)ctx).finish();
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productlist.size();
    }

//    public void setData(String[] productName, String[] productDate, int[] productPrice){
//        this.productName = productName;
//        this.productDate = productDate;
//        this.productPrice = productPrice;
//    }

    public void setArrayListData(ArrayList<Products> productlist){
        this.productlist = productlist;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice, tvMinPlay, tvMaxPlay;
        FrameLayout frProd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.textName);
            tvPrice = itemView.findViewById(R.id.textProdPrice);
            tvMinPlay = itemView.findViewById(R.id.textMinPlay);
            tvMaxPlay = itemView.findViewById(R.id.textMaxPlay);
            frProd = itemView.findViewById(R.id.frProd);
        }
    }

}
