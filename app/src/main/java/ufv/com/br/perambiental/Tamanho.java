package ufv.com.br.perambiental;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import ufv.com.br.foud.EstrategiaOrdenacao;

public class Tamanho implements EstrategiaOrdenacao {

    @Override
    public void ordenarLista(List<File> listaDeArquivos) {
        listaDeArquivos.sort(new Comparator<File>(){
            public int compare(File f1, File f2){
                if(f1.length() > f2.length()){
                    return 1;
                }
                if(f1.length() < f2.length()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
    }
}
