package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.EstoqueDAO;
import br.com.jpbonfa.vendas.model.Estoque;
import br.com.jpbonfa.vendas.model.Produto;
import br.com.jpbonfa.vendas.service.FuncionarioConectado;
import br.com.jpbonfa.vendas.util.Constantes;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.Numeros;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.EstoqueView;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joaop
 */
public class EstoqueController {

    private EstoqueView viewEstoque;
    private boolean alterar = false;
    private ArrayList<Estoque> listaEstoque;
    private ArrayList<Produto> listaProduto;
    private Estoque estoque;

    public EstoqueController() {
    }

    public EstoqueController(EstoqueView viewEstoque) {
        this.viewEstoque = viewEstoque;
    }

    public void carregarCombo() {
        listaProduto = new ProdutosController().buscarTodos();
        this.viewEstoque.getCbProduto().removeAllItems();
        this.viewEstoque.getCbProduto().addItem(Constantes.SELECIONE);
        for (Produto produto : listaProduto) {
            this.viewEstoque.getCbProduto().addItem(produto.getDescricao());
        }
    }

    public void acaoBotaoNovo() {
        desbloquearCampos();
        this.viewEstoque.getBtNovo().setEnabled(false);
        this.viewEstoque.getBtAlterar().setEnabled(false);
        this.viewEstoque.getBtExcluir().setEnabled(false);
        this.viewEstoque.getBtSair().setEnabled(false);

    }

