package industrialararaquara.com.br.produtos.helper;

import android.widget.EditText;

import industrialararaquara.com.br.produtos.FormularioActivity;
import industrialararaquara.com.br.produtos.Modelo.Produtos;
import industrialararaquara.com.br.produtos.R;

public class FormularioHelper {
    private final EditText etNome;
    private final EditText etMarca;
    private final EditText etValor;
    private Produtos produtos;

    public FormularioHelper(FormularioActivity activity){
        etNome = activity.findViewById(R.id.et_nome);
        etMarca = activity.findViewById(R.id.et_marca);
        etValor = activity.findViewById(R.id.et_valor);
        produtos = new Produtos();
    }
    public Produtos recuperarProduto(){
        String nome = etNome.getText().toString();
        String marca = etMarca.getText().toString();
        String valor = etValor.getText().toString();

        produtos.setNome(nome);
        produtos.setMarca(marca);
        produtos.setValor(valor);

        return produtos;

    }
    public void preencherFormulario(Produtos produtos){
        etNome.setText(produtos.getNome());
        etMarca.setText(produtos.getMarca());
        etValor.setText(produtos.getValor());

        this.produtos = produtos;
    }
}
