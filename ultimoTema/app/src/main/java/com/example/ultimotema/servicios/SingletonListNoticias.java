package com.example.ultimotema.servicios;

import com.example.ultimotema.clases.Noticia;

import java.util.ArrayList;
import java.util.List;

public class SingletonListNoticias {
    private static SingletonListNoticias servicio;
    private List<Noticia> noticias;
    private SingletonListNoticias(){
        this.noticias=new ArrayList<>();
    }

    public static SingletonListNoticias getInstancia(){
        if(servicio==null)
        {
           return servicio=new SingletonListNoticias();
        }
        return servicio;
    }
    public void cargarLista(List<Noticia> listaNueva){
        this.noticias=listaNueva;
    }
    public List<Noticia> getLista(){
        return noticias;
    }
}
