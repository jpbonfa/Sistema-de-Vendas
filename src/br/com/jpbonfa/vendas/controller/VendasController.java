package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.VendaDAO;
import br.com.jpbonfa.vendas.model.Cliente;
import br.com.jpbonfa.vendas.model.Funcionario;
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
import br.com.jpbonfa.vendas.view.VendaPrazoView;
import br.com.jpbonfa.vendas.view.VendasView;
import br.com.jpbonfa.vendas.vo.ItemDeVendaVO;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joaop
 */
public class VendasController {

    private VendasView viewVendas;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Funcionario> listaFuncionarios;
    private ArrayList<Produto> listaProdutos;
    private ArrayList<String> listaPagamentoTabela = new ArrayList<>();
    private ArrayList<ItemDeVendaVO> listaItemVenda = new ArrayList<>();
    private Produto produto;

        private double valorTotal = Numeros.ZERO_DOUBLE;
    private double valorFinal = Numeros.ZERO_DOUBLE;
    private int quantidade = Numeros.ZERO;
    private double desconto = Numeros.ZERO;
    private double descontoPagamento = Numeros.ZERO;
    private String pagamento;
    private int countPagamento;
    private int countConfirma;

    private boolean aPrazo = false;

    public VendasController() {
    }

    public VendasController(VendasView viewVendas) {
        this.viewVendas = viewVendas;
    }

