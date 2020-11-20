package com.example.ultimotema.clases.pais;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimotema.MainActivity;
import com.example.ultimotema.R;

public class PaisViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView tvTitle;
    public ImageView imageView;
    public int position=0;
    MainActivity mainActivity;
    public PaisViewHolder(MainActivity mainActivity,@NonNull View itemView) {
        super(itemView);
        tvTitle=itemView.findViewById(R.id.labelItemTitle);
        imageView=itemView.findViewById(R.id.imgItem);
        this.mainActivity=mainActivity;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //this.mainActivity.llamarPorTelefono(this.indice);
    }
}
