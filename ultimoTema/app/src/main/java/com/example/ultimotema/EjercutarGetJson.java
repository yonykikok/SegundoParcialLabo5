package com.example.ultimotema;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.ultimotema.clases.Pais;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EjercutarGetJson extends Thread {

    private String url;
    Handler handler;
    boolean esImagen;
    int positionObj;

    public EjercutarGetJson(String url, Handler handler, boolean esImagen,int positionObj){
        this.url=url;
        this.handler=handler;
        this.esImagen=esImagen;
        this.positionObj=positionObj;
    }

     @Override
    public void run(){
         ConexionHttp conexionHttp = new ConexionHttp();

         if(esImagen){
             byte[] imagen= conexionHttp.obtenerRespuestaImg(url);
             Message message= new Message();
            // message.arg1=MainActivity.RESPUESTA_IMG;
             message.arg2=positionObj;
             message.obj=imagen;
             this.handler.sendMessage(message);
         }else {
             String string = conexionHttp.obtenerRespuesta(this.url);
             Message message = new Message();//genero el msj
            // message.arg1 = MainActivity.RESPUESTA_JSON;
             message.obj = obtenerListaDePaises(string);
             this.handler.sendMessage(message);
         }
    }

    public List<Pais> obtenerListaDePaises(String json) {
        List<Pais> paises= new ArrayList<>();
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(json);
            for (int i=0;i<jsonArray.length();i++)
            {
                Pais pais = new Pais();
                JSONObject obj=jsonArray.getJSONObject(i);

                if(obj.getString("population")!="null" && obj.getString("area") != "null")
                {
                    pais.setName(obj.getString("name"));
                    pais.setArea((float) obj.getDouble("area"));
                    pais.setPopulation((float) obj.getDouble("population"));
                    pais.setRegion(obj.getString("region"));
                    pais.setFlag(obj.getString("flag"));
                }else{
                    pais.setName(obj.getString("name"));
                    pais.setArea((float) 0);
                    pais.setPopulation((float) 0);
                    pais.setRegion(obj.getString("region"));
                    pais.setFlag(obj.getString("flag"));
                }
                Log.d("TESTEOOO1", "obtenerListaDePaises: "+pais.getFlag());
                paises.add(pais);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return paises;
    }
}
