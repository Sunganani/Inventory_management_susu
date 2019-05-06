package com.susu.inventory_management_susu.helpers;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.susu.inventory_management_susu.transaction_adapter_class;
import com.susu.inventory_management_susu.transaction_item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryData {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter transaction_adapter;
    private RecyclerView.LayoutManager transaction_manager;

    public void setData(RecyclerView recyclerView, Context context, ArrayList<transaction_item> transaction_items){

        this.recyclerView = recyclerView;
        this.recyclerView.setHasFixedSize(true);

        transaction_manager = new LinearLayoutManager(context);
        transaction_adapter = new transaction_adapter_class(transaction_items);

        this.recyclerView.setLayoutManager(transaction_manager);
        this.recyclerView.setAdapter(transaction_adapter);
    }

    public void barchat(BarChart chart){

        List<BarEntry> entries = new ArrayList<>();

        Random random = new Random();

        for (int x = 0; x < 14; x++){
            entries.add(new BarEntry(x, random.nextInt(10000)));
        }

        BarDataSet set = new BarDataSet(entries, "Goods sold");

        BarData data = new BarData(set);
        set.setColor(Color.rgb(221, 126, 26));
        data.setBarWidth(0.9f); // set custom bar width
        chart.setData(data);
        chart.setHighlightFullBarEnabled(true);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh
    }
}
