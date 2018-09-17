package industrialararaquara.com.br.produtos.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

import industrialararaquara.com.br.produtos.Modelo.Produtos;

public class ProdutoDAO extends SQLiteOpenHelper{

    public ProdutoDAO(Context context) {
        super(context, "Cadastro", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE PRODUTOS(id INTEGER PRIMARY KEY , nome TEXT NOT NULL, marca TEXT NOT NULL, valor TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int vesaoAntiga, int versaoNova){

    }

    public void insere(Produtos produtos) {
        /*String sql = "INSERT INTO Produtos(nome, marca, valor) VALUES(" + produtos.getNome() + ",)";*/

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getDadosProdutos(produtos);

        db.insert("Produtos", null, values);
    }

    @NonNull
    private ContentValues getDadosProdutos(Produtos produtos) {
        ContentValues values = new ContentValues();
        values.put("nome", produtos.getNome());
        values.put("marca", produtos.getMarca());
        values.put("valor", produtos.getValor());
        return values;
    }

    public void alterar(Produtos produtos) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getDadosProdutos(produtos);
        String[] params = {produtos.getId().toString()};
        db.update("Produtos", values, "id=?", params);
    }

    public List<Produtos> recuperaProdutos() {

        String sql = "SELECT * FROM Produtos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Produtos> produtos = new ArrayList<>();

        while (c.moveToNext()) {
            Produtos produto = new Produtos();
            produto.setId(c.getLong(c.getColumnIndex("id")));
            produto.setNome(c.getString(c.getColumnIndex("nome")));
            produto.setMarca(c.getString(c.getColumnIndex("marca")));
            produto.setValor(c.getString(c.getColumnIndex("valor")));

            produtos.add(produto);
        }
        c.close();
        return produtos;
    }

    public void deletar(Produtos produtos) {
        SQLiteDatabase db = getWritableDatabase();

        String[] parametros = {String.valueOf(produtos.getId())};

        db.delete("Produtos", "id=?", parametros);
    }


        public void salvar(Produtos produtos){
            if (produtos.getId() != null) {
                alterar(produtos);
            } else {
                insere(produtos);
            }
        }
    }

