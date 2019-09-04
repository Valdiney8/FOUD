package ufv.com.br.perambiental;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.List;

import ufv.com.br.foud.EstrategiaOrdenacao;

public class DataDeCriacao implements EstrategiaOrdenacao {

    @Override
    public void ordenarLista(List<File> listaDeArquivos) {
        listaDeArquivos.sort(new Comparator<File>(){
            public int compare(File f1, File f2){
                //testar se f1 e f2 existem, se sao arquivos (if f1.isFile)
                BasicFileAttributes atrF1 = null;
                try {
                    atrF1 = Files.readAttributes(Paths.get(f1.getPath()), BasicFileAttributes.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BasicFileAttributes atrF2 = null;
                try {
                    atrF2 = Files.readAttributes(Paths.get(f2.getPath()), BasicFileAttributes.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(atrF1.creationTime().toMillis() > atrF2.creationTime().toMillis()){
                    return 1;
                }
                if(atrF1.creationTime().toMillis() < atrF2.creationTime().toMillis()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
    }
}
