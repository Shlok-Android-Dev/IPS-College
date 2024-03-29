package com.example.ipscollege.ebook;

import static android.app.ProgressDialog.show;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ipscollege.R;

import java.util.ArrayList;
import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder> {

 private  Context context;
    private  List<EbookData> list;

    public EbookAdapter(Context context, List<EbookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ebook_item_layout,parent,false);

        return new EbookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookAdapter.EbookViewHolder holder,final int position) {
//
            holder.EbookName.setText(list.get(position).getPdfTitle());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, PdfViewerActivity.class);
               intent.putExtra("pdfUrl",list.get(position).getPdfUrl());
               context.startActivity(intent);
           }
       });
       
       holder.EbookDownload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse(list.get(position).getPdfUrl()));
               context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void Filterdlist(ArrayList<EbookData> filterList) {

        list = filterList;
        notifyDataSetChanged();
    }

    public class EbookViewHolder extends RecyclerView.ViewHolder {
        private TextView EbookName;
        private ImageView EbookDownload;
        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);

            EbookName = itemView.findViewById(R.id.ebookName);
            EbookDownload = itemView.findViewById(R.id.ebookDownload);
        }
    }


}
