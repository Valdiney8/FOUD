package ufv.com.br.foud;

import android.support.annotation.NonNull;
import android.content.Context;
import java.lang.reflect.Method;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

class ControleNucleo extends Worker {
    public ControleNucleo(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Foud.context = getApplicationContext();
        String caminho = Foud.nucleo.getName();

        while (true) {
            Class cl = null;
            try {
                cl = Class.forName(caminho);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Method method = null;
            try {
                method = cl.getMethod("transmitir", null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {
                method.invoke(cl.newInstance(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
