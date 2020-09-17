package com.josevalenzuela.prospectosconcreditoapp.adapters;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.josevalenzuela.prospectosconcreditoapp.R;
import com.josevalenzuela.prospectosconcreditoapp.models.Prospecto;

import java.util.List;

public class AddDocsAdapter extends RecyclerView.Adapter<AddDocsAdapter.DocsViewHolder>{
    private List<Prospecto> prospectosList;
    private Context context;
    private AddDocsAdapter.OnItemClickListenerDoc onItemClickListener;

    public AddDocsAdapter(List<Prospecto> prospectosList, Context context, AddDocsAdapter.OnItemClickListenerDoc onItemClickListener) {
        this.prospectosList = prospectosList;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public AddDocsAdapter.DocsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_doc_item, parent, false);
        AddDocsAdapter.DocsViewHolder prospectosViewHolder = new AddDocsAdapter.DocsViewHolder(view, onItemClickListener);
        return prospectosViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddDocsAdapter.DocsViewHolder holder, int position) {
        Prospecto prospecto = prospectosList.get(position);

        //Seleccionando el prospecto para visualizar su informacion completa.
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imageView.setColorFilter(context.getResources().getColor(R.color.colorAccent));
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.Images.Media.TITLE, "Photodocs" + position);
                Uri img_uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                Intent camara = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camara.putExtra(MediaStore.EXTRA_OUTPUT,img_uri);
                if (camara.resolveActivity(context.getPackageManager()) != null) {
                    ((Activity) context).startActivityForResult(camara, 1);
                }




            }
        });


    }

    @Override
    public int getItemCount() {
        return prospectosList.size();
    }


    public static class DocsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private CardView cardViewAddDocs;
        private ImageView imageView;
        private LinearLayout linearLayout;
        private AddDocsAdapter.OnItemClickListenerDoc onItemClickListener;

        public DocsViewHolder(@NonNull View itemView, AddDocsAdapter.OnItemClickListenerDoc onItemClickListener) {
            super(itemView);
            //this.imageView = itemView.findViewById(R.id.imageView);
            this.cardViewAddDocs = itemView.findViewById(R.id.cardViewAddDocsId);
            this.linearLayout = itemView.findViewById(R.id.layoutStatusColorId);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            onItemClickListener.onClickItem(getAdapterPosition());
        }
    }

    public interface OnItemClickListenerDoc{
        void onClickItem(int position);
    }
}
