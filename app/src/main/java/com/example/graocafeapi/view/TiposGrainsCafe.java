package com.example.graocafeapi.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.graocafeapi.R;
import com.example.graocafeapi.api.Api;
import com.example.graocafeapi.interfaces.OnClickList;
import com.example.graocafeapi.model.TiposGraosCafe;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TiposGrainsCafe extends AppCompatActivity {
    private final Gui gui = new Gui();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_grains_cafe);
        setTitle(getString(R.string.types_grain_coofers));
        Intent i = getIntent();
        gui.ctx = this;
        gui.ab= getSupportActionBar();
        gui.ab.setDisplayHomeAsUpEnabled(true);
        gui.ab.setHomeButtonEnabled(true);
        gui.mRecyclerViewGrains=(RecyclerView)findViewById(R.id.recycler_view_layour_recycler);
        gui.tiposGraosCafeList = (List<TiposGraosCafe>) i.getSerializableExtra("typesCoffers");
        setupRecycler();





    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(gui.ctx, ShowGraosCafe.class));
                finishAffinity();
                break;
            default:break;
        }
        return true;
    }

    private void setupRecycler() {
        OnClickList onClickList=new OnClickList() {
            @Override
            public void Click(Object obj) {
                TiposGraosCafe tiposGraosCafe=(TiposGraosCafe)obj;
                Intent tiposGrainsCoffer = new Intent(gui.ctx, Detalhes.class);
                tiposGrainsCoffer.putExtra("typesCoffers", (Serializable) tiposGraosCafe);
                startActivity(tiposGrainsCoffer);


            }
        };

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(gui.ctx);
        gui.mRecyclerViewGrains.setLayoutManager(layoutManager);
        // Adiciona o adapter que irá anexar os objetos à lista.
        // Está sendo criado com lista vazia, pois será preenchida posteriormente.
        gui.mAdapter = new LineGrainAdapter(gui.tiposGraosCafeList,onClickList);
        gui.mRecyclerViewGrains.setAdapter(gui.mAdapter);


    }



    public  static class Gui{
        private List<TiposGraosCafe> tiposGraosCafeList;
        private RecyclerView mRecyclerViewGrains;
        private LineGrainAdapter mAdapter;
        private Context ctx;
        private ActionBar ab;
        private OnClickList onClickList;


    }



    public static class LineGrainHolder extends RecyclerView.ViewHolder {
        public TextView title,amont;
        public ImageView foto;
        public Button btnMais;
        public OnClickList onClickList;
        public TiposGraosCafe tiposGraosCafe;


        public void LineGrainHolder(OnClickList onClickList){
            this.onClickList=onClickList;
        }

        public LineGrainHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.main_line_title);
            foto=(ImageView) itemView.findViewById(R.id.foto);
            amont=(TextView)itemView.findViewById(R.id.main_line_amont);
            btnMais=(Button) itemView.findViewById(R.id.btnMais);



        }
    }

    public static class LineGrainAdapter extends RecyclerView.Adapter<LineGrainHolder> {

        private final OnClickList onClickList;
        private List<TiposGraosCafe> typesCoffes = new ArrayList<>();
        public LineGrainAdapter(List<TiposGraosCafe> typesCoffes,OnClickList onClickList) {
            this.typesCoffes = typesCoffes;
            this.onClickList=onClickList;

        }

        @Override
        public LineGrainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new LineGrainHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_line_view, parent, false));
        }

        @Override
        public void onBindViewHolder(LineGrainHolder holder, int position) {
            holder.title.setText(this.typesCoffes.get(position).getNome());
            final Object item = this.typesCoffes.get(position);
            if(!this.typesCoffes.get(position).getImg_path().isEmpty()){
                holder.foto.setImageResource(R.drawable.load);
                new DownloadImageTask(holder.foto).execute(String.format("%sfiles/%s", Api.BASE_URL,this.typesCoffes.get(position).getImg_path()));
            }
            holder.amont.setText(String.format("RS: %S",this.typesCoffes.get(position).getAmont()));


            holder.btnMais.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickList.Click(item);
                }
            });


        }

        @Override
        public int getItemCount() {
            return typesCoffes != null ? typesCoffes.size() : 0;
        }

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