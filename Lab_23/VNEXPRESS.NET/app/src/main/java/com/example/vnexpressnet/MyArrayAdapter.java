package com.example.vnexpressnet;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<ListArticle> {
    private Activity context;
    private int layoutID;
    private ArrayList<ListArticle> arr;

    public MyArrayAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<ListArticle> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutID = resource;
        this.arr = objects;
    }

    static class ViewHolder {
        ImageView imgItem;
        TextView txtTitleItem;
        TextView txtInfoItem;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutID, null);
            holder = new ViewHolder();
            holder.imgItem = convertView.findViewById(R.id.imgView);
            holder.txtTitleItem = convertView.findViewById(R.id.txtTitle);
            holder.txtInfoItem = convertView.findViewById(R.id.txtInfo);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ListArticle currentArticle = arr.get(position);

        if (currentArticle.getImg() != null) {
            holder.imgItem.setImageBitmap(currentArticle.getImg());
        } else {
            holder.imgItem.setImageResource(R.mipmap.ic_launcher);
        }

        holder.txtTitleItem.setText(currentArticle.getTitle());
        holder.txtInfoItem.setText(currentArticle.getInfo());

        return convertView;
    }
}
