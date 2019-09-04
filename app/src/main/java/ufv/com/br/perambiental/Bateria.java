package ufv.com.br.perambiental;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

class Bateria {
    Context context;
    Bateria(Context context) {
        this.context = context;
    }

    public int getNivel() {
        IntentFilter intentFilter = new
                IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context
                .registerReceiver(null, intentFilter);
        return batteryStatus
                .getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
    }
}
