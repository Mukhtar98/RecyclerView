package com.hfad.listrecyclerview;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private OnItemClickListener itemClickListener;
    private Context mContext;
    private ArrayList<Model> list;
    View view;
    Model temp;
    private boolean isCheckBoxVisible = false;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.name.setText(list.get(position).getName());
        holder.secondName.setText(list.get(position).getSurname());
        //holder.imageView.setImageResource(list.get(position).getImage());

        if (isCheckBoxVisible){
            holder.checkbox.setVisibility(View.VISIBLE);
            holder.checkbox.setChecked(false);
        }
        else {
            holder.checkbox.setVisibility(View.GONE);
        }

        holder.itDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Model theRemovedItem = list.get(position);
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged (position, getItemCount ());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Model myModel = list.get(holder.getAdapterPosition());
                itemClickListener.onItemClick(holder.getAdapterPosition(), myModel);
            }
        });

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setIsChecked(isChecked);
            }
        });

    }

    public void setCheckBoxVisible(boolean checkBoxVisible) {
        isCheckBoxVisible = checkBoxVisible;
        notifyDataSetChanged();
    }

    public void deleteAll(){
        List<Model> indexList = new ArrayList<>();
        for(int i = 0; i<list.size();i++){
            if(list.get(i).getIsChecked()){
                indexList.add(list.get(i));
            }
        }
        list.removeAll(indexList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateItem(int position, Model model) {
        list.set(position, model);
        notifyItemChanged(position);
    }

    public void insertItem(Model model) {
        list.add(model);
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itDelete;
        //ImageView imageView;
        TextView name, secondName;
        CheckBox checkbox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.imageView);
            itDelete = itemView.findViewById(R.id.itemDelete);
            name = itemView.findViewById(R.id.first_name);
            secondName = itemView.findViewById(R.id.second_name);
            checkbox = itemView.findViewById(R.id.checkbox);
        }
    }



}
