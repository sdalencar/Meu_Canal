package com.sdainfo.meucanal.actvity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.sdainfo.meucanal.R;
import com.sdainfo.meucanal.adapter.VideoAdaptador;
import com.sdainfo.meucanal.api.ApiYoutube;
import com.sdainfo.meucanal.helper.RetrofitConfig;
import com.sdainfo.meucanal.helper.YoutubeConfig;
import com.sdainfo.meucanal.listerner.RecyclerItemClickListener;
import com.sdainfo.meucanal.model.Item;
import com.sdainfo.meucanal.model.Resultado;
import com.sdainfo.meucanal.model.Video;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<Item> itemLista = new ArrayList<>();
    private Resultado resultado;

    private VideoAdaptador videoAdaptador;
    private MaterialSearchView searchView;

    private Retrofit retrofit;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = RetrofitConfig.getRetrofit();

        //inicia componetes
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        searchView = findViewById(R.id.searchView);

        buscarVideos("");


        // seta valores as componentes
        toolbar.setTitle(getString(R.string.meu ));
        setSupportActionBar(toolbar);


        //config buscas searchView
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                 buscarVideos(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                buscarVideos("");

            }
        });


    }

    private void buscarVideos(String q){

        String pesquisa = q.replaceAll(" ", "+");

        ApiYoutube apiYoutube = retrofit.create(ApiYoutube.class);

        apiYoutube.recuperarVideos("snippet", "date","20", YoutubeConfig.CHAVE_YOUTUBE_API,YoutubeConfig.CANAL_ID, pesquisa )
        .enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if(response.isSuccessful()){
                    resultado = response.body();
                    itemLista = resultado.items;
                   configurarRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });

    }

    public void configurarRecyclerView(){
        videoAdaptador = new VideoAdaptador(itemLista, this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(videoAdaptador);

        //configuração dos cliques
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Item item = itemLista.get(position);
                String idVideo = item.id.videoId;

                Intent intent = new Intent(new Intent(MainActivity.this, PlayerActivity.class));
                intent.putExtra("idVideo", idVideo);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }
        ));


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_busca, menu);

        MenuItem menuItem = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(menuItem);

        return true;
    }


}