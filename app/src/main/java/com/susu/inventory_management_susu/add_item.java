package com.susu.inventory_management_susu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import static android.support.constraint.Constraints.TAG;


public class add_item extends Fragment {
    private CheckBox check_show;
    private FloatingActionButton scan_btn;
    private Spinner select_item_type;


    public static add_item newInstance(View view) {
        return new add_item();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        setControls(view);
        return view;

    }

    private void setControls(final View view) {
        check_show = view.findViewById(R.id.check_show_scanner);


        select_item_type = view.findViewById(R.id.select_item);

//        select_item_type
        check_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                  MainActivity main = (MainActivity) getActivity();

//                  scan_btn = main.getScan_btn();
                  scan_btn = view.findViewById(R.id.scan_btn);

                  //TODO  you must come back to this
                  if (isChecked){
//                      scan_btn.show();
//                      Snackbar.make(buttonView,"Its Checked", Snackbar.LENGTH_LONG);
                      Log.i("Sal","Checked");
                  }else{
//                      Snackbar.make(buttonView,"Its unChecked", Snackbar.LENGTH_LONG);
                      Log.i("Sal", "Unchecked");
//                      scan_btn.hi//de();
                  }
              }

          }
        );
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
