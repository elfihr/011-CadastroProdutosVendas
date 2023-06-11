public class Vendas{
    private Produto pdt;
    private int quantidade;
    private String data;
    private double vendaTot;


    public Vendas(Produto pdt,int quantidade,String data) {
        this.pdt = pdt;
        this.quantidade = quantidade;
        this.data = data;
        this.vendaTot =getVendaTot();
    }

    public double getVendaTot() {
        return vendaTot;
    }

    public Produto getPdt() {
        return pdt;
    }

    public void setPdt(Produto pdt) {
        this.pdt = pdt;
    }

    public void setVendaTot(double vendaTot) {
        this.vendaTot = vendaTot;
    }




    @Override
    public String toString() {
        return "Vendas >>" + " Quantidade: " + quantidade + " | data: " + data + " | Venda Total: R$" + vendaTot;
    }
}
