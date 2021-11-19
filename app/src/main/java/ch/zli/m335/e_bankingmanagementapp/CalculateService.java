package ch.zli.m335.e_bankingmanagementapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalculateService extends Service {

    // variables for our coursename,
    // description, tracks and duration, id.
    private String userName;
    private String money;
    private int id;

    private final IBinder binder = new CalculateService.CalculateBinder();

    public class CalculateBinder extends Binder {
        CalculateService getService() {
            return CalculateService.this;
        }
    }

    public CalculateService() {
    }

    public IBinder onBind(Intent intent) {
        return binder;
    }

    // creating getter and setter methods
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public CalculateService(String userName, String money) {
        this.userName = userName;
        this.money = money;
    }
}
