package com.example.ultimotema.clases;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.ultimotema.EjecutarHttp;
import com.example.ultimotema.MainActivity;
import com.example.ultimotema.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickDialogGenericoListener implements DialogInterface.OnClickListener {

    private View view;
    private MainActivity mainActivity;
    public ClickDialogGenericoListener(View view,MainActivity mainActivity){
    this.view=view;
    this.mainActivity=mainActivity;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which){
            case -1://Guardar
                Persona personaAGuardar=new Persona();

                TextView userName=view.findViewById(R.id.inputUserName);
                TextView numero=view.findViewById(R.id.inputNumero);
                personaAGuardar.setNombre(userName.getText().toString());
                personaAGuardar.setNumero(numero.getText().toString());


                SharedPreferences preferences= mainActivity.getSharedPreferences("myConfig", Context.MODE_PRIVATE);

                String listaJson=preferences.getString("lista",null);
                List<Persona> listaNueva= ObtenerListaDePersonas(listaJson, personaAGuardar);
                String lista=pasarListaAStringJson(listaNueva);

                Log.d("TESTEOOO", "onClick: "+lista);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("lista",lista);
                editor.commit();
                mainActivity.refreshLista();
                break;
            case -2://cancelar
                mainActivity.refreshLista();
            break;
            case -3:
                break;
        }
    }
    public String pasarListaAStringJson(List<Persona> personas){
        String retorno="[";
        for(int i=0; i<personas.size();i++){
            if(i!=0)
            retorno+=",";
            retorno+=personas.get(i).toString();
        }
        retorno+="]";

        return  retorno;
    }
    public List<Persona> ObtenerListaDePersonas(String json, Persona personaAGuardar) {
        List<Persona> personas = new ArrayList<>();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(json);
                for (int i=0;i<jsonArray.length();i++)
                {
                    Persona persona = new Persona();
                    JSONObject obj=jsonArray.getJSONObject(i);
                    persona.setNombre(obj.getString("nombre"));
                    persona.setNumero(obj.getString("numero"));
                    personas.add(persona);

                }
            personas.add(personaAGuardar);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return personas;
    }
}
