package ufv.com.br.perambiental;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InterfaceDeRede {
    Context context;

    public InterfaceDeRede(Context context) {
        this.context = context;
    }

    public String getIdentificacao() {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork.getExtraInfo().replaceAll("\"", "");
    }

}
