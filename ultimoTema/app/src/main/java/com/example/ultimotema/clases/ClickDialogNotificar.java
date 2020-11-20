package com.example.ultimotema.clases;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ultimotema.MainActivity;
import com.example.ultimotema.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClickDialogNotificar implements DialogInterface.OnClickListener{
    private View view;
    private MainActivity mainActivity;

    public ClickDialogNotificar(View view,MainActivity mainActivity){
        this.view=view;
        this.mainActivity=mainActivity;
    }
    @Override
    public void onClick(DialogInterface dialog, int which) {

        switch (which){
            case -1://Guardar
                break;
            case -2://cancelar

                break;
            case -3:
                break;
        }
    }

}
