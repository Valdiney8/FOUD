package ufv.com.br.foud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EstatisticaDAO {
    private String nomeArquivo;
    private Long tamanhoArquivo;
    private String ssid;
    private Long tempoTransmissao;
    private String tipoTransmissao;
    private String tipoOrdenacao;


    private DBHelper dbHelper;
    private Context context;

    public EstatisticaDAO(Context context) {
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public void adicionar(Estatistica estatistica) {

        // Passando os valores a serem persistidos
        ContentValues values = new ContentValues();
        values.put("nome_arquivo", estatistica.getNomeArquivo());
        values.put("tamanho_arquivo", estatistica.getTamanhoArquivo());
        values.put("ssid", estatistica.getSsid());
        values.put("inicio_transmissao", estatistica.getInicioTransmissao());
        values.put("fim_transmissao", estatistica.getFimTransmissao());
        values.put("tipo_transmissao", estatistica.getTipoTransmissao());
        values.put("tipo_ordenacao", estatistica.getTipoOrdenacao());

        // Instanciando uma conexão
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Inserindo registro
        long id = db.insert("tbl_estatistica", null, values);
        estatistica.setId(id);

        // Encerra a conexão com o banco de dados
        db.close();
    }




    public List<Estatistica> listar(){
        //Criando uma lista para compor os objetos do BD
        List<Estatistica> listaDeEstatisticas = new ArrayList<>();

        //Instanciando nova conexão com BD
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Realizando a consulta
        Cursor cursor = db.query("tbl_estatistica", null, null, null, null,
                null, "_id");
        try{
            while (cursor.moveToNext()){
                Estatistica estatistica = new Estatistica();
                estatistica.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                estatistica.setNomeArquivo(cursor.getString(cursor.getColumnIndex("nome_arquivo")));
                estatistica.setTamanhoArquivo(cursor.getLong(cursor.getColumnIndex("tamanho_arquivo")));
                estatistica.setSsid(cursor.getString(cursor.getColumnIndex("ssid")));
                estatistica.setInicioTransmissao(cursor.getLong(cursor.getColumnIndex("inicio_transmissao")));
                estatistica.setFimTransmissao(cursor.getLong(cursor.getColumnIndex("fim_transmissao")));
                estatistica.setTipoTransmissao(cursor.getString(cursor.getColumnIndex("tipo_transmissao")));
                estatistica.setTipoOrdenacao(cursor.getString(cursor.getColumnIndex("tipo_ordenacao")));
                listaDeEstatisticas.add(estatistica);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            //Encerrando o cursor
            cursor.close();
        }

        //Encerrando conexão com BD
        db.close();

        //Retornando a lista com os objetos oriundo da consulta
        System.out.println("Tamanho lista: "+listaDeEstatisticas.size());
        return listaDeEstatisticas;
    }



    public void remover(Estatistica estatistica){
        // Instancia uma conexão com o BD em modo de gravação
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Remove item do BD
        db.delete("tbl_estatistica", "_id=?", new String[] { estatistica.getId().toString() });


        // Encerra a conexão com BD
        db.close();
    }



    public float calcularCapacidadeSSID(String ssid){

        float tamanhoTotal = 0;
        float tempoTotal = 0;

        //Instanciando nova conexão com BD
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Realizando a consulta
        Cursor cursor = db.query("tbl_estatistica", null, null, null, null,
                null, "_id");

        cursor.moveToFirst();
        try{
            while (cursor != null){
                tamanhoTotal = tamanhoTotal + cursor.getLong(cursor.getColumnIndex("tamanho_arquivo"));
                tempoTotal = tempoTotal + cursor.getLong(cursor.getColumnIndex("fim_transmissao")) -
                             cursor.getLong(cursor.getColumnIndex("inicio_transmissao"));
                cursor.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            //Encerrando o cursor
            cursor.close();
        }

        //Encerrando conexão com BD
        db.close();

        return tamanhoTotal/tempoTotal;
    }
}
