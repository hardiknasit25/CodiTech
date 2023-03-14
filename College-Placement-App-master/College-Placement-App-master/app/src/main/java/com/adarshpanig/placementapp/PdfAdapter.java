package com.adarshpanig.placementapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.PdfViewHolder> {
    private Context mContext;
    private List<uploadPDF> mFileList;
    private OnItemClickListener mListener2;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);

    }

    public PdfAdapter(Context context, List<uploadPDF> uploadPDFS)
    {
        mContext=context;
        mFileList=uploadPDFS;
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener2=listener;
    }


    public static class PdfViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewPdf;
        TextView textViewPdf;
        public PdfViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageViewPdf=itemView.findViewById(R.id.imageViewPdf);
            textViewPdf=itemView.findViewById(R.id.textViewpdf);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pdf,parent,false);
        PdfViewHolder pvh = new PdfViewHolder(v,mListener2);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewHolder holder, int position) {
       uploadPDF currentItem = mFileList.get(position);
       holder.textViewPdf.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return mFileList.size();
    }

}
