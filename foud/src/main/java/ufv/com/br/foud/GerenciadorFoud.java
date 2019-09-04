package ufv.com.br.foud;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class GerenciadorFoud {
    public static void iniciar() {
        CriadorDiretorio.criarDiretorio();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        OneTimeWorkRequest mNucleo = new OneTimeWorkRequest.Builder(ControleNucleo.class)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance().enqueue( mNucleo );
    }

}
