package ch.zli.m335.e_bankingmanagementapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CreditCardService extends Service {

    private final IBinder binder = new CreditCardBinder();

    public class CreditCardBinder extends Binder {
        CreditCardService getService() {
            return CreditCardService.this;
        }
    }

    public CreditCardService() {
    }

    public IBinder onBind(Intent intent) {
        return binder;
    }
}