    public void carregarClientes() {
        listaClientes = new ClienteController().buscarTodos();
        this.viewVendas.getCbCliente().removeAllItems();
        this.viewVendas.getCbCliente().addItem(Constantes.SELECIONE);
        for (Cliente cliente : listaClientes) {
            if (cliente.getTipoPessoa().equals(Constantes.LETRA_F)) {
                this.viewVendas.getCbCliente().addItem(cliente.getPessoaFisicaIdPessoaFisica().getNome());
            } else {
                this.viewVendas.getCbCliente().addItem(cliente.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
            }
        }
    }

    public void carregarFuncionarios() {
        listaFuncionarios = new FuncionarioController().buscarTodos();
        this.viewVendas.getCbFuncionario().removeAllItems();
        this.viewVendas.getCbFuncionario().addItem(Constantes.SELECIONE);
        for (Funcionario funcionario : listaFuncionarios) {
            this.viewVendas.getCbFuncionario().addItem(funcionario.getPessoaFisicaIdPessoaFisica().getNome());
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

    public void carregarProdutos() {
        this.viewVendas.getCbProduto().removeAllItems();
        this.viewVendas.getCbProduto().addItem(Constantes.SELECIONE);
        for (Produto produto : listaProdutos) {
            this.viewVendas.getCbProduto().addItem(produto.getDescricao());
        }
    }

    private void carregarTabela(ItemDeVendaVO item) {
        DefaultTableModel modelo = (DefaultTableModel) this.viewVendas.getTbVendas().getModel();
        modelo.addRow(new String[]{item.getProduto().getDescricao(), item.getQuantidade() + Constantes.VAZIO, ServiceUtil.formatarMoeda(item.getProduto().getValorVenda()), ServiceUtil.formatarMoeda(item.getDesconto()), ServiceUtil.formatarMoeda(item.getValorTotal())});
    }

    private void carregarTabelaPagamento(String pagamento) {
        DefaultTableModel modelo = (DefaultTableModel) this.viewVendas.getTbFormaPagamento().getModel();
        double desconto = Numeros.ZERO_DOUBLE;
        if (!Valida.verificaStringVazio(this.viewVendas.getTfDescontoPagamento().getText())) {
            desconto = Double.parseDouble(this.viewVendas.getTfDescontoPagamento().getText());

        }
        modelo.addRow(new String[]{pagamento, ServiceUtil.formatarMoeda(desconto)});
    }

    private void atualizarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewVendas.getTbVendas().getModel();
        if (this.viewVendas.getTbVendas().getSelectedRow() >= Numeros.ZERO) {
            modelo.removeRow(this.viewVendas.getTbVendas().getSelectedRow());
            this.viewVendas.getTbVendas().setModel(modelo);
        }
    }

    private void atualizarTabelaPagamento() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewVendas.getTbFormaPagamento().getModel();
        if (this.viewVendas.getTbFormaPagamento().getSelectedRow() >= Numeros.ZERO) {
            modelo.removeRow(this.viewVendas.getTbFormaPagamento().getSelectedRow());
            this.viewVendas.getTbFormaPagamento().setModel(modelo);
        }

    }

    private void valorTotal(Produto produto) {
        quantidade = Numeros.ZERO;
        double valorUnitario = Numeros.ZERO;
        if (!Valida.verificaStringVazio(this.viewVendas.getTfDescontoProduto().getText())) {
            desconto = Double.parseDouble(this.viewVendas.getTfDescontoProduto().getText());
        } else {
            desconto = 0.0;
        }
        quantidade = Integer.parseInt(this.viewVendas.getTfQuantidade().getText());
        valorUnitario = produto.getValorVenda();
        valorTotal = (valorUnitario * quantidade) - desconto;

    }

    public void acaoBotaoIniciarVenda() {
        desbloquearCampos();
        this.viewVendas.getCbFuncionario().setEnabled(false);
        this.viewVendas.getCbCliente().setEnabled(false);
        this.viewVendas.getBtIniciar().setEnabled(false);
        this.viewVendas.getBtSair().setEnabled(false);
    }

    public void acaoBotaoAdcionarProduto() {
        if (validaTabela()) {
            countPagamento++;
            produto = listaProdutos.get(viewVendas.getCbProduto().getSelectedIndex() - Numeros.UM);
            valorTotal(produto);

            ItemDeVendaVO vo = new ItemDeVendaVO();
            vo.setProduto(produto);
            vo.setQuantidade(quantidade);
            vo.setValorTotal(valorTotal);
            vo.setDesconto(desconto);

            listaItemVenda.add(vo);
            listaProdutos.remove(produto);
            carregarTabela(vo);
            carregarProdutos();
            this.viewVendas.getTfQuantidade().setText(null);
            this.viewVendas.getTfDescontoProduto().setText(null);
            valorFinal += valorTotal;

            if (countPagamento == Numeros.UM) {
                this.viewVendas.getCbPagamento().setEnabled(true);
                this.viewVendas.getBtIncluirPagamento().setEnabled(true);
                this.viewVendas.getBtExcluirPagamento().setEnabled(false);
                this.viewVendas.getTfDescontoPagamento().setEditable(true);
            }
        }
    }

    public void acaoBotaoExcluirProduto() {
        if (this.viewVendas.getTbVendas().getSelectedRow() < Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneProduto);
        } else {
            countPagamento--;
            ItemDeVendaVO item = listaItemVenda.get(this.viewVendas.getTbVendas().getSelectedRow());
            listaItemVenda.remove(item);
            listaProdutos.add(item.getProduto());
            valorFinal -= valorTotal;
            carregarProdutos();
            atualizarTabela();
        }
    }

    public void acaoBotaoAdcionarPagamento() {
        if (validaTabelaPagamento()) {
            pagamento = viewVendas.getCbPagamento().getSelectedItem() + Constantes.VAZIO;
            listaPagamentoTabela.add(pagamento);
            carregarTabelaPagamento(pagamento);
            bloquearPagamento();
            if (!Valida.verificaStringVazio(this.viewVendas.getTfDescontoPagamento().getText())) {
                descontoPagamento = Double.parseDouble(this.viewVendas.getTfDescontoPagamento().getText());
            } else {
                descontoPagamento = Numeros.ZERO_DOUBLE;
            }

            mostrarValorfinal();
            this.viewVendas.getTfDescontoPagamento().setText(null);
        }
    }

