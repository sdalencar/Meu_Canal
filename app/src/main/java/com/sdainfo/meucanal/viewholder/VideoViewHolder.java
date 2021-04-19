package com.sdainfo.meucanal.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sdainfo.meucanal.R;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    public TextView titulo, descricao, data;
    public ImageView capa;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        titulo = itemView.findViewById(R.id.tv_titulo);
        capa = itemView.findViewById(R.id.iv_capa);
        descricao = itemView.findViewById(R.id.tv_descricao);
        data = itemView.findViewById(R.id.tv_data);

    }
}
