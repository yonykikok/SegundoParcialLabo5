package com.example.ultimotema;

import android.os.Handler;
import android.os.Message;
import android.util.Xml;

import com.example.ultimotema.clases.Noticia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class EjecutarGetXML extends Thread {
    private String url;
    Handler handler;
    boolean esImagen;
    int positionObj;

    public EjecutarGetXML(String url, Handler handler, boolean esImagen,int positionObj){
        this.url=url;
        this.handler=handler;
        this.esImagen=esImagen;
        this.positionObj=positionObj;
    }

    @Override
    public void run(){
        ConexionHttp conexionHttp=new ConexionHttp();

        if(esImagen){
            byte[] imagen= conexionHttp.obtenerRespuestaImg(url);
            Message message= new Message();
            message.arg1=3;
            message.arg2=positionObj;
            message.obj=imagen;
            this.handler.sendMessage(message);
        }else{
            //ACA SE PROCESA EL JSON PARA TRANFORMARLO A UN OBJETO MANEJABLE.
            String string=conexionHttp.obtenerRespuesta(this.url);
            Message message=new Message();//genero el msj
            message.arg1=4;
            message.obj=parserNoticiaByXml(string);
            this.handler.sendMessage(message);

        }
    }
    private List<Noticia> parserNoticiaByXml(String xml){
        List<Noticia> lista= new ArrayList<>();
        XmlPullParser parser= Xml.newPullParser();
        try {
            parser.setInput(new StringReader(xml));
            int event = parser.getEventType();// se obtiene el tipo de evento
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
    }

}
