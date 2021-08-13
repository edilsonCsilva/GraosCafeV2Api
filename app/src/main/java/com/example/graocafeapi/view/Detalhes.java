package com.example.graocafeapi.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graocafeapi.R;
import com.example.graocafeapi.api.Api;
import com.example.graocafeapi.interfaces.OnClickList;
import com.example.graocafeapi.model.Characteristics;
import com.example.graocafeapi.model.TiposGraosCafe;

import java.io.InputStream;
import java.util.List;

public class Detalhes extends AppCompatActivity {
    private final Gui gui = new Gui();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        gui.ctx = this;
        gui.ab = getSupportActionBar();
        gui.ab.setDisplayHomeAsUpEnabled(true);
        gui.ab.setHomeButtonEnabled(true);

        
        gui.marca = (TextView) findViewById(R.id.marca);
        gui.format_coffer = (TextView) findViewById(R.id.format_coffer);
        gui.photo = (ImageView) findViewById(R.id.photo);
        gui.type_coffer = (TextView) findViewById(R.id.type_coffer);
        gui.type_embalagen = (TextView) findViewById(R.id.type_embalagen);
        gui.format_venda = (TextView) findViewById(R.id.format_venda);
        gui.peso = (TextView) findViewById(R.id.peso);
        gui.amont = (TextView) findViewById(R.id.amont);
        Intent i = getIntent();
        gui.tiposGraosCafe = (TiposGraosCafe) i.getSerializableExtra("typesCoffers");
        if (!gui.tiposGraosCafe.getImg_path().isEmpty()) {
            gui.photo.setImageDrawable(getResources().getDrawable(R.drawable.load));
            new DownloadImageTask(gui.photo).execute(String.format("%sfiles/%s", Api.BASE_URL, gui.tiposGraosCafe.getImg_path()));
        }

        try {
            gui.amont.setText("RS: "+gui.tiposGraosCafe.getAmont());
        } catch (Exception e) {
            gui.amont.setText("RS: 0.00");
        }

        try {
            gui.marca.setText(gui.tiposGraosCafe.getCharacteristicsList().get(0).getDescricao_valor());
        } catch (Exception e) {
            gui.marca.setText(R.string.nada);
        }

        try {
            gui.format_coffer.setText(gui.tiposGraosCafe.getCharacteristicsList().get(1).getDescricao_valor());
        } catch (Exception e) {
            gui.format_coffer.setText(R.string.nada);
        }

        try {
            gui.type_coffer.setText(gui.tiposGraosCafe.getCharacteristicsList().get(2).getDescricao_valor());
        } catch (Exception e) {
            gui.type_coffer.setText(R.string.nada);
        }

        try {
            gui.type_embalagen.setText(gui.tiposGraosCafe.getCharacteristicsList().get(3).getDescricao_valor());
        } catch (Exception e) {
            gui.type_embalagen.setText(R.string.nada);
        }

        try {
            gui.format_venda.setText(gui.tiposGraosCafe.getCharacteristicsList().get(4).getDescricao_valor());
        } catch (Exception e) {
            gui.format_venda.setText(R.string.nada);
        }

        try {
            gui.peso.setText(gui.tiposGraosCafe.getCharacteristicsList().get(5).getDescricao_valor());
        } catch (Exception e) {
            gui.peso.setText(R.string.nada);
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finishAffinity();
                break;
            default:
                break;
        }
        return true;
    }

    public static class Gui {
        public ActionBar ab;
        public Context ctx;
        private TiposGraosCafe tiposGraosCafe;
        private TextView marca, format_coffer, type_coffer, type_embalagen, format_venda, peso,amont;
        private ImageView photo;


    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {

                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}