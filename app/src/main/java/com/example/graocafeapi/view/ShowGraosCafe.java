package com.example.graocafeapi.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.graocafeapi.R;
import com.example.graocafeapi.api.Api;
import com.example.graocafeapi.model.Characteristics;
import com.example.graocafeapi.model.TiposGraosCafe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowGraosCafe extends AppCompatActivity {


    private final Gui gui = new Gui();
    private Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);
        InitPermisionGeral();
        ctx = this;
        gui.btnShowGrain = findViewById(R.id.btnShowGrain);
        gui.imgShowGrain = findViewById(R.id.imgShowGrain);
        setTitle(getString(R.string.telaprincipal_title));
        gui.imgShowGrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGrains();
            }
        });

        gui.btnShowGrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGrains();
            }
        });

    }

    private void showGrains() {
        gui.btnShowGrain.setText("Processando...");
        new Api().getItens().getItens().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    List<TiposGraosCafe> tiposGraosCafeList = new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(response.body().string());
                        int length = jsonArray.length();
                        for(int i=0; i < length; i++){
                            TiposGraosCafe tp = new TiposGraosCafe(
                                    jsonArray.getJSONObject(i).getString("title"),
                                    jsonArray.getJSONObject(i).getString("img_path"),
                                    jsonArray.getJSONObject(i).getString("amont")
                            );

                            JSONArray caracteristicas = jsonArray.getJSONObject(i).getJSONArray("caracteristicas");
                            List<Characteristics> characteristicsList=new ArrayList<>();
                            for(int next=0; next < caracteristicas.length(); next++){
                               String[] split = caracteristicas.get(next).toString()
                                        .replace("{","").replace("}","").split(":");
                                characteristicsList.add(
                                        new Characteristics(split[0],split[1])
                                );
                            }
                            tp.setCharacteristicsList(characteristicsList);
                            tiposGraosCafeList.add(tp);
                    }
                   } catch (Exception e) {
                        gui.btnShowGrain.setText("* Erro ao Consultar Servidor >> ");
                   }
                    Intent tiposGrainsCoffer = new Intent(ctx, TiposGrainsCafe.class);
                    tiposGrainsCoffer.putExtra("typesCoffers", (Serializable) tiposGraosCafeList);
                    gui.btnShowGrain.setText("Mostrar Tipos de Grão >> ");
                    startActivity(tiposGrainsCoffer);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                gui.btnShowGrain.setText("* Erro ao Consultar Servidor >> ");
            }
        });

    }


    /*Permisoes Gerais*/
    public void InitPermisionGeral() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.INSTANT_APP_FOREGROUND_SERVICE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_BOOT_COMPLETED) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.INSTALL_SHORTCUT) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.LOCATION_HARDWARE) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(this, android.Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {

            } else {
                ActivityCompat.requestPermissions(ShowGraosCafe.this,
                        new String[]{
                                Manifest.permission.INTERNET,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INSTANT_APP_FOREGROUND_SERVICE,
                                Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.RECEIVE_BOOT_COMPLETED,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.INSTALL_SHORTCUT,
                                Manifest.permission.LOCATION_HARDWARE,
                                Manifest.permission.SYSTEM_ALERT_WINDOW


                        }, 10000);
            }

        }


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public static class Gui {
        private ImageView imgShowGrain;
        private Button btnShowGrain;
    }


}