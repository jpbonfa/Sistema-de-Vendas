package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.ProdutoDAO;
import br.com.jpbonfa.vendas.model.Fornecedor;
import br.com.jpbonfa.vendas.model.Produto;
import br.com.jpbonfa.vendas.service.FuncionarioConectado;
import br.com.jpbonfa.vendas.util.Constantes;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.Numeros;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.ProdutosView;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joaop
 */
public class ProdutosController {

    ProdutosView viewProdutos;
    private ArrayList<Produto> listaProduto;
    private ArrayList<Fornecedor> listaFornecedor;
    private Produto produto;
    private boolean alterar = false;

    public ProdutosController() {
    }

    public ProdutosController(ProdutosView viewProdutos) {
        this.viewProdutos = viewProdutos;
    }

    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewProdutos.getTbProdutos().getModel();
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance();
        modelo.setRowCount(Numeros.ZERO);
        for (Produto produto : listaProduto) {
            // Adicioando a linha com os dados
            modelo.addRow(new String[]{produto.getDescricao(), produto.getFornecedorIdFornecedor().getPessoaJuridicaIdPessoaJuridica().getRazaoSocial(), formatoMoeda.format(produto.getValorCusto()) + Constantes.VAZIO, formatoMoeda.format(produto.getValorVenda()) + Constantes.VAZIO});
        }
    }

    public void carregarCombo() {
        listaFornecedor = new FornecedorController().buscarTodos();
        this.viewProdutos.getCbFornecedor().removeAllItems();
        this.viewProdutos.getCbFornecedor().addItem(Constantes.SELECIONE);
        for (Fornecedor fornecedor : listaFornecedor) {
            this.viewProdutos.getCbFornecedor().addItem(fornecedor.getContato());
        }
    }

    public void listarProduto() {
        try {
            listaProduto = buscarTodos();
            carregarTabela();
        } catch (Exception e) {
            //tratar erro de listagem
        }
    }

    public ArrayList<Produto> buscarTodos() {
        return new ProdutoDAO().buscarTodos();
    }

    public void acaoBotaoNovo() {
        desbloquearCampos();
        this.viewProdutos.getBtNovo().setEnabled(false);
        this.viewProdutos.getBtAlterar().setEnabled(false);
        this.viewProdutos.getBtExcluir().setEnabled(false);
        this.viewProdutos.getBtSair().setEnabled(false);
        this.viewProdutos.getBtSalvar().setEnabled(true);
        this.viewProdutos.getBtCancelar().setEnabled(true);
    }

    public void acaoBotaoAlterar() {
        if (this.viewProdutos.getTbProdutos().getSelectedRow() < Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneProduto);
        } else {
            alterar = true;
            desbloqueioAlterar();
            produto = listaProduto.get(this.viewProdutos.getTbProdutos().getSelectedRow());
            carregarTela();
        }
    }

    public void desbloqueioAlterar() {
        desbloquearCampos();
        this.viewProdutos.getTfDescricao().setEditable(false);
        this.viewProdutos.getBtNovo().setEnabled(false);
        this.viewProdutos.getBtAlterar().setEnabled(false);
        this.viewProdutos.getBtExcluir().setEnabled(false);
        this.viewProdutos.getBtSair().setEnabled(false);
        this.viewProdutos.getBtSalvar().setEnabled(true);
        this.viewProdutos.getBtCancelar().setEnabled(true);
    }

    public void acaoBotaoExcluir() {
        if (this.viewProdutos.getTbProdutos().getSelectedRow() < Numeros.ZERO) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneProduto, Mensagem.selecione, Numeros.ZERO);
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.desejaExcluirProduto);
            if (opcao == JOptionPane.YES_OPTION) {
                try {
                    produto = listaProduto.get(this.viewProdutos.getTbProdutos().getSelectedRow());
                    //comandos de exclusao
                    excluir(produto);
                     new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_PRODUTO, FuncionarioConectado.funcionarioConectado);
                    JOptionPane.showMessageDialog(null, Mensagem.produtoExcluido, Mensagem.sucesso, Numeros.UM);
                    listarProduto();
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        }
    }

    public void acaoBotaoSair() {
        this.viewProdutos.setVisible(false);

    }

    public void acaoBotaoCancelar() {
        limparCampos();
        carregarTabela();
        this.viewProdutos.getBtNovo().setEnabled(true);
        this.viewProdutos.getBtAlterar().setEnabled(true);
        this.viewProdutos.getBtExcluir().setEnabled(true);
        this.viewProdutos.getBtSair().setEnabled(true);
        this.viewProdutos.getBtSalvar().setEnabled(false);
        this.viewProdutos.getBtCancelar().setEnabled(false);
        this.viewProdutos.getCbFornecedor().setEnabled(false);

    }

    public void acaoBotaoSalvar() {
        if (alterar) {
            if (validaAlterar()) {
                //alterar um Produto
                produto = listaProduto.get(this.viewProdutos.getTbProdutos().getSelectedRow());
                produto.setValorVenda(Double.parseDouble(this.viewProdutos.getTfValorVenda().getText()));
                produto.setValorCusto(Double.parseDouble(this.viewProdutos.getTfValorCompra().getText()));
                produto.setFornecedorIdFornecedor(listaFornecedor.get(this.viewProdutos.getCbFornecedor().getSelectedIndex() - Numeros.UM));
                salvar(produto);
                new LogUsuarioController().gerarLog(Constantes.UPDATE, Constantes.TABELA_PRODUTO, FuncionarioConectado.funcionarioConectado);
                JOptionPaneUtil.sucesso(Mensagem.produtoAlterado);
                acaoBotaoCancelar();
                listarProduto();
            }
        } else {
            if (valida()) {
                Produto produto = new Produto();
                produto.setDescricao(this.viewProdutos.getTfDescricao().getText());
                produto.setValorVenda(Double.parseDouble(this.viewProdutos.getTfValorVenda().getText()));
                produto.setValorCusto(Double.parseDouble(this.viewProdutos.getTfValorCompra().getText()));
                produto.setFornecedorIdFornecedor(listaFornecedor.get(this.viewProdutos.getCbFornecedor().getSelectedIndex() - Numeros.UM));
                salvar(produto);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_PRODUTO, FuncionarioConectado.funcionarioConectado);
                JOptionPaneUtil.sucesso(Mensagem.produtoSalvo);
                acaoBotaoCancelar();
                listarProduto();
            }
        }
    }

    public void salvar(Produto produto) {
        try {
            new ProdutoDAO().salvar(produto);

        } catch (Exception e) {
            JOptionPaneUtil.erro(Mensagem.produtoErroSalvar);

        }

    }

    public void excluir(Produto produto) {
        try {
            new ProdutoDAO().excluir(produto);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Mensagem.produtoErroExcluir, Mensagem.erro, Numeros.ZERO);
            e.printStackTrace();
        }
    }

    public void bloquearCampos() {
        this.viewProdutos.getTfDescricao().setEditable(false);
        this.viewProdutos.getTfValorCompra().setEditable(false);
        this.viewProdutos.getTfValorVenda().setEditable(false);
        this.viewProdutos.getCbFornecedor().setEnabled(false);
        this.viewProdutos.getBtSalvar().setEnabled(false);
        this.viewProdutos.getBtCancelar().setEnabled(false);

    }

    private void desbloquearCampos() {
        this.viewProdutos.getTfDescricao().setEditable(true);
        this.viewProdutos.getTfValorCompra().setEditable(true);
        this.viewProdutos.getTfValorVenda().setEditable(true);
        this.viewProdutos.getCbFornecedor().setEnabled(true);
        this.viewProdutos.getBtSalvar().setEnabled(true);
        this.viewProdutos.getBtCancelar().setEnabled(true);
    }

    private void limparCampos() {
        this.viewProdutos.getTfDescricao().setText(null);
        this.viewProdutos.getTfValorCompra().setText(null);
        this.viewProdutos.getTfValorVenda().setText(null);
        this.viewProdutos.getCbFornecedor().setSelectedIndex(Numeros.ZERO);
    }

    private boolean valida() {
        if (Valida.verificaStringVazio(this.viewProdutos.getTfDescricao().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeDescriçao);
            this.viewProdutos.getTfDescricao().grabFocus();
            return false;
        }
        if (this.viewProdutos.getCbFornecedor().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeFornecedor);
            this.viewProdutos.getCbFornecedor().grabFocus();
            return false;
        }
        if (Valida.verificaDoubleZero(this.viewProdutos.getTfValorCompra().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeValorCompra);
            this.viewProdutos.getTfValorCompra().grabFocus();
            return false;
        }
        if (Valida.verificaDoubleZero(this.viewProdutos.getTfValorVenda().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeValorVenda);
            this.viewProdutos.getTfValorVenda().grabFocus();
            return false;
        }

        return true;
    }

    private boolean validaAlterar() {

        if (this.viewProdutos.getCbFornecedor().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeFornecedor);
            this.viewProdutos.getCbFornecedor().grabFocus();
            return false;
        }
        if (Valida.verificaDoubleZero(this.viewProdutos.getTfValorCompra().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeValorCompra);
            this.viewProdutos.getTfValorCompra().grabFocus();
            return false;
        }
        if (Valida.verificaDoubleZero(this.viewProdutos.getTfValorVenda().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeValorVenda);
            this.viewProdutos.getTfValorVenda().grabFocus();
            return false;
        }

        return true;
    }

    public void carregarTela() {

        this.viewProdutos.getTfDescricao().setText(produto.getDescricao());
        this.viewProdutos.getCbFornecedor().setSelectedItem(produto.getFornecedorIdFornecedor().getContato());
        this.viewProdutos.getTfValorCompra().setText(produto.getValorCusto() + Constantes.VAZIO);
        this.viewProdutos.getTfValorVenda().setText(produto.getValorVenda() + Constantes.VAZIO);

    }
}
