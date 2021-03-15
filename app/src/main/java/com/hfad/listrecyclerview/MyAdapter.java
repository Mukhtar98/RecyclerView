package com.hfad.listrecyclerview;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Model> list;
    View view;
    Model temp;

    public MyAdapter(Context mContext, ArrayList<Model> list){
        this.mContext = mContext;
        this.list =  list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.each_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.menuPopUp.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(mContext, v);
            popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.delete:
                            Toast.makeText(mContext, "Delete clicked", Toast.LENGTH_SHORT).show();
                            temp = new Model(list.get(position).getName(), list.get(position).getImage());
                            deleteItem(position);
                            break;
                    }
                    return true;
                }
            });
        });
    }

    private void deleteItem(int position)
    {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, menuPopUp;
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            menuPopUp = itemView.findViewById(R.id.itemDelete);
            name = itemView.findViewById(R.id.textView);
        }
    }
}
