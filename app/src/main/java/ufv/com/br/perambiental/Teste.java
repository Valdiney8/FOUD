package ufv.com.br.perambiental;

import android.content.Context;
import java.util.Arrays;
import ufv.com.br.foud.Foud;
import ufv.com.br.foud.EstrategiaOrdenacao;
import ufv.com.br.foud.EstrategiaTransmissao;
import ufv.com.br.foud.NucleoDeTransmissaoOportunistica;

public class Teste extends NucleoDeTransmissaoOportunistica {
    Context context = Foud.context;
    Bateria bateria = new Bateria(context);
    InterfaceDeRede interfaceDeRede = new InterfaceDeRede(context);

    @Override
    public boolean obterAutorizacaoExecucao() {
        if(bateria.getNivel() > 50)
            return true;
        return false;
    }

    @Override
    protected EstrategiaOrdenacao obterEstrategiaOrdenacao() {
        String[] extensoes = new String[]{"mp4"};
        if(bateria.getNivel() > 50 && bateria.getNivel() <= 60)
            return new Tamanho();
        if(bateria.getNivel() >= 90 && interfaceDeRede.
                getIdentificacao().equals("minhaRede"))
            return new Extensao(Arrays.asList(extensoes));
        return new DataDeCriacao();
    }

    @Override
    public EstrategiaTransmissao obterEstrategiaTransmissao() {
        return new Http();
    }
}
