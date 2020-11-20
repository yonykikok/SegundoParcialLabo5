package com.example.ultimotema.clases.pais;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimotema.MainActivity;
import com.example.ultimotema.R;
import com.example.ultimotema.clases.Pais;

import java.util.List;

public class PaisAdapter extends RecyclerView.Adapter<PaisViewHolder> {
    public List<Pais> lista;
    MainActivity mainActivity;

    public PaisAdapter(MainActivity mainActivity, List<Pais> lista)
    {
        this.mainActivity=mainActivity;
        this.lista=lista;
    }
    public void setLista(List<Pais> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public PaisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,
                false);
        PaisViewHolder paisViewHolder=new PaisViewHolder(mainActivity,view);
        return paisViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaisViewHolder holder, int position) {

       holder.tvTitle.setText(lista.get(position).getName());
        holder.position=position;
        if(lista.get(position).getImagen()!=null)
        {//
            //holder.imageView.setImageBitmap(BitmapFactory.decodeFile(lista.get(position).getFlag());
            holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(lista.get(position).getImagen(),0,lista.get(position).getImagen().length));
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
