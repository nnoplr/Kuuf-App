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

public class TransAdapter extends RecyclerView.Adapter<TransAdapter.ViewHolder> {


    Context ctx;

    ArrayList<Transaction> translist;

    private final String KEYPRODTRANS = "KEYPRODTRANS";

    @NonNull
    @Override
    public TransAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflater
        ctx = parent.getContext();
        View view = LayoutInflater.from(ctx).inflate(R.layout.transactions_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransAdapter.ViewHolder holder, int position) {

        //inisialisasi data
        holder.tvName.setText(translist.get(position).getName());
        holder.tvDate.setText(translist.get(position).getDate());
        holder.tvPrice.setText(String.valueOf(translist.get(position).getPrice()));


        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translist.remove(position);

                //Simpan Transaction
                Intent intent = new Intent(ctx, HomeActivity.class);

                SharedPreferences idSelUser = PreferenceManager.getDefaultSharedPreferences(ctx);
                int idd = idSelUser.getInt("SELECTEDID", 0);
                SharedPreferences.Editor spp1 = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
                spp1.putInt(KEYPRODTRANS + idd + "size", translist.size());
                spp1.apply();
                for (int i = 0; i < translist.size(); i++) {
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
                    edit.putInt(KEYPRODTRANS + idd + "ID" + i, translist.get(i).getId());
                    edit.putString(KEYPRODTRANS + idd + "NAME" + i, translist.get(i).getName());
                    edit.putString(KEYPRODTRANS + idd + "DATE" + i, translist.get(i).getDate());
                    edit.putInt(KEYPRODTRANS + idd + "PRICE" + i, translist.get(i).getPrice());
                    edit.apply();
                }

                ctx.startActivity(intent);
                ((Activity)ctx).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return translist.size();
    }

    public void setArrayListData(ArrayList<Transaction> translist){
        this.translist = translist;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice, tvDate;
        Button deleteBtn;
        FrameLayout frTrans;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.textName);
            tvPrice = itemView.findViewById(R.id.textPrice);
            tvDate = itemView.findViewById(R.id.textTransDate);
            deleteBtn = itemView.findViewById(R.id.btnDelete);
            frTrans = itemView.findViewById(R.id.frTrans);
        }
    }

}
