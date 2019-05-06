package com.susu.inventory_management_susu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton scan_btn;
    private Fragment currentFragment;
    private Boolean show_scan_btn = true;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_transaction:
                    show_scan_btn = true;
                    setScan_btn();
                    currentFragment = Transaction.newInstance();
                    switchFragments(currentFragment);
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_show_item:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    show_scan_btn = false;
//                    setScan_btn();
//                    currentFragment = show_all_inventories.newInstance();
//                    switchFragments(currentFragment);
                    Intent intent = new Intent(MainActivity.this, Stock.class);
                    startNewActivity(intent);
                    return true;
                case R.id.navigation_add_item:
//                    mTextMessage.setText(R.string.title_notifications);
                    show_scan_btn = false;
                    setScan_btn();
                    currentFragment = add_item.newInstance(scan_btn);

                    switchFragments(currentFragment);
                    return true;
            }
            return false;
        }
    };

    public void setScan_btn(){
        if (!show_scan_btn){
            scan_btn.hide();
            return;
        }
       scan_btn.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            return;
        }

        currentFragment = Transaction.newInstance();
        switchFragments(currentFragment);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        scan_btn = findViewById(R.id.scan_btn);

        final Activity activity = this;

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setPrompt("SCAN BARCODE");
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult Result = IntentIntegrator.parseActivityResult(requestCode , resultCode ,data);
        if(Result != null){
            if(Result.getContents() == null){
                Toast.makeText(this, "cancelled", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this,"Scanned -> " + Result.getContents(), Toast.LENGTH_SHORT).show();

                Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
//                textView.setText(Result.getContents());
//                Snackbar.make( "",Snackbar.LENGTH_LONG)
                vibrator.vibrate(500);
            }
        }
        else {
            super.onActivityResult(requestCode , resultCode , data);
        }
    }


    private void startNewActivity(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.slide_up,  R.anim.no_animation);
    }

private void switchFragments(Fragment newFragment){
    FragmentManager fragmentManager = getSupportFragmentManager();
//    fragmentManager.put
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
//    newFragment = Fragment.newInstance();

    transaction.replace(R.id.fragment_container, newFragment);
    transaction.addToBackStack(null);
    transaction.commit();

}

    public FloatingActionButton getScan_btn() {
        return scan_btn;
    }

}
