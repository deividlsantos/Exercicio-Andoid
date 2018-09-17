package industrialararaquara.com.br.produtos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import industrialararaquara.com.br.produtos.Dao.ProdutoDAO;
import industrialararaquara.com.br.produtos.Modelo.Produtos;

public class ListaProdutosActivity extends AppCompatActivity {
    private ListView listaProdutos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        listaProdutos = findViewById(R.id.lista_produtos);
        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produtos produtos = (Produtos)listaProdutos.getItemAtPosition(position);

                Intent vaiparaTelaFormulario = new Intent(ListaProdutosActivity.this,FormularioActivity.class);

                vaiparaTelaFormulario.putExtra("produto", produtos);

                startActivity(vaiparaTelaFormulario);
                }
            });
            registerForContextMenu(listaProdutos);

            Button btnNovoProduto = findViewById(R.id.btn_novo_produto);
            btnNovoProduto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ListaProdutosActivity.this, "Funcionou", Toast.LENGTH_SHORT).show();

                    Intent vaiparaTelaFormulario = new Intent(ListaProdutosActivity.this, FormularioActivity.class);

                    startActivity(vaiparaTelaFormulario);
                }
            });


        }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

                Produtos produtos = (Produtos) listaProdutos.getItemAtPosition(info.position);

                ProdutoDAO dao = new ProdutoDAO(ListaProdutosActivity.this);
                dao.deletar(produtos);
                dao.close();

                return false;
            }
        });
    }

    private void carregaLista(){
        ProdutoDAO dao = new ProdutoDAO(this);
        List<Produtos> produtos = dao.recuperaProdutos();
        dao.close();

        ArrayAdapter<Produtos> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, produtos);

        listaProdutos.setAdapter(adapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        carregaLista();
    }
}
