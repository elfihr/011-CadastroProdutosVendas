import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;



public class Metodo {
    private static List<Produto> listaProduto = new ArrayList<>();
    private static List<Vendas> listaVendas = new ArrayList<>();

    public static Scanner leia = new Scanner(System.in);
    public static Scanner leiaString = new Scanner(System.in);


    public static void printSeparador(int n){
        for(int i = 0;i<n;i++)
            System.out.print("-");
        System.out.println();
    }

    public static void pressAnythingToContinue(){
        System.out.println("\nPresione qualquer tecla para continuar");
        leiaString.nextLine();
    }

    public static void printCabecalho(String titulo){
        printSeparador(30);
        System.out.println(titulo);
        printSeparador(30);
    }

    static public String listar(){
        String saida ="";
        int i = 1;
        for(Produto P:listaProduto){
            saida+="\nnº"+(i++)+" - "+ P.imprimir();
        }return  saida;
    }

    public static int leiaInt(String prompt, int useChoice){
        int input;
        do{
            System.out.print(prompt);
            try{
                input = Integer.parseInt(leia.next());
            } catch(Exception e) {
                input = -1;
                System.out.println("Por favor ultilize um numero inteiro");
            }
        }while(input < 1 || input > useChoice);
        return input;
    }
    public static void abreMenu() {
        System.out.println("\n");
        printCabecalho("=============MENU============");
        System.out.println("1 – Incluir produto \n" +
                "2 – Consultar produto \n" +
                "3 – Listagem de produtos \n" +
                "4 – Realizar venda \n" +
                "5 – Vendas por período – detalhado \n" +
                "6 – Sair ");
    }

    public static void rodaPrograma(){
        int input = 1;
        do{
            Date thisDate = new Date();
            SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/YYYY");
            Metodo.abreMenu();
            input = leiaInt("=> ",6);
            if(input == 1){
                printCabecalho("=====Incluir Produto=====");
                System.out.print("Nome: ");
                String nome = leiaString.nextLine();
                System.out.print("Codigo: ");
                String codigo = leiaString.nextLine();
                System.out.print("Preço R$ ");
                Double valor = leia.nextDouble();
                System.out.print("Estoque: ");
                int estoque = leia.nextInt();


                listaProduto.add(new Produto(nome,codigo,valor,estoque));
            } else if (input == 2) {
                printCabecalho("=====Consultar Produto====");
                System.out.print("Informe o codigo do produto: ");
                String codigoInformado = leia.next();

                List<Produto> listaP = listaProduto.stream()
                        .filter(p -> p .getCodigo().contains(codigoInformado)).collect(Collectors.toList());
                if(listaP.isEmpty()){
                    System.out.println("Produto nao localizado");
                    pressAnythingToContinue();
                }else {
                    for (Produto P: listaP){
                        System.out.println(P.imprimir());
                        pressAnythingToContinue();
                    }
                }
            } else if (input == 3) {
                printCabecalho("=====Listagem de Produto=====");

                List<Produto> P = listaProduto.stream()
                        .filter(p -> p .getNome().contains(p.getNome())).collect(Collectors.toList());;
                if(P.isEmpty()){
                    System.out.println("Não existe produto cadastrado!");
                    pressAnythingToContinue();
                }else{
                    System.out.println(listar());
                    printSeparador(60);

                    DoubleSummaryStatistics R = listaProduto.stream()
                            .collect(Collectors.summarizingDouble(produto -> produto.getValor()));
                    System.out.printf("Preço Medio: %.2f | Maior Preço: %.2f | Menor Preço: %.2f \n",R.getAverage(),R.getMax(),R.getMin());
                    printSeparador(50);
                    pressAnythingToContinue();

                }
            } else if (input == 4) {
                printCabecalho("====Venda de Produto====");

                System.out.print("Informe o código do produto: ");
                String codigoInformado = leia.next();
                List<Produto> L = listaProduto.stream()
                        .filter(v -> v .getCodigo().equals(codigoInformado)).collect(Collectors.toList());
                if (L.isEmpty()) {
                    System.out.println("Produto não localizado!");
                    pressAnythingToContinue();
                } else {

                    System.out.print("Informe quantas unidades foram vendidas: ");
                    int qtdVendida = leia.nextInt();

                    //DATA
                    boolean validacao;
                    String dia,mes,ano;

                    do{
                        System.out.print("Dia(ex: 11):");
                        dia = leia.nextLine();
                        validacao = dia.matches("[0-9]{2}");
                        if(!validacao){
                            System.out.println("invalido");
                        }
                    }while(!validacao);

                    do{
                        System.out.print("Mes(ex: 02):");
                        mes = leia.nextLine();
                        validacao = mes.matches("[0-9]{2}");
                        if(!validacao){
                            System.out.println("invalido");
                        }
                    }while(!validacao);

                    do{
                        System.out.print("Ano(ex: 1987):");
                        ano = leia.nextLine();
                        validacao = ano.matches("[0-9]{4}");
                        if(!validacao){
                            System.out.println("invalido");
                        }
                    }while(!validacao);


                    String dateToday = dia+"/"+mes+"/"+ano;

                    Produto produto = L.get(0);

                    Vendas venda = new Vendas(produto, qtdVendida,dateToday);
                    //calculo do total
                    double ganho = produto.getValor();
                    double totGanho = ganho *qtdVendida;

                    Vendas venda = new Vendas(produto, qtdVendida,dateToday,totGanho);
                    listaVendas.add(venda);
                    produto.setQtd(produto.getQtd() - qtdVendida);


                    pressAnythingToContinue();

                }
            } else if (input == 5) {
                printCabecalho("==Vendas por período – detalhado==");

                System.out.println("Data da Emissão: "+dateForm.format(thisDate));

                printSeparador(30);

                Map<Produto, List<Vendas>> vendasPorProduto =
                        listaVendas.stream().collect(Collectors.groupingBy(Vendas::getPdt));
                vendasPorProduto.entrySet().forEach(v -> System.out.printf("%s | %s\n", v.getKey(), v.getValue()) );

                printSeparador(30);

                DoubleSummaryStatistics R = listaVendas.stream()
                        .collect(Collectors.summarizingDouble(Vendas::getVendaTot));
                System.out.printf("Preço Medio: %.2f | Maior Preço: %.2f | Menor Preço: %.2f \n",R.getAverage(),R.getMax(),R.getMin());

                pressAnythingToContinue();
            }

        }while (input!=6);
    }
}
