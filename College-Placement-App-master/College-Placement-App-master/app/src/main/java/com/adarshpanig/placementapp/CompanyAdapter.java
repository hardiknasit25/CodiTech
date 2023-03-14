package com.adarshpanig.placementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;
    private CompanyAdapter.OnItemClickListener mListener1;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);

    }

    public CompanyAdapter(Context context,List<Upload> uploads)
    {
        mContext=context;
        mUploads=uploads;
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener1=listener;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView1;
        public TextView textView2;

        public ImageViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgbtn1);
            textView1=itemView.findViewById(R.id.cmptitle1);
            textView2=itemView.findViewById(R.id.cmpdesc1);

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
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.company_item,parent,false);
        ImageViewHolder imh = new ImageViewHolder(v,mListener1);
        return imh;
    }
    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload uploadCurrent=mUploads.get(position);
        holder.textView1.setText(uploadCurrent.getName());
        holder.textView2.setText(uploadCurrent.getDesc());
        Picasso.with(mContext).load(uploadCurrent.getImageUrl()).fit().centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }
}
