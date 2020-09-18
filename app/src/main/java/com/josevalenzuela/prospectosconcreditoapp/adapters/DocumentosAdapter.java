package com.josevalenzuela.prospectosconcreditoapp.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.josevalenzuela.prospectosconcreditoapp.R;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

public class DocumentosAdapter extends RecyclerView.Adapter<DocumentosAdapter.DocumentosViewHolder> {
    private List<Uri> urisDocs;

    public DocumentosAdapter(List<Uri> urisDocs) {
        this.urisDocs = urisDocs;
    }

    @NonNull
    @Override
    public DocumentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_doc_item, parent, false);
        DocumentosViewHolder holder = new DocumentosViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentosViewHolder holder, int position) {
        Uri uri = urisDocs.get(position);
        holder.imageView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return urisDocs.size();
    }


    public static class DocumentosViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public DocumentosViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageViewDoc);
        }
    }
}
