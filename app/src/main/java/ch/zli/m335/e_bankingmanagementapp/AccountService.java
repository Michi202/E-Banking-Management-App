package ch.zli.m335.e_bankingmanagementapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class AccountService extends Service {

    private final IBinder binder = new AccountBinder();

    public class AccountBinder extends Binder {
        AccountService getService() {
            return AccountService.this;
        }
    }

    public AccountService() {
    }

    public IBinder onBind(Intent intent) {
        return binder;
    }
}
