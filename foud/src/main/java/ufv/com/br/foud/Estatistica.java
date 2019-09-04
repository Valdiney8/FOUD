package ufv.com.br.foud;

import java.io.Serializable;

public class Estatistica implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nomeArquivo;
    private Long tamanhoArquivo;
    private String ssid;
    private long inicioTransmissao;
    private long fimTransmissao;
    private String tipoTransmissao;
    private String tipoOrdenacao;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Long getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(Long tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public long getInicioTransmissao() {
        return inicioTransmissao;
    }

    public void setInicioTransmissao(long inicioTransmissao) {
        this.inicioTransmissao = inicioTransmissao;
    }

    public long getFimTransmissao() {
        return fimTransmissao;
    }

    public void setFimTransmissao(long fimTransmissao) {
        this.fimTransmissao = fimTransmissao;
    }

    public String getTipoTransmissao() {
        return tipoTransmissao;
    }

    public void setTipoTransmissao(String tipoTransmissao) {
        this.tipoTransmissao = tipoTransmissao;
    }

    public String getTipoOrdenacao() {
        return tipoOrdenacao;
    }

    public void setTipoOrdenacao(String tipoOrdenacao) {
        this.tipoOrdenacao = tipoOrdenacao;
    }
}
