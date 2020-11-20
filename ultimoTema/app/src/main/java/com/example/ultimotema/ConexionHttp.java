package com.example.ultimotema;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConexionHttp {


    public String obtenerRespuesta(String inputUrl){

        try {
            URL url= new URL(inputUrl);
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            Log.d("RESPUESTA!!", "obtenerRespuesta: PASOOOOO");
            if(200==urlConnection.getResponseCode())
            {
                InputStream inputString=urlConnection.getInputStream();

                ByteArrayOutputStream baos= new ByteArrayOutputStream(); //genera un array de bytes

                byte[] buffer=new byte[1024];
                int lenght=0;
                while((lenght = inputString.read(buffer)) != -1){
                    baos.write(buffer,0,lenght);
                }
                inputString.close();
                Log.d("RESPUESTA!!", "obtenerRespuesta: "+baos.toString());
                return baos.toString();

            }
            else{
                Log.d("RESPUESTA", "errorrrrrrr exception");
                throw  new RuntimeException("Error: "+urlConnection.getResponseCode());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public byte[] obtenerRespuestaImg(String inputUrl){
        try {
            URL url= new URL(inputUrl);
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(200==urlConnection.getResponseCode())
            {
                InputStream inputString=urlConnection.getInputStream();

                ByteArrayOutputStream baos= new ByteArrayOutputStream(); //genera un array de bytes

                byte[] buffer=new byte[1024];
                int lenght=0;
                while((lenght = inputString.read(buffer)) != -1){
                    baos.write(buffer,0,lenght);
                }
                inputString.close();
                Log.d("RESPUESTA!!", "obtenerRespuesta: "+baos.toString());
                return baos.toByteArray();

            }
            else{
                throw  new RuntimeException("Error: "+urlConnection.getResponseCode());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
