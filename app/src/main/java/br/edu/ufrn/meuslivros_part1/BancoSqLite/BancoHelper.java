package br.edu.ufrn.meuslivros_part1.BancoSqLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufrn.meuslivros_part1.classes.Livro;
import br.edu.ufrn.meuslivros_part1.classes.LivroContrato;

public class BancoHelper extends SQLiteOpenHelper {

    private static final String TAG = "sql";
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " INTEGER";
    private static final String DOUBLE_TYPE = " REAL";
    private static final String VIRGULA = ",";

    //Instrução Mysql para criação da tabela livro
    private static final String SQL_CREATE_TABLE =
            ("CREATE TABLE " + LivroContrato.LivroEntry.TABLE_NAME +
                    "("+
                    LivroContrato.LivroEntry._ID + " INTEGER PRIMARY KEY"+ VIRGULA+
                    LivroContrato.LivroEntry.TITULO + TEXT_TYPE + VIRGULA+
                    LivroContrato.LivroEntry.AUTOR + TEXT_TYPE + VIRGULA+
                    LivroContrato.LivroEntry.ANO + NUMBER_TYPE+ VIRGULA+
                    LivroContrato.LivroEntry.NOTA + DOUBLE_TYPE+
            ");");

    //instrução Mysql para deletar a tabela do banco
    private static final String SQL_DROP_TABLE = (
            "DROP TABLE " + LivroContrato.LivroEntry.TABLE_NAME
            );

    //Nome do Banco e Versão do Banco
    private static final String DATABASE_NAME = "MEUS_LIVROS";
    private static final int DATABASE_VERSION = 4;

    //constructor
    public BancoHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(SQL_DROP_TABLE);
            this.onCreate(db);
        }
    }

    //Salvar dados no banco
    public long save(Livro livro){

        long id = livro.getId();
        SQLiteDatabase db = getWritableDatabase();

        try{

            ContentValues values = new ContentValues();
            values.put(LivroContrato.LivroEntry.AUTOR, livro.getAutor());
            values.put(LivroContrato.LivroEntry.TITULO, livro.getTitulo());
            values.put(LivroContrato.LivroEntry.ANO, livro.getAno());
            values.put(LivroContrato.LivroEntry.NOTA, livro.getNota());

            if(id != 0){

                String selection = LivroContrato.LivroEntry._ID + "=?";
                String[] where_args = new String[]{String.valueOf(id)};

                int count = db.update(LivroContrato.LivroEntry.TABLE_NAME, values, selection, where_args);
                Log.i("ATUALIZOU", "id: " + id);
                return count;

            } else {

                id = db.insert(LivroContrato.LivroEntry.TABLE_NAME, null, values);
                Log.i("INSERIU", "id: " + id);
                return id;
            }

        } finally {
            db.close();
        }
    }

    //Lista todos os objetos de carros no banco
    public List<Livro> findAll(){
        SQLiteDatabase db = getReadableDatabase();
        try{

            Cursor c = db.query(LivroContrato.LivroEntry.TABLE_NAME, null, null, null, null, null, null, null);
            Log.i("LISTOU", "Lista Livros");
            return toList(c);

        } finally {
            db.close();
        }
    }

    //Recebe um Cursor e cria a lista de dados
    private List<Livro> toList(Cursor c){

        List<Livro> livros = new ArrayList<>();

        if(c.moveToFirst()){
            do{

                Livro livro = new Livro();
                livros.add(livro);

                livro.setId(c.getInt(c.getColumnIndex(LivroContrato.LivroEntry._ID)));
                livro.setAutor(c.getString(c.getColumnIndex(LivroContrato.LivroEntry.AUTOR)));
                livro.setTitulo(c.getString(c.getColumnIndex(LivroContrato.LivroEntry.TITULO)));
                livro.setNota(c.getDouble(c.getColumnIndex(LivroContrato.LivroEntry.NOTA)));
                livro.setAno(c.getInt(c.getColumnIndex(LivroContrato.LivroEntry.ANO)));

            } while(c.moveToNext());
        }

        return livros;
    }

    //Executa o  mysql
    public void execSQL(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql);
        } finally {
            db.close();
        }
    }

}
