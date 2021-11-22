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

public class CreditCardActivity extends AppCompatActivity {

    TextView titlepage, subtitlepage, completeName, userName;
    Button btnCancel, btnSave;

    String SHARED_PREFS = "sharedPrefs";
    String SHARED_PREFS2 = "sharedPrefs2";
    String userCompleteName = "";
    String getUserCompleteName;
    String userUserName = "";
    String getUserUserName;
    ch.zli.m335.e_bankingmanagementapp.CreditCardService creditCardService;
    boolean creditCardBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_card_activity);

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitlepage);
        completeName = findViewById(R.id.completeName);
        userName = findViewById(R.id.userName);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);

        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(), "fonts/MRegular.ttf");

        titlepage.setTypeface(MRegular);
        subtitlepage.setTypeface(MLight);
        completeName.setTypeface(MRegular);
        userName.setTypeface(MRegular);

        btnSave.setTypeface(MMedium);
        btnCancel.setTypeface(MLight);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(userCompleteName, completeName.getText().toString());
                editor.apply();

                SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                editor2.putString(userUserName, userName.getText().toString());
                editor2.apply();

                Intent a = new Intent(CreditCardActivity.this, ch.zli.m335.e_bankingmanagementapp.AccountActivity.class);
                startActivity(a);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ch.zli.m335.e_bankingmanagementapp.CreditCardService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            ch.zli.m335.e_bankingmanagementapp.CreditCardService.CreditCardBinder binder = (ch.zli.m335.e_bankingmanagementapp.CreditCardService.CreditCardBinder) service;
            creditCardService = binder.getService();
            creditCardBound = true;
            if (creditCardBound) {

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            creditCardBound = false;
        }
    };

}
