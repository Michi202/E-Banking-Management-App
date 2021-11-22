package ch.zli.m335.e_bankingmanagementapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import ch.zli.m335.e_bankingmanagementapp.AccountService;

public class AccountActivity extends AppCompatActivity {

    TextView titlepage, subtitlepage, completeName, userName, bankone, expired;
    Button btnEdit;
    String SHARED_PREFS = "sharedPrefs";
    String SHARED_PREFS2 = "sharedPrefs2";
    String userCompleteName = "";
    String getUserCompleteName;
    String userUserName ="";
    String getUserUserName;
    AccountService accountService;
    boolean accountBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        completeName = findViewById(R.id.completeName);
        userName = findViewById(R.id.userName);
        bankone = findViewById(R.id.bankone);
        expired = findViewById(R.id.expired);
        btnEdit = findViewById(R.id.btnEdit);

        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(), "fonts/MRegular.ttf");

        titlepage.setTypeface(MRegular);
        subtitlepage.setTypeface(MLight);
        completeName.setTypeface(MRegular);
        userName.setTypeface(MRegular);
        bankone.setTypeface(MMedium);
        expired.setTypeface(MLight);
        btnEdit.setTypeface(MMedium);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AccountActivity.this, CreditCardActivity.class);
                startActivity(a);
            }
        });

        loadData();
        updateData();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        getUserCompleteName = sharedPreferences.getString(userCompleteName, "");

        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        getUserUserName = sharedPreferences2.getString(userUserName, "");
    }

    public void updateData() {
        completeName.setText(getUserCompleteName);
        userName.setText(getUserUserName);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, AccountService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,IBinder service) {
            AccountService.AccountBinder binder = (AccountService.AccountBinder) service;
            accountService = binder.getService();
            accountBound = true;
            if (accountBound) {

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            accountBound = false;
        }
    };

}
