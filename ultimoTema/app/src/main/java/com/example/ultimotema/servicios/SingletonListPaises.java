package com.example.ultimotema.servicios;

import com.example.ultimotema.clases.Pais;

import java.util.ArrayList;
import java.util.List;

public class SingletonListPaises {

    private static SingletonListPaises servicio;
    private List<Pais> lista;
    private SingletonListPaises(){
        this.lista=new ArrayList<>();
    }

    public static SingletonListPaises getInstancia(){
        if(servicio==null)
        {
            return servicio=new SingletonListPaises();
        }
        return servicio;
    }
    public void cargarLista(List<Pais> listaNueva){
        this.lista=listaNueva;
    }

    public List<Pais> getLista(){
        return lista;
    }
}
