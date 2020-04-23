package com.example.mailapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mailapplication.MailItemClickListener;
import com.example.mailapplication.R;
import com.example.mailapplication.adapter.model.ItemModel;

import java.util.ArrayList;
import java.util.List;


public class MailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<ItemModel> mailModelList;
    MailItemClickListener listener;

    public MailAdapter(List<ItemModel> mailModelList) {
        this.mailModelList = mailModelList;
    }

    public MailAdapter(List<ItemModel> mailModelList, MailItemClickListener listener) {
        this.mailModelList = mailModelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gmail, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemModel mailModel = mailModelList.get(position);
        ItemViewHolder viewHolder = (ItemViewHolder)holder;

        viewHolder.txtBackground.setText(mailModel.getName().substring(0, 1));
        viewHolder.tag.setText(mailModel.getTag());
        viewHolder.name.setText(mailModel.getName());
        viewHolder.header.setText(mailModel.getHeader());
        viewHolder.content.setText(mailModel.getContent());

        if(mailModel.isFavorite()) viewHolder.imageFavorite.setImageResource(R.drawable.star_favorite);
        else viewHolder.imageFavorite.setImageResource(R.drawable.star_normal);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mailModelList.size();
    }

    public void filterList(ArrayList<ItemModel> filterList){
        mailModelList = filterList;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView txtBackground;
        TextView name;
        TextView header;
        TextView content;
        TextView tag;
        ImageView imageFavorite;
        ImageView imageAvatar;
        LinearLayout element;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.content);
            header = itemView.findViewById(R.id.header);
            name = itemView.findViewById(R.id.txtName);
            txtBackground = itemView.findViewById(R.id.txtBackground);
            tag = itemView.findViewById(R.id.tag);
            imageFavorite = itemView.findViewById(R.id.image_favorite);
            imageAvatar = itemView.findViewById(R.id.imageAvatar);
            element = itemView.findViewById(R.id.element);

            imageFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isFavorite = mailModelList.get(getAdapterPosition()).isFavorite();
                    mailModelList.get(getAdapterPosition()).setFavorite(!isFavorite);
                    notifyDataSetChanged();
                }
            });

            txtBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isSelected = mailModelList.get(getAdapterPosition()).isChosen();
                    mailModelList.get(getAdapterPosition()).setChosen(!isSelected);
                    imageAvatar.setVisibility(View.VISIBLE);
                    txtBackground.setVisibility(View.INVISIBLE);
                    name.setBackgroundColor(0x0000BBBB);
                    notifyDataSetChanged();
                }
            });

            imageAvatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isSelected = mailModelList.get(getAdapterPosition()).isChosen();
                    mailModelList.get(getAdapterPosition()).setChosen(!isSelected);
                    imageAvatar.setVisibility(View.INVISIBLE);
                    txtBackground.setVisibility(View.VISIBLE);
                    name.setBackgroundColor(0x0000FFFF);
                    notifyDataSetChanged();
                }
            });

        }
    }
}
