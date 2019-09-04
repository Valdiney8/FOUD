package ufv.com.br.foud;

import android.app.Application;
import android.content.Context;

public class Foud extends Application {
    public static Class nucleo;
    public static Context context;

    public Foud(Class nucleo) {
        this.nucleo = nucleo;
        GerenciadorFoud.iniciar();
    }

}
