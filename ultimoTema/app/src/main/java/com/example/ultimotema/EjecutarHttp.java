package com.example.ultimotema;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class EjecutarHttp extends Thread {
    private String url;
    Handler handler;
    boolean esImagen;
    public EjecutarHttp(String url, Handler handler, boolean esImagen){
        this.url=url;
        this.handler=handler;
        this.esImagen=esImagen;
    }
    @Override
    public void run(){
        ConexionHttp conexionHttp=new ConexionHttp();

            String string=conexionHttp.obtenerRespuesta(this.url);
            Message message=new Message();//genero el msj
            message.arg1=1;
            message.obj=string;
            Log.d("TESTEOOO", "run: "+message.obj);
            this.handler.sendMessage(message);
    }

/*
    private List<Noticia> parserNoticiaByXml(String xml){
        List<Noticia> lista= new ArrayList<>();
        XmlPullParser parser= Xml.newPullParser();
        try {
            parser.setInput(new StringReader(xml));
            int event = parser.getEventType();
            Noticia noticia= null;

            while(event != XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_TAG:
                        String nombreTag= parser.getName();
                        if("title".equals(nombreTag) && noticia != null){
                            noticia.titulo=parser.nextText();//toma lo que hay dentro de la etiqueta
                        }else if("item".equals(nombreTag)){
                            noticia=new Noticia();
                        }else if("enclosure".equals(nombreTag)){
                            noticia.urlImg= parser.getAttributeValue(null,"url");
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        nombreTag= parser.getName();
                        if("item".equals(nombreTag)){
                            lista.add(noticia);
                        }
                        break;
                }
                event=parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }*/

    private List<String> generarLista(String json) throws JSONException {
        //     private List<Pais> generarLista(String json) throws JSONException {
        //  Pais pais = new Pais();
        List<String> lista= new ArrayList<>();
        // List<Pais> lista= new ArrayList<Pais>();
        JSONArray jsonArray = new JSONArray(json);
        for (int i=0; i<jsonArray.length(); i++){
            JSONObject object=jsonArray.getJSONObject(i);

                /*if(object.getString("area")!="null"&&object.getString("population")!="null")
                {
                    pais.setArea(Float.valueOf(object.getString("area")));
                    pais.setName(object.getString("name"));
                    pais.setRegion(object.getString("region"));
                    pais.setPopulation(Float.valueOf(object.getString("population")));
                    lista.add(pais);
                }*/
            //      lista.add(object.getString("area"));
        }

        //        Log.d("MOSTRANDOESTO", "handleMessage: "+lista.get(lista.size()-1));
        return  lista;


    }
}
