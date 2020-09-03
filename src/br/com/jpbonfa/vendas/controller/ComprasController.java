package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.ComprasDAO;
import br.com.jpbonfa.vendas.model.Compra;
import br.com.jpbonfa.vendas.model.Fornecedor;
import br.com.jpbonfa.vendas.model.Funcionario;
import br.com.jpbonfa.vendas.model.ItemCompra;
import br.com.jpbonfa.vendas.model.ItemVenda;
import br.com.jpbonfa.vendas.model.Produto;
import br.com.jpbonfa.vendas.model.Venda;
import br.com.jpbonfa.vendas.service.FuncionarioConectado;
import br.com.jpbonfa.vendas.util.Constantes;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.Numeros;
import br.com.jpbonfa.vendas.util.ServiceUtil;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.ComprasView;
import br.com.jpbonfa.vendas.vo.ItemDeCompraVO;
import br.com.jpbonfa.vendas.vo.ItemDeVendaVO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joaop
 */
public class ComprasController {

    private ComprasView viewCompras;
    private ArrayList<Fornecedor> listaFornecedores;
    private ArrayList<Funcionario> listaFuncionarios;
    private ArrayList<Produto> listaProdutos;
    private ArrayList<Produto> listaProdutosTabela = new ArrayList<>();
    private ArrayList<String> listaPagamentoTabela = new ArrayList<>();
    private ArrayList<ItemDeCompraVO> listaItemCompraVO = new ArrayList<>();
    private Produto produto;

    private int quantidadeProdutos = Numeros.ZERO;
    private double valorTotal = Numeros.ZERO_DOUBLE;
    private double valorFinal = Numeros.ZERO_DOUBLE;
    private double quantidade = Numeros.ZERO_DOUBLE;

    private String pagamento;
    private double desconto = Numeros.ZERO_DOUBLE;
    private int countPagamento = Numeros.ZERO;

    public ComprasController() {
    }

    public ComprasController(ComprasView viewCompras) {
        this.viewCompras = viewCompras;
    }

    public void carregarFornecedores() {
        listaFornecedores = new FornecedorController().buscarTodos();
        this.viewCompras.getCbFornecedor().removeAllItems();
        this.viewCompras.getCbFornecedor().addItem(Constantes.SELECIONE);
        for (Fornecedor fornecedor : listaFornecedores) {
            this.viewCompras.getCbFornecedor().addItem(fornecedor.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
        }
    }

    public void carregarFuncionarios() {
        listaFuncionarios = new FuncionarioController().buscarTodos();
        this.viewCompras.getCbFuncionario().removeAllItems();
        this.viewCompras.getCbFuncionario().addItem(Constantes.SELECIONE);
        for (Funcionario funcionario : listaFuncionarios) {
            this.viewCompras.getCbFuncionario().addItem(funcionario.getPessoaFisicaIdPessoaFisica().getNome());
        }
    }

    public void listarProdutos() {
        try {
            listaProdutos = new ProdutosController().buscarTodos();
            carregarProdutos();
        } catch (Exception e) {
            //
        }
    }

    private void carregarProdutos() {
        this.viewCompras.getCbProduto().removeAllItems();
        this.viewCompras.getCbProduto().addItem(Constantes.SELECIONE);
        for (Produto produto : listaProdutos) {
            this.viewCompras.getCbProduto().addItem(produto.getDescricao());
        }
    }

    private void carregarTabela(ItemDeCompraVO item) {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCompras.getTbCompras().getModel();
        modelo.addRow(new String[]{item.getProduto().getDescricao(), item.getQuantidade() + Constantes.VAZIO, ServiceUtil.formatarMoeda(item.getProduto().getValorCusto()), ServiceUtil.formatarMoeda(item.getValorTotal())});
    }

    private void carregarTabelaPagamento(String pagamento) {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCompras.getTbPagamento().getModel();
        double desconto = Numeros.ZERO_DOUBLE;
        if (!Valida.verificaStringVazio(this.viewCompras.getTfDesconto().getText())) {
            desconto = Double.parseDouble(this.viewCompras.getTfDesconto().getText());
        }
        modelo.addRow(new String[]{pagamento, ServiceUtil.formatarMoeda(desconto)});
    }

    private void atualizarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCompras.getTbCompras().getModel();
        if (this.viewCompras.getTbCompras().getSelectedRow() >= Numeros.ZERO) {
            modelo.removeRow(this.viewCompras.getTbCompras().getSelectedRow());
            this.viewCompras.getTbCompras().setModel(modelo);
        }
    }

