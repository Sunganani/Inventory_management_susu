package com.susu.inventory_management_susu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.susu.inventory_management_susu.helpers.InventoryData;
import com.susu.inventory_management_susu.helpers.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import static android.support.constraint.Constraints.TAG;


public class Transaction extends Fragment {

    private RecyclerView recent_transaction;

    public static Transaction newInstance() {
        return new Transaction();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;

        view = inflater.inflate(R.layout.fragment_transaction, container, false);
        setListView(view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }



    public void setListView(View view){
        final ArrayList<transaction_item> transaction_items = new ArrayList<>();

        String[] items = {"Soap","Kazinga","sugar","salt","eggs","Milk","Soap","Kazinga","sugar","salt","eggs","Milk","Soap","Kazinga","sugar","salt","eggs","Milk"};

        Random random = new Random();

        for( int x = 0; x < items.length; x++) {
            transaction_items.add(new transaction_item(R.drawable.cart, "Name: "+items[x], "Barcode: "+random.nextInt(10000)+"", "Type: "+items[x]));
        }


        recent_transaction = view.findViewById(R.id.recent_transaction);
        InventoryData inventoryData = new InventoryData();
        inventoryData.setData(recent_transaction,getContext(), transaction_items);


        recent_transaction.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recent_transaction ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        //TODO LISTVIEW ACTION LISTEN TRANSACTION
                        Object[] data = {
                                transaction_items.get(position).getImage(),
                                transaction_items.get(position).getHeader(),
                                transaction_items.get(position).getDescription(),
                                transaction_items.get(position).getType(),
                        };

                        Intent intent = new Intent(getActivity(), edit_item.class);
                        startNewActivity(intent, data);
                        Toast.makeText(getContext(), transaction_items.get(position).getHeader(), Toast.LENGTH_LONG).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        Vibrator vibrator = (Vibrator) getActivity().getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(100);
                        Toast.makeText(getContext(), "Hellow! sungi", Toast.LENGTH_LONG).show();
                    }
                })
        );
    }

    public void startNewActivity(Intent intent, Object[] data){
        intent.putExtra("item_data", data );
        startActivity(intent);
    }



}
