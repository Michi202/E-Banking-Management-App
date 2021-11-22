package ch.zli.m335.e_bankingmanagementapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AnalysisActivity extends AppCompatActivity {

    ch.zli.m335.e_bankingmanagementapp.AnalysisService analysisService;
    boolean analysisBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_activity);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ch.zli.m335.e_bankingmanagementapp.AnalysisService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            ch.zli.m335.e_bankingmanagementapp.AnalysisService.AnalysisBinder binder = (ch.zli.m335.e_bankingmanagementapp.AnalysisService.AnalysisBinder) service;
            analysisService = binder.getService();
            analysisBound = true;
            if (analysisBound) {

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            analysisBound = false;
        }
    };

}