    public void acaoBotaoExcluirPagamento() {
        if (this.viewVendas.getTbFormaPagamento().getSelectedRow() < Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneFormaDePagamento);
        } else {
            pagamento = listaPagamentoTabela.get(this.viewVendas.getTbFormaPagamento().getSelectedRow());
            listaPagamentoTabela.remove(pagamento);
            valorFinal += descontoPagamento;
            this.viewVendas.getLbValorTotal().setText(null);
            desbloquearPagamento();
            this.viewVendas.getCbPagamento().setSelectedIndex(Numeros.ZERO);
            atualizarTabelaPagamento();

        }
    }

    public void acaoBotaoSair() {
        this.viewVendas.setVisible(false);

    }

    public void acaoBotaoCancelar() {
        limparCampos();
        bloquearCampos();
        this.viewVendas.getCbFuncionario().setEnabled(true);
        this.viewVendas.getCbCliente().setEnabled(true);
        this.viewVendas.getBtSair().setEnabled(true);
        this.viewVendas.getLbValorTotal().setText(null);
    }

    public void acaoBotaoConfirmar() {
        if (validaConfirmar()) {
            if (this.aPrazo) {
                new VendaPrazoView();

            } else {
                Venda venda = new Venda();
                venda.setClienteIdCliente(listaClientes.get(this.viewVendas.getCbCliente().getSelectedIndex() - Numeros.UM));
                venda.setFuncionarioIdFuncionario(listaFuncionarios.get(this.viewVendas.getCbFuncionario().getSelectedIndex() - Numeros.UM));
                venda.setDataVenda(ServiceUtil.dataCompleta());
                venda.setFormaPagamento(pagamento);
                valorFinal -= descontoPagamento;
                DecimalFormat decimal = new DecimalFormat("0.00");
                venda.setValorTotal(Double.parseDouble(decimal.format(valorFinal)));
                salvar(venda);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_VENDA, FuncionarioConectado.funcionarioConectado);
                salvarItemVenda(venda);
                acaoBotaoCancelar();
            }
        }
    }

    private void salvarItemVenda(Venda venda) {
        for (ItemDeVendaVO item : listaItemVenda) {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProdutoIdProduto(item.getProduto());
            itemVenda.setDescontoProduto(item.getDesconto());
            itemVenda.setValorTotal(item.getValorTotal());
            itemVenda.setQuantidadeProduto(item.getQuantidade());
            itemVenda.setVendaIdVenda(venda);
            new ItemVendaController().salvar(itemVenda);
            new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_ITEM_VENDA, FuncionarioConectado.funcionarioConectado);
        }

    }

    public void salvar(Venda venda) {
        try {
            new VendaDAO().salvar(venda);
            JOptionPaneUtil.sucesso(Mensagem.vendaSalva);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bloquearCampos() {
        //bloqueio dos dados
        this.viewVendas.getCbFuncionario().setEnabled(true);
        this.viewVendas.getCbCliente().setEnabled(true);
        this.viewVendas.getBtIniciar().setEnabled(false);
        //bloqueio Produtos
        this.viewVendas.getCbProduto().setEnabled(false);
        this.viewVendas.getTfQuantidade().setEditable(false);
        this.viewVendas.getTfDescontoProduto().setEditable(true);
        this.viewVendas.getBtAdicionarProduto().setEnabled(false);
        this.viewVendas.getBtExcluirProduto().setEnabled(false);
        //bloqueio das formas de pagamento
        this.viewVendas.getCbPagamento().setEnabled(false);
        this.viewVendas.getBtIncluirPagamento().setEnabled(false);
        this.viewVendas.getBtExcluirPagamento().setEnabled(false);
        this.viewVendas.getTfDescontoPagamento().setVisible(false);
        this.viewVendas.getLbDesconto().setVisible(false);
        //bloquear botoes
        this.viewVendas.getBtConfirmar().setEnabled(false);
        this.viewVendas.getBtCancelar().setEnabled(false);
        this.viewVendas.getBtSair().setEnabled(true);
    }

    private void desbloquearCampos() {

        //bloqueio Produtos
        this.viewVendas.getCbProduto().setEnabled(true);
        this.viewVendas.getTfQuantidade().setEditable(true);
        this.viewVendas.getTfDescontoProduto().setEditable(true);
        this.viewVendas.getBtAdicionarProduto().setEnabled(true);
        this.viewVendas.getBtExcluirProduto().setEnabled(true);
        //bloqueio das formas de pagamento
        this.viewVendas.getCbPagamento().setEnabled(false);
        this.viewVendas.getBtIncluirPagamento().setEnabled(false);
        this.viewVendas.getBtExcluirPagamento().setEnabled(false);
        this.viewVendas.getTfDescontoPagamento().setEditable(true);
        //bloquear botoes
        this.viewVendas.getBtConfirmar().setEnabled(true);
        this.viewVendas.getBtCancelar().setEnabled(true);
        this.viewVendas.getBtSair().setEnabled(true);
    }

    private void desbloquearPagamento() {
        this.viewVendas.getCbPagamento().setEnabled(true);
        this.viewVendas.getBtIncluirPagamento().setEnabled(true);
        this.viewVendas.getBtExcluirPagamento().setEnabled(false);
        this.viewVendas.getTfDescontoPagamento().setEditable(true);
    }

    public void desbloquearDesconto() {
        if (this.viewVendas.getCbPagamento().getSelectedIndex() == Numeros.UM) {
            this.viewVendas.getTfDescontoPagamento().setVisible(true);
            this.viewVendas.getLbDesconto().setVisible(true);
            this.aPrazo = false;
        } else if (this.viewVendas.getCbPagamento().getSelectedIndex() == Numeros.DOIS) {
            this.aPrazo = true;
        } else {
            this.aPrazo = false;
            this.viewVendas.getTfDescontoPagamento().setVisible(false);
            this.viewVendas.getLbDesconto().setVisible(false);
        }
    }

    private void bloquearPagamento() {
        this.viewVendas.getCbPagamento().setEnabled(false);
        this.viewVendas.getBtIncluirPagamento().setEnabled(false);
        this.viewVendas.getBtExcluirPagamento().setEnabled(true);
        this.viewVendas.getTfDescontoPagamento().setEditable(false);

    }

    private void limparCampos() {
        //bloqueio dos dados
        this.viewVendas.getCbFuncionario().setSelectedIndex(Numeros.ZERO);
        this.viewVendas.getCbCliente().setSelectedIndex(Numeros.ZERO);
        //bloqueio Produtos
        this.viewVendas.getCbProduto().setSelectedIndex(Numeros.ZERO);
        this.viewVendas.getTfQuantidade().setText(null);
        this.viewVendas.getTfDescontoProduto().setText(null);
        //bloqueio das formas de pagamento
        this.viewVendas.getCbPagamento().setSelectedIndex(Numeros.ZERO);
        this.viewVendas.getTfDescontoPagamento().setText(null);
        DefaultTableModel modelo = (DefaultTableModel) this.viewVendas.getTbVendas().getModel();
        modelo.setRowCount(Numeros.ZERO);
        DefaultTableModel modeloVendas = (DefaultTableModel) this.viewVendas.getTbFormaPagamento().getModel();
        modeloVendas.setRowCount(Numeros.ZERO);
    }

    public void desbloqueioIniciar() {
        if (this.viewVendas.getCbFuncionario().getSelectedIndex() > Numeros.ZERO && this.viewVendas.getCbCliente().getSelectedIndex() > Numeros.ZERO) {
            this.viewVendas.getBtIniciar().setEnabled(true);
        } else {
            this.viewVendas.getBtIniciar().setEnabled(false);
        }
    }

    private boolean validaTabela() {
        if (this.viewVendas.getCbProduto().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneProduto);
            this.viewVendas.getCbProduto().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewVendas.getTfQuantidade().getText())) {
            JOptionPaneUtil.erro(Mensagem.InformeQuantidade);
            this.viewVendas.getTfQuantidade().grabFocus();
            return false;
        }

        return true;
    }

    private boolean validaTabelaPagamento() {
        if (this.viewVendas.getCbPagamento().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneFormaDePagamento);
            this.viewVendas.getCbProduto().grabFocus();
            return false;
        }

        return true;
    }

    private boolean validaConfirmar() {
        if (listaItemVenda.size() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeProduto);
            return false;
        }
        if (listaPagamentoTabela.size() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneFormaDePagamento);

        }

        return true;
    }

    public void mostrarValorfinal() {

        valorFinal -= descontoPagamento;

        this.viewVendas.getLbValorTotal().setText(ServiceUtil.formatarMoeda(valorFinal));
    }

}
