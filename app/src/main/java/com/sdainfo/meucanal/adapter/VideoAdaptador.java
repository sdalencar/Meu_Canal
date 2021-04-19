package com.sdainfo.meucanal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sdainfo.meucanal.R;
import com.sdainfo.meucanal.model.Item;
import com.sdainfo.meucanal.model.Video;
import com.sdainfo.meucanal.viewholder.VideoViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class VideoAdaptador extends RecyclerView.Adapter<VideoViewHolder> {

    private List<Item> videoLista = new ArrayList<>();
    private Context ctx;

    public VideoAdaptador(List<Item> videoLista, Context ctx) {
        this.videoLista = videoLista;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_adaptador, parent, false);



        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        Item item = videoLista.get(position);
        holder.titulo.setText(item.snippet.title);
        holder.descricao.setText(item.snippet.description);
        holder.data.setText(item.snippet.publishTime);

        String url = item.snippet.thumbnails.high.url;
        Picasso.get().load(url).into(holder.capa);

    }

    @Override
    public int getItemCount() {
        return videoLista.size();
    }
}
