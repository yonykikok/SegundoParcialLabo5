package com.example.ultimotema.clases;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ultimotema.MainActivity;
import com.example.ultimotema.R;

import org.w3c.dom.Text;

public class DialogGenerico extends DialogFragment {

    private  String mensaje;
    private  String titulo;
    private Boolean neutral;
    private Boolean negative;
    private Boolean positive;
    private MainActivity mainActivity;
    private String nameBtnUno;
    private String nameBtnDos;

    public void setNameBtnUno(String nameBtnUno) {
        this.nameBtnUno = nameBtnUno;
    }

    public void setNameBtnDos(String nameBtnDos) {
        this.nameBtnDos = nameBtnDos;
    }

    public void setNameBtnTres(String nameBtnTres) {
        this.nameBtnTres = nameBtnTres;
    }

    private String nameBtnTres;

    public DialogGenerico( String titulo, String mensaje){
        this.titulo=titulo;
        this.mensaje=mensaje;
    }

    public DialogGenerico(MainActivity main,String titulo, String mensaje, Boolean positive, Boolean neutral, Boolean negative){
        this.titulo=titulo;
        this.mensaje=mensaje;
        this.positive=positive;
        this.neutral=neutral;
        this.negative=negative;
        this.mainActivity=main;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());//obtiene la activity que genere este objeto
        builder.setTitle(this.titulo);
        builder.setMessage(this.mensaje);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog,null);
        ClickDialogGenericoListener listener=new ClickDialogGenericoListener(view,mainActivity);

        builder.setView(view);
        if(this.neutral){
            builder.setNeutralButton("--",listener);
        }
        if(this.positive){
            builder.setPositiveButton("Guardar",listener);
        }
        if(this.negative){
            builder.setNegativeButton("Cancelar",listener);
        }
        return  builder.create();
    }
}
