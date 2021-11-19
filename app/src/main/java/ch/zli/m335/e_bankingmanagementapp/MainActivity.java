package ch.zli.m335.e_bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> money;
    CalculateService calculateService;
    boolean calculateBound = false;
    private DBHandler dbHandler;
    private Button monthly;
    private Button analysis;
    private Button cards;
    private Button payments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthly = (Button) findViewById(R.id.monthly);
        monthly.setOnClickListener(clickListener);

        analysis = (Button) findViewById(R.id.analysis);
        analysis.setOnClickListener(clickListener);

        cards = (Button) findViewById(R.id.cards);
        cards.setOnClickListener(clickListener);

        payments = (Button) findViewById(R.id.payments);
        payments.setOnClickListener(clickListener);

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.monthly:
                    Intent i = new Intent(MainActivity.this, MonthlyPlanActivity.class);
                    startActivity(i);
                    break;
                case R.id.analysis:
                    Intent i2 = new Intent(MainActivity.this, AnalysisActivity.class);
                    startActivity(i2);
                    break;
                case R.id.cards:
                    Intent i3 = new Intent(MainActivity.this, CreditCardActivity.class);
                    startActivity(i3);
                    break;
                case R.id.payments:
                    Intent i4 = new Intent(MainActivity.this, AccountActivity.class);
                    startActivity(i4);
                    break;
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, CalculateService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            CalculateService.CalculateBinder binder = (CalculateService.CalculateBinder) service;
            calculateService = binder.getService();
            calculateBound = true;
            if (calculateBound) {

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            calculateBound = false;
        }
    };
}