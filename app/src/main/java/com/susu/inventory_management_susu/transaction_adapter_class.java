package com.susu.inventory_management_susu;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.susu.inventory_management_susu.R;

import java.util.ArrayList;

public class transaction_adapter_class extends RecyclerView.Adapter<transaction_adapter_class.transaction_view_holder> {

    private ArrayList<transaction_item> transactionItems;

    public static class transaction_view_holder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView header;
        private TextView description;
        private TextView type;

        public transaction_view_holder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            header = itemView.findViewById(R.id.header);
            description = itemView.findViewById(R.id.description);
            type = itemView.findViewById(R.id.type);
        }
    }

    public transaction_adapter_class(ArrayList<transaction_item> transactionItems){
        this.transactionItems = transactionItems;
    }

    @NonNull
    @Override
    public transaction_view_holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_item, viewGroup, false);
        transaction_view_holder tvh = new transaction_view_holder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull transaction_view_holder transaction_view_holder, int i) {
        transaction_item current_item = transactionItems.get(i);

        transaction_view_holder.image.setImageResource(current_item.getImage());
        transaction_view_holder.header.setText(current_item.getHeader());
        transaction_view_holder.description.setText(current_item.getDescription());
        transaction_view_holder.type.setText(current_item.getType());
    }

    @Override
    public int getItemCount() {
        return transactionItems.size();
    }
}
