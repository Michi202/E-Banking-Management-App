package ch.zli.m335.e_bankingmanagementapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MonthlyPlanService extends Service {

    private String monthlyName;
    private String monthlyDuration;
    private String monthlyTracks;
    private String monthlyDescription;
    private int id;

    private final IBinder binder = new MonthlyPlanService.MonthlyPlanBinder();

    public class MonthlyPlanBinder extends Binder {
        MonthlyPlanService getService() {
            return MonthlyPlanService.this;
        }
    }

    public MonthlyPlanService() {
    }

    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getMonthlyName() {
        return monthlyName;
    }

    public void setMonthlyName(String monthlyName) {
        this.monthlyName = monthlyName;
    }

    public String getMonthlyDuration() {
        return monthlyDuration;
    }

    public void setMonthlyDuration(String monthlyDuration) {
        this.monthlyDuration = monthlyDuration;
    }

    public String getMonthlyTracks() {
        return monthlyTracks;
    }

    public void setMonthlyTracks(String monthlyTracks) {
        this.monthlyTracks = monthlyTracks;
    }

    public String getMonthlyDescription() {
        return monthlyDescription;
    }

    public void setMonthlyDescription(String monthlyDescription) {
        this.monthlyDescription = monthlyDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MonthlyPlanService(String monthlyName, String monthlyDuration, String monthlyTracks, String monthlyDescription) {
        this.monthlyName = monthlyName;
        this.monthlyDuration = monthlyDuration;
        this.monthlyTracks = monthlyTracks;
        this.monthlyDescription = monthlyDescription;
    }
}
