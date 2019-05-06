package com.susu.inventory_management_susu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.beehive.inventory_management.helpers.support_methods;
import com.github.mikephil.charting.charts.BarChart;
import com.susu.inventory_management_susu.R;
import com.susu.inventory_management_susu.helpers.InventoryData;
import com.susu.inventory_management_susu.helpers.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.Random;

public class Stock extends AppCompatActivity {

    private RecyclerView recent_transaction;
    private InventoryData inventoryData = new InventoryData();
    BarChart barChart;
    private support_methods support_methods = new support_methods();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        overridePendingTransition(R.anim.no_animation, R.anim.slide_down);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setListView();

//        initialising barchart
        barChart = findViewById(R.id.barchart);
        inventoryData.barchat(barChart);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void setListView(){
        final ArrayList<transaction_item> transaction_items = new ArrayList<>();

        String[] items = {"Soap","Kazinga","sugar","salt","eggs","Milk","Soap","Kazinga","sugar","salt","eggs","Milk","Soap","Kazinga","sugar","salt","eggs","Milk"};

        Random random = new Random();

        for( int x = 0; x < items.length; x++) {
            transaction_items.add(new transaction_item(R.drawable.cart, "Name: "+items[x], "Barcode: "+random.nextInt(10000)+"", "Type: "+items[x]));
        }

        recent_transaction = findViewById(R.id.stock_view);
        InventoryData inventoryData = new InventoryData();
        inventoryData.setData(recent_transaction,this.getApplicationContext(), transaction_items);

        recent_transaction.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recent_transaction ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        //TODO LISTVIEW LISTENER STOCK
                        Object[] data = {
                                transaction_items.get(position).getImage(),
                                transaction_items.get(position).getHeader(),
                                transaction_items.get(position).getDescription(),
                                transaction_items.get(position).getType(),
                        };

                        Intent intent = new Intent(Stock.this, edit_item.class);
                        startNewActivity(intent, data);
                        Toast.makeText(getApplicationContext(), transaction_items.get(position).getHeader(), Toast.LENGTH_LONG).show();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(100);

                        Toast.makeText(getApplicationContext(), "Hellow! susu", Toast.LENGTH_LONG).show();
                    }
                })
        );


    }
    public void startNewActivity(Intent intent, Object[] data){
        intent.putExtra("item_data", data );
        startActivity(intent);
    }
}
