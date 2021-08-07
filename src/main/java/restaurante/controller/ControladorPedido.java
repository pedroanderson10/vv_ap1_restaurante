package restaurante.controller;

import restaurante.model.ItemPedido;
import restaurante.model.Pedido;
import restaurante.model.Produto;
import restaurante.dao.BDPedido;
import restaurante.dao.DAOProduto;
import restaurante.validation.ProdutoNaoEncontradoException;

public class ControladorPedido extends Controlador {

    public void realizarPedido(DAOProduto DAOProduto) throws Exception {

        instanciarAtributos();

        //bdProduto.listarProdutosDisponiveis();
        DAOProduto.listProdutosDisponiveis();

        System.out.println("\nMONTE SEU PEDIDO");
        Pedido novoPedido = new Pedido();

        do {
            System.out.println("Insira o codigo do produto que deseja inserir no carrinho : ");
            int codigo = scan.nextInt();
            scan.nextLine();
            try {
                Produto produto = DAOProduto.procurarProduto(codigo);

                System.out.println("Insira a quantidade de itens do produto escolhido : ");
                int quantidade = scan.nextInt(); scan.nextLine();
                ItemPedido item = new ItemPedido(produto,quantidade);
                novoPedido.adicionarItemPedido(item);

            }catch(ProdutoNaoEncontradoException p) {
                System.out.println(p.getMessage());
            }
            System.out.println("Deseja inserir um novo item? s-SIM / n-NAO");
        }while(scan.nextLine().equals("s"));

        System.out.println("Valor Total do Pedido = " + novoPedido.obterValorTotalPedido());
        System.out.println("---------------------------------------------------------------");
        BDPedido BDpedido = new BDPedido();
        BDpedido.adicionarPedido(novoPedido);
    }

}