    public void acaoBotaoAlterar() {
        if (this.viewEstoque.getTbEstoque().getSelectedRow() < Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneEstoque);
        } else {
            alterar = true;
            desbloqueioAlterar();
            estoque = listaEstoque.get(this.viewEstoque.getTbEstoque().getSelectedRow());
            carregarTela();
        }
    }

    public void acaoBotaoExcluir() {
        if (this.viewEstoque.getTbEstoque().getSelectedRow() < Numeros.ZERO) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneEstoque, Mensagem.selecione, Numeros.ZERO);
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.desejaExcluirEstoque);
            if (opcao == JOptionPane.YES_OPTION) {
                try {
                    estoque = listaEstoque.get(this.viewEstoque.getTbEstoque().getSelectedRow());
                    //comandos de exclusao
                    excluir(estoque);
                    new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_ESTOQUE, FuncionarioConectado.funcionarioConectado);
                    JOptionPane.showMessageDialog(null, Mensagem.estoqueExcluido, Mensagem.sucesso, Numeros.UM);
                    listarEstoque();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void acaoBotaoSair() {
        this.viewEstoque.setVisible(false);

    }

    public void acaoBotaoSalvar() {
        if (alterar) {
            if (validaAlterar()) {
                //alterar um cliente
                estoque = listaEstoque.get(this.viewEstoque.getTbEstoque().getSelectedRow());
                estoque.setQuantidadeMinima(Integer.parseInt(this.viewEstoque.getTfQuantidadeMinima().getText()));
                salvar(estoque);
                new LogUsuarioController().gerarLog(Constantes.UPDATE, Constantes.TABELA_ESTOQUE, FuncionarioConectado.funcionarioConectado);
                JOptionPaneUtil.sucesso(Mensagem.estoqueAlterado);
                limparCampos();
                acaoBotaoCancelar();
                listarEstoque();
            }
        } else {
            if (valida()) {
                Estoque estoque = new Estoque();
                estoque.setProdutoIdProduto(listaProduto.get(this.viewEstoque.getCbProduto().getSelectedIndex() - Numeros.UM));
                estoque.setQuantidadeEstoque(Integer.parseInt(this.viewEstoque.getTfEstoque().getText()));
                estoque.setQuantidadeMinima(Integer.parseInt(this.viewEstoque.getTfQuantidadeMinima().getText()));
                salvar(estoque);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_ESTOQUE, FuncionarioConectado.funcionarioConectado);
                JOptionPaneUtil.sucesso(Mensagem.estoqueSalvo);
                limparCampos();
                acaoBotaoCancelar();
                listarEstoque();
            }
        }
    }

    public void salvar(Estoque estoque) {
        try {
            new EstoqueDAO().salvar(estoque);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Mensagem.estoqueErroExcluir, Mensagem.erro, Numeros.ZERO);
            e.printStackTrace();
        }
    }

    public void excluir(Estoque estoque) {
        try {
            new EstoqueDAO().excluir(estoque);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void acaoBotaoCancelar() {
        bloquearCampos();
        this.viewEstoque.getBtNovo().setEnabled(true);
        this.viewEstoque.getBtAlterar().setEnabled(true);
        this.viewEstoque.getBtExcluir().setEnabled(true);
        this.viewEstoque.getBtSair().setEnabled(true);
        carregarTabela();
        limparCampos();
    }

    public void bloquearCampos() {
        this.viewEstoque.getCbProduto().setEnabled(false);
        this.viewEstoque.getTfQuantidadeMinima().setEditable(false);
        this.viewEstoque.getTfEstoque().setEditable(false);
        this.viewEstoque.getBtSalvar().setEnabled(false);
        this.viewEstoque.getBtCancelar().setEnabled(false);
    }

    public void desbloquearCampos() {
        this.viewEstoque.getCbProduto().setEnabled(true);
        this.viewEstoque.getTfQuantidadeMinima().setEditable(true);
        this.viewEstoque.getTfEstoque().setEditable(true);
        this.viewEstoque.getBtSalvar().setEnabled(true);
        this.viewEstoque.getBtCancelar().setEnabled(true);
    }

    private void desbloqueioAlterar() {
        this.viewEstoque.getCbProduto().setEnabled(false);
        this.viewEstoque.getTfQuantidadeMinima().setEditable(true);
        this.viewEstoque.getTfEstoque().setEditable(false);
        this.viewEstoque.getBtSalvar().setEnabled(true);
        this.viewEstoque.getBtCancelar().setEnabled(true);
    }

    private void limparCampos() {
        this.viewEstoque.getCbProduto().setSelectedIndex(Numeros.ZERO);
        this.viewEstoque.getTfEstoque().setText(null);
        this.viewEstoque.getTfQuantidadeMinima().setText(null);
    }

    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewEstoque.getTbEstoque().getModel();
        modelo.setRowCount(Numeros.ZERO);
        for (Estoque estoque : listaEstoque) {
            modelo.addRow(new String[]{estoque.getProdutoIdProduto().getDescricao(), estoque.getProdutoIdProduto().getFornecedorIdFornecedor().getContato(), estoque.getQuantidadeEstoque() + Constantes.VAZIO, estoque.getQuantidadeMinima() + Constantes.VAZIO,});
        }
    }

    public void listarEstoque() {
        try {
            listaEstoque = buscarTodos();
            carregarTabela();
        } catch (Exception e) {
            //tratar erro de listagem
        }
    }

    public ArrayList<Estoque> buscarTodos() {
        return new EstoqueDAO().buscarTodos();
    }

    private void carregarTela() {
        this.viewEstoque.getCbProduto().setSelectedItem(estoque.getProdutoIdProduto().getDescricao());
        this.viewEstoque.getTfEstoque().setText(estoque.getQuantidadeEstoque() + Constantes.VAZIO);
        this.viewEstoque.getTfQuantidadeMinima().setText(estoque.getQuantidadeMinima() + Constantes.VAZIO);
    }

    private boolean valida() {
        if (this.viewEstoque.getCbProduto().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeProduto);
            this.viewEstoque.getCbProduto().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewEstoque.getTfEstoque().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEstoque);
            this.viewEstoque.getTfEstoque().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewEstoque.getTfQuantidadeMinima().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeQuantidadeMinima);
            this.viewEstoque.getTfQuantidadeMinima().grabFocus();
            return false;
        }
        return true;
    }

    private boolean validaAlterar() {
        if (Valida.verificaIntZero(this.viewEstoque.getTfEstoque().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEstoque);
            this.viewEstoque.getTfEstoque().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewEstoque.getTfQuantidadeMinima().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeQuantidadeMinima);
            this.viewEstoque.getTfQuantidadeMinima().grabFocus();
            return false;
        }
        return true;
    }

}
