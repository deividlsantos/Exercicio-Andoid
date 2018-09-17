package industrialararaquara.com.br.produtos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import industrialararaquara.com.br.produtos.Dao.ProdutoDAO;
import industrialararaquara.com.br.produtos.Modelo.Produtos;
import industrialararaquara.com.br.produtos.helper.FormularioHelper;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Produtos produtos = (Produtos) intent.getSerializableExtra("produtos");

        if (produtos != null){
            helper.preencherFormulario(produtos);
        }
        /*Button btnSalvar = findViewById(R.id.btn_salvar);
        *
        * btnSalvar.setOnClickListener(new View.OnClickListener(){
        *     @Override
        *     public void onClick(View view){
        *       Toast.makeText(FormularioActivity.this, "Funcionou", Toast.LENGTH_SHORT).show();
        *       finish();
        *       }
        * }*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:


                Produtos produtos = helper.recuperarProduto();
                ProdutoDAO dao = new ProdutoDAO(this);
                dao.salvar(produtos);
                dao.close();

                Toast.makeText(this, "Produto" + produtos.getNome() + "salvo com sucesso", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
