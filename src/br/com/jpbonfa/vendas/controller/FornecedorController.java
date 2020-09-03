package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.FornecedorDAO;
import br.com.jpbonfa.vendas.model.Cidade;
import br.com.jpbonfa.vendas.model.Contato;
import br.com.jpbonfa.vendas.model.Endereco;
import br.com.jpbonfa.vendas.model.Estado;
import br.com.jpbonfa.vendas.model.Fornecedor;
import br.com.jpbonfa.vendas.model.PessoaJuridica;
import br.com.jpbonfa.vendas.service.FuncionarioConectado;
import br.com.jpbonfa.vendas.util.Constantes;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.Numeros;
import br.com.jpbonfa.vendas.util.ServiceUtil;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.FornecedorView;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joaop
 */
public class FornecedorController {

    private FornecedorView viewfornecedor;
    private ArrayList<Cidade> listaCidade;
    private ArrayList<Estado> listaEstado;
    private ArrayList<Fornecedor> listaFornecedor;
    private Fornecedor fornecedor;
    private Endereco endereco;
    private Contato contato;
    private boolean alterar = false;

    public FornecedorController() {
    }

    public FornecedorController(FornecedorView viewfornecedor) {
        this.viewfornecedor = viewfornecedor;
    }

    public void carregarCidade() {
        listaCidade = new CidadeController().buscarTodos();
        this.viewfornecedor.getCbCidade().removeAllItems();
        this.viewfornecedor.getCbCidade().addItem(Constantes.SELECIONE);
        for (Cidade cidade : listaCidade) {
            this.viewfornecedor.getCbCidade().addItem(cidade.getNome());
        }
    }

    public void carregarEstado() {
        listaEstado = new EstadoController().buscarTodos();
        this.viewfornecedor.getCbEstado().removeAllItems();
        this.viewfornecedor.getCbEstado().addItem(Constantes.SELECIONE);
        for (Estado estado : listaEstado) {
            this.viewfornecedor.getCbEstado().addItem(estado.getUf());
        }
    }

    public void carregarUf() {
        this.viewfornecedor.getCbEstado().setSelectedItem(listaCidade.get(this.viewfornecedor.getCbCidade().getSelectedIndex()).getEstadoIdEstado().getUf());
    }

