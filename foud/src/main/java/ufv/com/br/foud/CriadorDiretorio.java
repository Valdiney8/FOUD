package ufv.com.br.foud;

import android.os.Environment;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CriadorDiretorio {


    public static void criarDiretorio() {
        List<String> subdiretorios = Arrays.asList(new String[] {"A Enviar", "Enviando", "Enviados"});

        //Criando diretorio padrao do OnPostUnit
        File diretorio = new File(Environment.getExternalStorageDirectory() + "/OnPostUnit");
        //testar         File diretorio = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"OnPostUnit");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        //Criando subdiretorios do OnPostUnit
        for(String subdir : subdiretorios){
            File pasta = new File(diretorio + "/" + subdir);
            if (!pasta.exists()) {
                pasta.mkdir();
            }
        }
    }
}
