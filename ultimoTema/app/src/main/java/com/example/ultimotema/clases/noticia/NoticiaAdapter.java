package com.example.ultimotema.clases.noticia;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ultimotema.MainActivity;
import com.example.ultimotema.R;
import com.example.ultimotema.clases.Noticia;

import java.util.List;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaViewHolder> {
    public List<Noticia> noticias;
    MainActivity mainActivity;
    public NoticiaAdapter(MainActivity mainActivity,List<Noticia> noticias)
    {
        this.mainActivity=mainActivity;
        this.noticias=noticias;
    }
    public void setLista(List<Noticia> lista) {
        this.noticias = lista;
    }

    @NonNull
    @Override
    public NoticiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,
                false);
        NoticiaViewHolder noticiaViewHolder=new NoticiaViewHolder(mainActivity,view);
        return noticiaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiaViewHolder holder, int position) {
        holder.tvTitle.setText(noticias.get(position).getTitulo());
        if(noticias.get(position).getImagen()!=null)
        {
            holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(noticias.get(position).getImagen(),0,noticias.get(position).getImagen().length));
        }
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }
}