    private void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewfornecedor.getTbFornecedores().getModel();
        modelo.setRowCount(Numeros.ZERO);
        for (Fornecedor fornecedor : listaFornecedor) {
            // Adicioando a linha com os dados
            modelo.addRow(new String[]{fornecedor.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial(), fornecedor.getContatoIdContato().getTelefone(), fornecedor.getContato(), fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getNome()});
        }
    }

    public void listarFornecedor() {
        try {
            listaFornecedor = buscarTodos();
            carregarTabela();
        } catch (Exception e) {
            //tratar erro de listagem
        }
    }

    public ArrayList<Fornecedor> buscarTodos() {
        return new FornecedorDAO().buscarTodos();
    }

    public void acaoBotaoNovo() {
        desbloquearCampos();
        this.viewfornecedor.getBtNovo().setEnabled(false);
        this.viewfornecedor.getBtAlterar().setEnabled(false);
        this.viewfornecedor.getBtExcluir().setEnabled(false);
        this.viewfornecedor.getBtSair().setEnabled(false);
        this.viewfornecedor.getBtSalvar().setEnabled(true);
        this.viewfornecedor.getBtCancelar().setEnabled(true);
    }

    public void acaoBotaoAlterar() {
        if (this.viewfornecedor.getTbFornecedores().getSelectedRow() < Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneFornecedor);
        } else {
            alterar = true;
            fornecedor = listaFornecedor.get(this.viewfornecedor.getTbFornecedores().getSelectedRow());
            carregarTela();
            desbloqueioAlterar();
        }
    }

    public void acaoBotaoExcluir() {
        if (this.viewfornecedor.getTbFornecedores().getSelectedRow() < Numeros.ZERO) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneFornecedor, Mensagem.selecione, Numeros.ZERO);
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.desejaExcluirFornecedor);
            if (opcao == JOptionPane.YES_OPTION) {

                fornecedor = listaFornecedor.get(this.viewfornecedor.getTbFornecedores().getSelectedRow());
                //comandos de exclusao
                excluir(fornecedor);
                new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_FORNECEDOR, FuncionarioConectado.funcionarioConectado);

                new PessoaJuridicaController().excluir(fornecedor.getPessoaJuridicaIdPessoaJuridica());
                new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_PESSOA_JURIDICA, FuncionarioConectado.funcionarioConectado);
                new EnderecoController().excluir(fornecedor.getEnderecoIdEndereco());
                new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_ENDERECO, FuncionarioConectado.funcionarioConectado);
                new ContatoController().excluir(fornecedor.getContatoIdContato());
                new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_CONTATO, FuncionarioConectado.funcionarioConectado);

                JOptionPane.showMessageDialog(null, Mensagem.fornecedorExcluido, Mensagem.sucesso, Numeros.UM);
                listarFornecedor();

            }
        }
    }

    public void excluir(Fornecedor fornecedor) {
        try {
            new FornecedorDAO().excluir(fornecedor);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Mensagem.fornecedorErroExcluir, Mensagem.erro, Numeros.ZERO);
            e.printStackTrace();

        }
    }

    public void acaoBotaoSair() {
        this.viewfornecedor.setVisible(false);

    }

    public void acaoBotaoCancelar() {
        limparCampos();
        bloquearCampos();
        this.viewfornecedor.getBtNovo().setEnabled(true);
        this.viewfornecedor.getBtAlterar().setEnabled(true);
        this.viewfornecedor.getBtExcluir().setEnabled(true);
        this.viewfornecedor.getBtSair().setEnabled(true);
        this.viewfornecedor.getBtSalvar().setEnabled(false);
        this.viewfornecedor.getBtCancelar().setEnabled(false);
        carregarTabela();
    }

    public void acaoBotaoSalvar() {
        if (alterar) {
            if (validaAlterar()) {
                //alterar um Fornecedor
                fornecedor = listaFornecedor.get(this.viewfornecedor.getTbFornecedores().getSelectedRow());
                endereco = fornecedor.getEnderecoIdEndereco();
                contato = fornecedor.getContatoIdContato();

                endereco.setEndereco(this.viewfornecedor.getTfLogradouro().getText());
                endereco.setNumero(Integer.parseInt(this.viewfornecedor.getTfNumero().getText()));
                endereco.setComplemento(this.viewfornecedor.getTfComplemento().getText());
                endereco.setBairro(this.viewfornecedor.getTfBairro().getText());
                endereco.setCep(this.viewfornecedor.getTfCep().getText());
                endereco.setCidadeIdCidade(listaCidade.get(this.viewfornecedor.getCbCidade().getSelectedIndex() - Numeros.UM));
                new EnderecoController().salvar(endereco);
                new LogUsuarioController().gerarLog(Constantes.UPDATE, Constantes.TABELA_ENDERECO, FuncionarioConectado.funcionarioConectado);

                contato.setTelefone(this.viewfornecedor.getTfTelefone().getText());
                contato.setCelular(this.viewfornecedor.getTfCelular().getText());
                contato.setEmail(this.viewfornecedor.getTfEmail().getText());
                new ContatoController().salvar(contato);
                new LogUsuarioController().gerarLog(Constantes.UPDATE, Constantes.TABELA_CONTATO, FuncionarioConectado.funcionarioConectado);

                fornecedor.setContato(this.viewfornecedor.getTfContato().getText());
                salvar(fornecedor);
                new LogUsuarioController().gerarLog(Constantes.UPDATE, Constantes.TABELA_FORNECEDOR, FuncionarioConectado.funcionarioConectado);

                JOptionPaneUtil.sucesso(Mensagem.fornecedorAlterado);
                limparCampos();
                acaoBotaoCancelar();
                listarFornecedor();

            }
        } else {
            if (valida()) {
                Contato contato = new Contato();
                contato.setTelefone(this.viewfornecedor.getTfTelefone().getText());
                contato.setCelular(this.viewfornecedor.getTfCelular().getText());
                contato.setEmail(this.viewfornecedor.getTfEmail().getText());
                new ContatoController().salvar(contato);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_CONTATO, FuncionarioConectado.funcionarioConectado);

                Endereco endereco = new Endereco();
                endereco.setEndereco(this.viewfornecedor.getTfLogradouro().getText());
                endereco.setNumero(Integer.parseInt(this.viewfornecedor.getTfNumero().getText()));
                endereco.setComplemento(this.viewfornecedor.getTfComplemento().getText());
                endereco.setBairro(this.viewfornecedor.getTfBairro().getText());
                endereco.setCep(this.viewfornecedor.getTfCep().getText());
                endereco.setCidadeIdCidade(listaCidade.get(this.viewfornecedor.getCbCidade().getSelectedIndex() - Numeros.UM));
                new EnderecoController().salvar(endereco);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_ENDERECO, FuncionarioConectado.funcionarioConectado);

                PessoaJuridica pj = new PessoaJuridica();
                pj.setRazaoSocial(this.viewfornecedor.getTfRazao().getText());
                pj.setCnpj(this.viewfornecedor.getTfCnpj().getText());
                pj.setInscricaoEstadual(this.viewfornecedor.getTfInscricao().getText());
                pj.setDataFundacao(this.viewfornecedor.getTfData().getText());
                new PessoaJuridicaController().salvar(pj);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_PESSOA_JURIDICA, FuncionarioConectado.funcionarioConectado);

                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setContatoIdContato(contato);
                fornecedor.setEnderecoIdEndereco(endereco);
                fornecedor.setPessoaJuridicaIdPessoaJuridica(pj);
                fornecedor.setContato(this.viewfornecedor.getTfContato().getText());
                salvar(fornecedor);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_FORNECEDOR, FuncionarioConectado.funcionarioConectado);

                JOptionPaneUtil.sucesso(Mensagem.fornecedorSalvo);
                limparCampos();
                acaoBotaoCancelar();
                listarFornecedor();
            }
        }
    }

    public void salvar(Fornecedor fornecedor) {
        try {
            new FornecedorDAO().salvar(fornecedor);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void bloquearCampos() {
        this.viewfornecedor.getTfCnpj().setEditable(false);
        this.viewfornecedor.getTfInscricao().setEditable(false);
        this.viewfornecedor.getTfRazao().setEditable(false);
        this.viewfornecedor.getTfData().setEditable(false);
        this.viewfornecedor.getTfLogradouro().setEditable(false);
        this.viewfornecedor.getTfNumero().setEditable(false);
        this.viewfornecedor.getTfComplemento().setEditable(false);
        this.viewfornecedor.getTfBairro().setEditable(false);
        this.viewfornecedor.getCbCidade().setEnabled(false);
        this.viewfornecedor.getCbEstado().setEnabled(false);
        this.viewfornecedor.getTfCep().setEditable(false);
        this.viewfornecedor.getTfTelefone().setEditable(false);
        this.viewfornecedor.getTfCelular().setEditable(false);
        this.viewfornecedor.getTfEmail().setEditable(false);
        this.viewfornecedor.getTfContato().setEditable(false);
        this.viewfornecedor.getBtSalvar().setEnabled(false);
        this.viewfornecedor.getBtCancelar().setEnabled(false);

    }

    private void desbloquearCampos() {
        this.viewfornecedor.getTfCnpj().setEditable(true);
        this.viewfornecedor.getTfInscricao().setEditable(true);
        this.viewfornecedor.getTfRazao().setEditable(true);
        this.viewfornecedor.getTfData().setEditable(true);
        this.viewfornecedor.getTfLogradouro().setEditable(true);
        this.viewfornecedor.getTfNumero().setEditable(true);
        this.viewfornecedor.getTfComplemento().setEditable(true);
        this.viewfornecedor.getTfBairro().setEditable(true);
        this.viewfornecedor.getCbCidade().setEnabled(true);
        this.viewfornecedor.getCbEstado().setEnabled(true);
        this.viewfornecedor.getTfCep().setEditable(true);
        this.viewfornecedor.getTfTelefone().setEditable(true);
        this.viewfornecedor.getTfCelular().setEditable(true);
        this.viewfornecedor.getTfEmail().setEditable(true);
        this.viewfornecedor.getTfContato().setEditable(true);
        this.viewfornecedor.getBtSalvar().setEnabled(true);
        this.viewfornecedor.getBtCancelar().setEnabled(true);
        this.viewfornecedor.getTfCnpj().grabFocus();

    }

    private void limparCampos() {
        this.viewfornecedor.getTfCnpj().setValue(null);
        this.viewfornecedor.getTfInscricao().setValue(null);
        this.viewfornecedor.getTfRazao().setText(null);
        this.viewfornecedor.getTfData().setValue(null);
        this.viewfornecedor.getTfLogradouro().setText(null);
        this.viewfornecedor.getTfNumero().setText(null);
        this.viewfornecedor.getTfComplemento().setText(null);
        this.viewfornecedor.getTfBairro().setText(null);
        this.viewfornecedor.getCbCidade().setSelectedIndex(Numeros.ZERO);
        this.viewfornecedor.getCbEstado().setSelectedIndex(Numeros.ZERO);
        this.viewfornecedor.getTfCep().setValue(null);
        this.viewfornecedor.getTfTelefone().setValue(null);
        this.viewfornecedor.getTfCelular().setValue(null);
        this.viewfornecedor.getTfEmail().setText(null);
        this.viewfornecedor.getTfContato().setText(null);

    }

    private void desbloqueioAlterar() {
        desbloquearCampos();
        this.viewfornecedor.getTfCnpj().setEditable(false);
        this.viewfornecedor.getTfInscricao().setEditable(false);
        this.viewfornecedor.getTfRazao().setEditable(false);
        this.viewfornecedor.getTfData().setEditable(false);
        this.viewfornecedor.getBtNovo().setEnabled(false);
        this.viewfornecedor.getBtAlterar().setEnabled(false);
        this.viewfornecedor.getBtExcluir().setEnabled(false);
        this.viewfornecedor.getBtSair().setEnabled(false);
        this.viewfornecedor.getBtSalvar().setEnabled(true);
        this.viewfornecedor.getBtCancelar().setEnabled(true);

    }

    private boolean valida() {

        if (Valida.verificaCnpjVazio(this.viewfornecedor.getTfCnpj().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCnpj);
            this.viewfornecedor.getTfCnpj().grabFocus();
            return false;
        }
        if (Valida.verificaIeVazio(this.viewfornecedor.getTfInscricao().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeIe);
            this.viewfornecedor.getTfInscricao().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewfornecedor.getTfRazao().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeRazao);
            this.viewfornecedor.getTfRazao().grabFocus();
            return false;
        }
        if (Valida.verificaDataVazio(this.viewfornecedor.getTfData().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeDataFundacao);
            this.viewfornecedor.getTfData().grabFocus();
            return false;

        } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewfornecedor.getTfData().getText()))) {
            JOptionPaneUtil.erro(Mensagem.DataFundacaoInvalida);
            this.viewfornecedor.getTfData().grabFocus();
            return false;
        }

        if (Valida.verificaStringVazio(this.viewfornecedor.getTfLogradouro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogradouro);
            this.viewfornecedor.getTfLogradouro().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewfornecedor.getTfNumero().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNumero);
            this.viewfornecedor.getTfNumero().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewfornecedor.getTfBairro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeBairro);
            this.viewfornecedor.getTfBairro().grabFocus();
            return false;
        }
        if (this.viewfornecedor.getCbCidade().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeCidade);
            this.viewfornecedor.getCbCidade().grabFocus();
            return false;
        }
        if (this.viewfornecedor.getCbEstado().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeEstado);
            this.viewfornecedor.getCbEstado().grabFocus();
            return false;
        }
        if (Valida.verificaCepVazio(this.viewfornecedor.getTfCep().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCep);
            this.viewfornecedor.getTfCep().grabFocus();
            return false;
        }
        if (Valida.verificaTelefoneVazio(this.viewfornecedor.getTfTelefone().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeTelefone);
            this.viewfornecedor.getTfTelefone().grabFocus();
            return false;
        }
        if (Valida.verificaCelularVazio(this.viewfornecedor.getTfCelular().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCelular);
            this.viewfornecedor.getTfCelular().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewfornecedor.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmail);
            this.viewfornecedor.getTfEmail().grabFocus();
            return false;
        } else if (!Valida.validaEmail(this.viewfornecedor.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmailValido);
            this.viewfornecedor.getTfEmail().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewfornecedor.getTfContato().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeContato);
            this.viewfornecedor.getTfContato().grabFocus();
            return false;
        } else if (Valida.apenasLetras(this.viewfornecedor.getTfContato().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeContatoValido);
            this.viewfornecedor.getTfContato().grabFocus();
            return false;
        }

        return true;
    }

    private boolean validaAlterar() {
        if (Valida.verificaStringVazio(this.viewfornecedor.getTfLogradouro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogradouro);
            this.viewfornecedor.getTfLogradouro().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewfornecedor.getTfNumero().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNumero);
            this.viewfornecedor.getTfNumero().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewfornecedor.getTfBairro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeBairro);
            this.viewfornecedor.getTfBairro().grabFocus();
            return false;
        }
        if (this.viewfornecedor.getCbCidade().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeCidade);
            this.viewfornecedor.getCbCidade().grabFocus();
            return false;
        }
        if (this.viewfornecedor.getCbEstado().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeEstado);
            this.viewfornecedor.getCbEstado().grabFocus();
            return false;
        }
        if (Valida.verificaCepVazio(this.viewfornecedor.getTfCep().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCep);
            this.viewfornecedor.getTfCep().grabFocus();
            return false;
        }
        if (Valida.verificaTelefoneVazio(this.viewfornecedor.getTfTelefone().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeTelefone);
            this.viewfornecedor.getTfTelefone().grabFocus();
            return false;
        }
        if (Valida.verificaCelularVazio(this.viewfornecedor.getTfCelular().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCelular);
            this.viewfornecedor.getTfCelular().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewfornecedor.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmail);
            this.viewfornecedor.getTfEmail().grabFocus();
            return false;
        } else if (!Valida.validaEmail(this.viewfornecedor.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmailValido);
            this.viewfornecedor.getTfEmail().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewfornecedor.getTfContato().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeContato);
            this.viewfornecedor.getTfContato().grabFocus();
            return false;
        } else if (Valida.apenasLetras(this.viewfornecedor.getTfContato().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeContatoValido);
            this.viewfornecedor.getTfContato().grabFocus();
            return false;
        }

        return true;

    }

    private void carregarTela() {
        this.viewfornecedor.getTfCnpj().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getCnpj());
        this.viewfornecedor.getTfInscricao().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getInscricaoEstadual());
        this.viewfornecedor.getTfRazao().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
        this.viewfornecedor.getTfData().setText(fornecedor.getPessoaJuridicaIdPessoaJuridica().getDataFundacao());
        this.viewfornecedor.getTfLogradouro().setText(fornecedor.getEnderecoIdEndereco().getEndereco());
        this.viewfornecedor.getTfNumero().setText(fornecedor.getEnderecoIdEndereco().getNumero() + Constantes.VAZIO);
        this.viewfornecedor.getTfComplemento().setText(fornecedor.getEnderecoIdEndereco().getComplemento());
        this.viewfornecedor.getTfBairro().setText(fornecedor.getEnderecoIdEndereco().getBairro());
        this.viewfornecedor.getCbCidade().setSelectedItem(fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
        this.viewfornecedor.getCbEstado().setSelectedItem(fornecedor.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getUf());
        this.viewfornecedor.getTfCep().setText(fornecedor.getEnderecoIdEndereco().getCep());
        this.viewfornecedor.getTfTelefone().setText(fornecedor.getContatoIdContato().getTelefone());
        this.viewfornecedor.getTfCelular().setText(fornecedor.getContatoIdContato().getCelular());
        this.viewfornecedor.getTfEmail().setText(fornecedor.getContatoIdContato().getEmail());
        this.viewfornecedor.getTfContato().setText(fornecedor.getContato());
    }

}
