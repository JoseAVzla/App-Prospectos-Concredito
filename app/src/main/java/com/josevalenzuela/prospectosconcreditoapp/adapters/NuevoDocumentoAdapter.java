package com.josevalenzuela.prospectosconcreditoapp.adapters;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.josevalenzuela.prospectosconcreditoapp.R;

import java.util.List;

public class NuevoDocumentoAdapter extends RecyclerView.Adapter<NuevoDocumentoAdapter.NuevoDocumentoViewHolder> {
    private List<Bitmap> bitmaps;

    public NuevoDocumentoAdapter(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    @NonNull
    @Override
    public NuevoDocumentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_doc_item, parent, false);
        NuevoDocumentoViewHolder holder = new NuevoDocumentoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NuevoDocumentoViewHolder holder, int position) {
        Bitmap bitmap = bitmaps.get(position);
        holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return bitmaps.size();
    }


    public static class NuevoDocumentoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public NuevoDocumentoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageViewDoc);
        }
    }
}
