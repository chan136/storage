package com.example.storage.Bean;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.storage.R;
import com.example.storage.Utils.DrawableColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerry on 2017/12/26.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private List<Photo> mPhotoList;
    private View view;
    private List<Integer> list = new ArrayList<>();

    public List<Integer> getList() {
        return list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        ImageButton uncheck;
        ImageButton check;
        public ViewHolder(View view) {
            super(view);
            iv = (ImageView) view.findViewById(R.id.photo);
            uncheck = (ImageButton) view.findViewById(R.id.uncheck);
            check = (ImageButton) view.findViewById(R.id.check);
        }
    }

    public PhotoAdapter(List<Photo> photoList) {
        mPhotoList = photoList;
    }

    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_photo,parent,false);
        ViewHolder holder = new ViewHolder(view);
        list.clear();
        holder.uncheck.setVisibility(View.VISIBLE);
        holder.check.setVisibility(View.GONE);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Photo photo = mPhotoList.get(position);
        holder.iv.setImageBitmap(photo.getBitmap());
        holder.uncheck.setVisibility(View.VISIBLE);
        holder.check.setVisibility(View.GONE);
        DrawableColor.changeColor(view,R.id.uncheck,R.drawable.ic_uncheck_box_black_24dp,R.color.colorPrimary);
        DrawableColor.changeColor(view,R.id.check,R.drawable.ic_check_box_black_24dp,R.color.checkout);
        holder.uncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.uncheck.setVisibility(View.GONE);
                holder.check.setVisibility(View.VISIBLE);
                list.add(position);
            }
        });
        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.uncheck.setVisibility(View.VISIBLE);
                holder.check.setVisibility(View.GONE);
                for(int i = 0; i < list.size(); i++){
                    if(list.get(i) == position){
                        list.remove(i);
                    }
                }
            }

        });

    }

}