    private void atualizarTabelaPagamento() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCompras.getTbPagamento().getModel();
        if (this.viewCompras.getTbPagamento().getSelectedRow() >= Numeros.ZERO) {
            modelo.removeRow(this.viewCompras.getTbPagamento().getSelectedRow());
            this.viewCompras.getTbPagamento().setModel(modelo);
        }
    }

    private void valorTotal(Produto produto) {
        quantidade = Numeros.ZERO_DOUBLE;
        double valorUnitario = Numeros.ZERO_DOUBLE;

        quantidade = Double.parseDouble(this.viewCompras.getTfQuantidade().getText());
        valorUnitario = produto.getValorCusto();
        valorTotal = valorUnitario * quantidade;

    }

    public void acaoBotaoIniciarCompra() {
        desbloquearCampos();
        this.viewCompras.getBtConfirmar().setEnabled(true);
        this.viewCompras.getBtCancelar().setEnabled(true);
        this.viewCompras.getCbFuncionario().setEnabled(false);
        this.viewCompras.getCbFornecedor().setEnabled(false);
        this.viewCompras.getBtIniciar().setEnabled(false);

    }

    public void acaoBotaoExcluirProduto() {
        if (this.viewCompras.getTbCompras().getSelectedRow() < Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneProduto);
        } else {
            produto = listaProdutosTabela.get(this.viewCompras.getTbCompras().getSelectedRow());
            listaProdutosTabela.remove(produto);
            listaProdutos.add(produto);
            valorFinal -= valorTotal;
            carregarProdutos();
            atualizarTabela();
        }

    }

    public void acaoBotaoIncluirProduto() {
        if (validaTabela()) {
            countPagamento++;
            produto = listaProdutos.get(viewCompras.getCbProduto().getSelectedIndex() - Numeros.UM);
            valorTotal(produto);

            ItemDeCompraVO vo = new ItemDeCompraVO();
            vo.setProduto(produto);
            vo.setQuantidade(quantidadeProdutos);
            vo.setValorTotal(valorTotal);

            listaItemCompraVO.add(vo);
            listaProdutos.remove(produto);
            quantidadeProdutos = Integer.parseInt(this.viewCompras.getTfQuantidade().getText());
            carregarTabela(vo);
            carregarProdutos();
            this.viewCompras.getTfQuantidade().setText(null);
            valorFinal += valorTotal;
            if (countPagamento == Numeros.UM) {
                this.viewCompras.getCbPagamento().setEnabled(true);
                this.viewCompras.getBtIncluirPagamento().setEnabled(true);
                this.viewCompras.getBtExcluirPagamento().setEnabled(false);

            }

        }

    }

    public void acaoBotaoExcluirPagamento() {
        if (this.viewCompras.getTbPagamento().getSelectedRow() < Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneFormaDePagamento);
        } else {
            countPagamento--;
            pagamento = listaPagamentoTabela.get(this.viewCompras.getTbPagamento().getSelectedRow());
            listaPagamentoTabela.remove(pagamento);
            this.viewCompras.getCbPagamento().setEnabled(true);
            this.viewCompras.getBtIncluirPagamento().setEnabled(true);
            this.viewCompras.getBtExcluirPagamento().setEnabled(false);
            this.viewCompras.getCbPagamento().setSelectedIndex(0);
            valorFinal += desconto;
            atualizarTabelaPagamento();
            this.viewCompras.getLbValorTotal().setText(null);

        }

    }

    public void acaoBotaoAdcionarPagamento() {
        if (validaTabelaPagamento()) {
            pagamento = viewCompras.getCbPagamento().getSelectedItem() + Constantes.VAZIO;
            listaPagamentoTabela.add(pagamento);
            carregarTabelaPagamento(pagamento);
            bloquearPagamento();
            if (!Valida.verificaStringVazio(this.viewCompras.getTfDesconto().getText())) {
                desconto = Double.parseDouble(this.viewCompras.getTfDesconto().getText());
            } else {
                desconto = Numeros.ZERO_DOUBLE;
            }
            this.viewCompras.getTfDesconto().setText(null);
            mostrarValorfinal();
        }
    }

    public void acaoBotaoSair() {
        this.viewCompras.setVisible(false);

    }

    public void acaoBotaoCancelar() {
        limparCampos();
        bloquearCampos();
        this.viewCompras.getCbFuncionario().setEnabled(true);
        this.viewCompras.getCbFornecedor().setEnabled(true);
        this.viewCompras.getBtIniciar().setEnabled(false);
        this.viewCompras.getLbValorTotal().setText(null);

    }

    public void acaoBotaoConfirmar() {
        if (validaConfirmar()) {
            Compra compra = new Compra();
            compra.setFornecedorIdFornecedor(listaFornecedores.get(this.viewCompras.getCbFornecedor().getSelectedIndex() - Numeros.UM));
            compra.setFuncionarioIdFuncionario(listaFuncionarios.get(this.viewCompras.getCbFuncionario().getSelectedIndex() - Numeros.UM));
            compra.setDataCompra(ServiceUtil.dataCompleta());
            compra.setFormaPagamento(pagamento);
            valorFinal -= desconto;
            DecimalFormat decimal = new DecimalFormat("0.00");
            compra.setValorTotal(Double.parseDouble(decimal.format(valorFinal)));
            salvar(compra);
            new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_COMPRA, FuncionarioConectado.funcionarioConectado);
            salvarItemCompra(compra);
            acaoBotaoCancelar();
        }
    }

    private void salvarItemCompra(Compra compra) {
        for (ItemDeCompraVO item : listaItemCompraVO) {
            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setProdutoIdProduto(item.getProduto());
            itemCompra.setQuantidadeProduto(item.getQuantidade());
            itemCompra.setValorTotal(item.getValorTotal());
            itemCompra.setCompraIdCompra(compra);
            new ItemCompraController().salvar(itemCompra);
            new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_ITEM_COMPRA, FuncionarioConectado.funcionarioConectado);
        }

    }

    private void salvar(Compra compra) {
        try {
            new ComprasDAO().salvar(compra);
            JOptionPaneUtil.sucesso(Mensagem.compraSalva);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bloquearCampos() {
        //bloqueio dos produtos
        this.viewCompras.getCbProduto().setEnabled(false);
        this.viewCompras.getTfQuantidade().setEditable(false);
        this.viewCompras.getBtIncluirProduto().setEnabled(false);
        this.viewCompras.getBtExcluirProduto().setEnabled(false);
        this.viewCompras.getBtIniciar().setEnabled(false);
        //bloqueio das formas de pagamento
        this.viewCompras.getCbPagamento().setEnabled(false);
        this.viewCompras.getBtIncluirPagamento().setEnabled(false);
        this.viewCompras.getBtExcluirPagamento().setEnabled(false);
        this.viewCompras.getTfDesconto().setVisible(false);
        this.viewCompras.getLbDesconto().setVisible(false);
        //bloquear botoes
        this.viewCompras.getBtConfirmar().setEnabled(false);
        this.viewCompras.getBtCancelar().setEnabled(false);
        this.viewCompras.getBtSair().setEnabled(true);

    }

    private void desbloquearCampos() {
        //desbloqueio dos dados
        this.viewCompras.getCbProduto().setEnabled(true);
        this.viewCompras.getTfQuantidade().setEditable(true);
        this.viewCompras.getBtIncluirProduto().setEnabled(true);
        this.viewCompras.getBtExcluirProduto().setEnabled(true);
    }

    private void desbloquearCamposPagamento() {
        //desbloqueio das formas de pagamento
        this.viewCompras.getCbPagamento().setEnabled(true);
        this.viewCompras.getBtIncluirPagamento().setEnabled(true);
        this.viewCompras.getBtExcluirPagamento().setEnabled(false);
    }

    private void desbloquearBotoes() {
        this.viewCompras.getBtConfirmar().setEnabled(true);
        this.viewCompras.getBtCancelar().setEnabled(true);
        this.viewCompras.getBtSair().setEnabled(false);

    }

    private void bloquearPagamento() {
        this.viewCompras.getCbPagamento().setEnabled(false);
        this.viewCompras.getBtIncluirPagamento().setEnabled(false);
        this.viewCompras.getBtExcluirPagamento().setEnabled(true);
    }

    private void desbloquearPagamento() {
        this.viewCompras.getCbPagamento().setEnabled(true);
        this.viewCompras.getBtIncluirPagamento().setEnabled(true);
        this.viewCompras.getBtExcluirPagamento().setEnabled(false);

    }

    public void desbloquearDesconto() {
        if (this.viewCompras.getCbPagamento().getSelectedIndex() == Numeros.UM) {
            this.viewCompras.getTfDesconto().setVisible(true);
            this.viewCompras.getLbDesconto().setVisible(true);
        } else {
            this.viewCompras.getLbDesconto().setVisible(false);
            this.viewCompras.getTfDesconto().setVisible(false);
        }
    }

    private void limparCampos() {
        //limpar Dados
        this.viewCompras.getCbFuncionario().setSelectedIndex(Numeros.ZERO);
        this.viewCompras.getCbFornecedor().setSelectedIndex(Numeros.ZERO);
        //Limpar Produtos
        this.viewCompras.getCbProduto().setSelectedIndex(Numeros.ZERO);
        this.viewCompras.getTfQuantidade().setText(null);
        //Limpar formas de pagamento
        this.viewCompras.getCbPagamento().setSelectedIndex(Numeros.ZERO);
        DefaultTableModel modelo = (DefaultTableModel) this.viewCompras.getTbPagamento().getModel();
        modelo.setRowCount(Numeros.ZERO);
        DefaultTableModel modeloCompras = (DefaultTableModel) this.viewCompras.getTbCompras().getModel();
        modeloCompras.setRowCount(Numeros.ZERO);
    }

    public void desbloqueioIniciar() {
        if (this.viewCompras.getCbFuncionario().getSelectedIndex() > Numeros.ZERO && this.viewCompras.getCbFornecedor().getSelectedIndex() > Numeros.ZERO) {
            this.viewCompras.getBtIniciar().setEnabled(true);
        } else {
            this.viewCompras.getBtIniciar().setEnabled(false);
        }
    }

    public boolean validaTabela() {
        if (this.viewCompras.getCbProduto().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneProduto);
            this.viewCompras.getCbProduto().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewCompras.getTfQuantidade().getText())) {
            JOptionPaneUtil.erro(Mensagem.InformeQuantidade);
            this.viewCompras.getTfQuantidade().grabFocus();
            return false;
        }
        return true;
    }

    private boolean validaTabelaPagamento() {
        if (this.viewCompras.getCbPagamento().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneFormaDePagamento);
            this.viewCompras.getCbProduto().grabFocus();
            return false;
        }
        return true;
    }

    private boolean validaConfirmar() {
        if (listaItemCompraVO.size() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeProduto);
            return false;
        }
        if (listaPagamentoTabela.size() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneFormaDePagamento);

        }

        return true;
    }

    public void mostrarValorfinal() {

        valorFinal -= desconto;

        this.viewCompras.getLbValorTotal().setText(ServiceUtil.formatarMoeda(valorFinal));
    }

}
