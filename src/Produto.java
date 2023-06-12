public class Produto {
    private String nome;
    private String codigo;
    private Double valor;
    private int estoque =0;


    public Produto(String nome, String codigo, Double valor, int estoque) {
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
        this.estoque = estoque;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public  String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public  Double getValor() {
        return valor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String imprimir() {
        return "Codigo: " + codigo + " | Produto: " + nome+ " | Preço R$" + valor + " | Qtd: " + estoque;
    }


    public int getQtd() {
        return estoque;
    }
    public void setQtd(int qtd) {
        this.estoque = qtd;
    }

    public double getGanho(){
        return valor;
    }

    public double setGanho(double V){
        return this.valor = V;
    }

    @Override
    public String toString() {
        return "Produto >>" + " Nome: " + nome + " | Preço: R$" + valor;
    }
}
