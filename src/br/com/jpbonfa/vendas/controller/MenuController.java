package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.service.FuncionarioConectado;
import br.com.jpbonfa.vendas.util.Constantes;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.Numeros;
import br.com.jpbonfa.vendas.view.ClienteView;
import br.com.jpbonfa.vendas.view.ComprasView;
import br.com.jpbonfa.vendas.view.ContasAPagarView;
import br.com.jpbonfa.vendas.view.ContasAReceberView;
import br.com.jpbonfa.vendas.view.EstoqueView;
import br.com.jpbonfa.vendas.view.FornecedorView;
import br.com.jpbonfa.vendas.view.FuncionarioView;
import br.com.jpbonfa.vendas.view.LoginView;
import br.com.jpbonfa.vendas.view.MenuView;
import br.com.jpbonfa.vendas.view.ProdutosView;
import br.com.jpbonfa.vendas.view.VendasView;
import javax.swing.JOptionPane;

/**
 *
 * @author joaop
 */
public class MenuController {

    private MenuView viewMenu;

    public MenuController() {
    }

    public MenuController(MenuView viewMenu) {
        this.viewMenu = viewMenu;
    }

    public void acaoBotaoClientes() {
        new ClienteView();
    }

    public void acaoBotaoFornecedor() {
        new FornecedorView();
    }

    public void acaoBotaoFuncionario() {
        new FuncionarioView();
    }

    public void acaoBotaoProdutos() {
        new ProdutosView();
    }

    public void acaoBotaoVendas() {
        new VendasView();
    }

    public void acaoBotaoCompras() {
        new ComprasView();
    }

    public void acaoBotaoSair() {
        int opcao = JOptionPane.showConfirmDialog(null, Mensagem.desejaEncerrar, Mensagem.atencao, JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            System.exit(Numeros.ZERO);
        }
    }

    public void acaoMenuLogout() {
        this.viewMenu.setVisible(false);
        new LogUsuarioController().gerarLog(Constantes.LOGOUT, Constantes.VAZIO, FuncionarioConectado.funcionarioConectado);
        new LoginView();
    }

    public void acaoMenuContasAPagar() {
        new ContasAPagarView();
    }

    public void acaoMenuContasAReceber() {
        new ContasAReceberView();
    }

    public void acaoMenuEstoque() {
        new EstoqueView();
    }

    public void acaoMenuEfetuarCompra() {
        new ComprasView();
    }

    public void acaoMenuEfetuarVenda() {
        new VendasView();
    }

}
