package com.josevalenzuela.prospectosconcreditoapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.josevalenzuela.prospectosconcreditoapp.R;

import java.util.List;

public class DocumentosAdapter extends RecyclerView.Adapter<DocumentosAdapter.DocumentosViewHolder> {
    private List<String> ulrs;
    private Context mContext;
    public DocumentosAdapter(List<String> ulrs, Context context) {
        this.ulrs = ulrs;
        this.mContext = context;
    }

    @NonNull
    @Override
    public DocumentosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_doc_item, parent, false);
        DocumentosAdapter.DocumentosViewHolder holder = new DocumentosAdapter.DocumentosViewHolder(view);
        return holder;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull DocumentosViewHolder holder, int position) {
        System.out.println("Glide testing");
        Glide.with(mContext)
            .load(ulrs.get(position))
            .placeholder(R.drawable.ic_baseline_photo_camera_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .override(200, 200)
            .centerCrop()
            .into(holder.imageView);
        System.out.println("Glide working");
    }

    @Override
    public int getItemCount() {
        return ulrs.size();
    }

    public static class DocumentosViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public DocumentosViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageViewDoc);
        }
    }
}
