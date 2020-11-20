package com.example.ultimotema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.ultimotema.clases.DialogGenerico;
import com.example.ultimotema.clases.DialogNotificar;
import com.example.ultimotema.clases.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    SharedPreferences sharedPreferences;
    DialogGenerico dialog;
    String listaJson;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         sharedPreferences= super.getSharedPreferences("myConfig", Context.MODE_PRIVATE);
         listaJson=sharedPreferences.getString("lista",null);
        //sharedPreferences.edit().clear().commit();
         if(listaJson==null){
             SharedPreferences.Editor editor=sharedPreferences.edit();
             editor.putString("lista","[]");
             editor.commit();
         }

    this.refreshLista();

        //actionBar.setDisplayHomeAsUpEnabled(true);//back button enabled

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://backButton
                Log.d("TESTEOOO", "onOptionsItemSelected: "+"BACK BUTON");
                break;
            case R.id.agregar:
                dialog= new DialogGenerico(this,"","",true,false,true);
                dialog.show(getSupportFragmentManager(),"dialog");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void refreshLista(){
        sharedPreferences= super.getSharedPreferences("myConfig", Context.MODE_PRIVATE);
        listaJson=sharedPreferences.getString("lista",null);
        TextView textView= findViewById(R.id.tvLista);
        textView.setText(listaJson.toString());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        sharedPreferences= super.getSharedPreferences("myConfig", Context.MODE_PRIVATE);
        listaJson=sharedPreferences.getString("lista",null);

        boolean encontrado=false;
            List<Persona> auxList=new ArrayList<Persona>();
        for(Persona auxPersona:ObtenerListaDePersonas(listaJson)){
            if(auxPersona.getNombre().contains(query)){
                DialogNotificar dialog= new DialogNotificar(this,auxPersona.getNombre(),auxPersona.getNumero(),true,false,true);
                dialog.show(getSupportFragmentManager(),"dialog");
                encontrado=true;
                break;
            }
        }
        if(!encontrado)
        {
            DialogNotificar dialog= new DialogNotificar(this,"Sorry","La persona que busco no se encuentra",true,false,true);
            dialog.show(getSupportFragmentManager(),"dialog");
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("TESTEOOO", "onQueryTextChange: "+newText);
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);//renderizo el xml de menu

       MenuItem menuItem= menu.findItem(R.id.opSearch);//obtengo el search item por id
        SearchView searchView= (SearchView) menuItem.getActionView();//
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    public List<Persona> ObtenerListaDePersonas(String json) {
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

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return personas;
    }

}
