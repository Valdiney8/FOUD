package ufv.com.br.foud;

import android.os.Environment;

import java.io.File;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

class RepositorioDeDados {
    private static File diretorioOnFoud = new File(Environment.getExternalStorageDirectory() + "/FOUD");
    private static File diretorioEnviar = new File(diretorioOnFoud + "/" + "A Enviar");
    private static File diretorioEnviando = new File(diretorioOnFoud +  "/" + "Enviando");
    static File diretorioEnviado = new File(diretorioOnFoud + "/" + "Enviados");

    static CopyOnWriteArrayList<File> listaEnviar = new CopyOnWriteArrayList<>();
    static CopyOnWriteArrayList<File> listaEnviando = new CopyOnWriteArrayList<>();

    private static void carregarRepositorio(CopyOnWriteArrayList<File> listaDeArquivos, File diretorio) {

        File[] arquivosOuSubdiretorios = diretorio.listFiles();
        if (arquivosOuSubdiretorios != null) {
            for (File arquivo : arquivosOuSubdiretorios) {
                if (arquivo.isDirectory()) {
                    carregarRepositorio(listaDeArquivos, arquivo);
                } else {
                    listaDeArquivos.add(arquivo);
                }
            }
        }
    }

    static void aguardarArquivos(){
        carregarRepositorio(listaEnviar, diretorioEnviar);
        carregarRepositorio(listaEnviando, diretorioEnviando);
        while (listaEnviar.isEmpty() & listaEnviando.isEmpty()) {
            try {
                Thread.sleep(1000 * 2);// 2 segundos
                System.out.println("Listas vazias");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            carregarRepositorio(listaEnviar, diretorioEnviar);
            carregarRepositorio(listaEnviando, diretorioEnviando);
        }

        Iterator<File> iter = listaEnviar.iterator();
        while (iter.hasNext()){
            File arquivo = iter.next();
            if(arquivo.renameTo(new File(diretorioEnviando, arquivo.getName())))
                iter.remove();
        }
        carregarRepositorio(listaEnviando, diretorioEnviando);

    }
}
