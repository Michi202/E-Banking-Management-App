package ch.zli.m335.e_bankingmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CalculateService calculateService;
    boolean calculateBound = false;
    private DBHandler dbHandler;
    private Button monthly, payments, cards, analysis, transaction;

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

        transaction = (Button) findViewById(R.id.transaction);
        transaction.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        PendingIntent pIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                        Notification noti = new Notification.Builder(MainActivity.this)
                            .setTicker("User Transaction")
                            .setContentTitle("Transaction")
                            .setContentText("Transactions unable, because of maintainance issues")
                                .setSmallIcon(R.drawable.logo)
                                .setContentIntent(pIntent).getNotification();

                        noti.flags = Notification.FLAG_AUTO_CANCEL;
                        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        nm.notify(0, noti);

                        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            vibe.vibrate(100);
                    }
                }
        );

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