package ch.zli.m335.e_bankingmanagementapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class AnalysisService extends Service {

    private final IBinder binder = new AnalysisBinder();

    public class AnalysisBinder extends Binder {
        AnalysisService getService() {
            return AnalysisService.this;
        }
    }

    public AnalysisService() {
    }

    public IBinder onBind(Intent intent) {
        return binder;
    }
}
