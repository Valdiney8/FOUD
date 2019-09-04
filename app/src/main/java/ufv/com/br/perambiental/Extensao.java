package ufv.com.br.perambiental;

import java.io.File;
import java.util.Comparator;
import java.util.List;

import ufv.com.br.foud.EstrategiaOrdenacao;

public class Extensao implements EstrategiaOrdenacao {
    private List<String> extensoesPrioritarias;
    public Extensao(List<String> extensoesPrioritarias) {
        this.extensoesPrioritarias = extensoesPrioritarias;
    }

    @Override
    public void ordenarLista(List<File> listaDeArquivos) {
        listaDeArquivos.sort(new Comparator<File>(){
            public int compare(File f1, File f2){
                String ext1 = getExtension(f1);
                String ext2 = getExtension(f2);
                if(retornaPosicao(extensoesPrioritarias, ext1) >
                        retornaPosicao(extensoesPrioritarias, ext2)){
                    return 1;
                }
                if(retornaPosicao(extensoesPrioritarias, ext1) <
                        retornaPosicao(extensoesPrioritarias, ext2)){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
    }

    private String getExtension(File arquivo) {
        if (arquivo instanceof File) {
            if (arquivo.getName().length() > 1 && arquivo.getName().contains(".")) {
                return arquivo.getName().substring(arquivo.getName().lastIndexOf("."));
            }
        }
        return null;
    }

    private int retornaPosicao(List<String> listaDeExtensoes, String extensao){
        int indice = listaDeExtensoes.size();
        for(String ext : listaDeExtensoes){
            if(ext.equals(extensao)){
                indice = listaDeExtensoes.indexOf(extensao);
            }
        }
        return indice;
    }

    public void setExtensoesPrioritarias(List<String> extensoesPrioritarias) {
        this.extensoesPrioritarias = extensoesPrioritarias;
    }
}
