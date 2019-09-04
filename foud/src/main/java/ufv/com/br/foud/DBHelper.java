package ufv.com.br.foud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String NOME_DO_BANCO = "estatistica";

    private static final int VERSAO_DO_BANCO = 1;

    DBHelper(Context context) {
        super(context, NOME_DO_BANCO, null, VERSAO_DO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tbl_estatistica (" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT" +
                ",nome_arquivo TEXT NOT NULL" +
                ",tamanho_arquivo LONG NOT NULL" +
                ",ssid TEXT NOT NULL" +
                ",inicio_transmissao LONG NOT NULL" +
                ",fim_transmissao LONG NOT NULL" +
                ",tipo_transmissao TEXT NOT NULL" +
                ",tipo_ordenacao TEXT NOT NULL" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tbl_estatistica";
        db.execSQL(sql);
        onCreate(db);
    }
}
