package com.example.ultimotema.clases;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.ultimotema.MainActivity;
import com.example.ultimotema.R;

public class DialogNotificar extends DialogFragment {

    private  String mensaje;
    private  String titulo;
    private Boolean neutral;
    private Boolean negative;
    private Boolean positive;
    private MainActivity mainActivity;

    public DialogNotificar(MainActivity main,String titulo, String mensaje, Boolean positive, Boolean neutral, Boolean negative) {
        this.mensaje = mensaje;
        this.titulo = titulo;
        this.neutral = neutral;
        this.negative = negative;
        this.positive = positive;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());//obtiene la activity que genere este objeto
        builder.setTitle(this.titulo);
        builder.setMessage(this.mensaje);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_notificar,null);
        ClickDialogNotificar listener=new ClickDialogNotificar(view,mainActivity);

        builder.setView(view);
        if(this.neutral){
           // builder.setNeutralButton("--",listener);
        }
        if(this.positive){
            builder.setPositiveButton("OK",listener);
        }
        if(this.negative){
           // builder.setNegativeButton("Cancelar",listener);
        }
        return  builder.create();
    }
}
