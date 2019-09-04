package ufv.com.br.foud;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public abstract class NucleoDeTransmissaoOportunistica {
    Context context = Foud.context;
    Estatistica estatistica = new Estatistica();

    final void transmitir() {
        //excluirDadosContextuais();

        boolean isAutorizado = obterAutorizacaoExecucao();
        while(isAutorizado){
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = (connectivityManager).getActiveNetworkInfo();

            RepositorioDeDados.aguardarArquivos();
            EstrategiaOrdenacao mOrdenador = obterEstrategiaOrdenacao();

            try{
                mOrdenador.ordenarLista(RepositorioDeDados.listaEnviando);
            }catch (Exception e){
                Log.e("NucleoTransmissaoOportunistica", "falha ao chamar a ordenação!", e);
            }

            Iterator<File> iter = RepositorioDeDados.listaEnviando.iterator();
            while (iter.hasNext()){
                verDadosContextuais();
                File arquivo = iter.next();
                if(arquivo.exists()) {
                    EstrategiaTransmissao mTransmissor = obterEstrategiaTransmissao();
                    estatistica.setInicioTransmissao(System.currentTimeMillis());
                    try{
                        if (mTransmissor.transmitir(arquivo)) {
                            estatistica.setFimTransmissao(System.currentTimeMillis());
                            estatistica.setSsid( networkInfo.getExtraInfo().replaceAll("\"", ""));
                            estatistica.setNomeArquivo(arquivo.getName());
                            estatistica.setTamanhoArquivo(arquivo.length());
                            estatistica.setTipoOrdenacao(mOrdenador.getClass().getName().substring(25,50));
                            estatistica.setTipoTransmissao(mTransmissor.getClass().getName());
                            armazenarDadosContextuais();
                            if( arquivo.renameTo(new File(RepositorioDeDados.diretorioEnviado, arquivo.getName()))) {
                                iter.remove();
                            }
                        }
                    }catch (Exception e){
                        Log.e("NucleoTransmissaoOportunistica", "falha ao chamar ao transmitir!", e);
                    }
                }
            }
        }
    }

    private void armazenarDadosContextuais(){
        EstatisticaDAO dao = new EstatisticaDAO(context);
        dao.adicionar(estatistica);
    }

    public void verDadosContextuais(){
        EstatisticaDAO estatisticaDAO = new EstatisticaDAO(context);
        List<Estatistica> lista = estatisticaDAO.listar();
        for(Estatistica estatistica : lista){
            System.out.println(estatistica.getId() +" "+estatistica.getNomeArquivo() +" "+estatistica.getTamanhoArquivo()
                    +" "+estatistica.getSsid()  +" "+estatistica.getInicioTransmissao() +" "+estatistica.getFimTransmissao()+" "+estatistica.getTipoTransmissao()  +" "+estatistica.getTipoOrdenacao());
        }
    }

    public void excluirDadosContextuais(){
        EstatisticaDAO estatisticaDAO = new EstatisticaDAO(context);
        List<Estatistica> lista = estatisticaDAO.listar();
        for(Estatistica estatistica : lista){
            estatisticaDAO.remover(estatistica);
        }
    }

    public abstract boolean obterAutorizacaoExecucao();
    protected abstract EstrategiaOrdenacao obterEstrategiaOrdenacao();
    public abstract EstrategiaTransmissao obterEstrategiaTransmissao();
}
