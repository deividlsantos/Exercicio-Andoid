package industrialararaquara.com.br.produtos.Modelo;

import java.io.Serializable;

public class Produtos implements Serializable{
    private Long id;
    private String nome;
    private String marca;
    private String valor;

    public Long getId(){ return id; }

    public void setId(Long id){ this.id = id; }

    public String getNome(){ return nome; }

    public void setNome(String nome){ this.nome = nome; }

    public String getMarca() { return marca; }

    public void setMarca(String marca) { this.marca = marca; }

    public String getValor() { return valor; }

    public void setValor(String valor) { this.valor = valor;  }

    public String toString(){ return id + " - " + nome; }
}
