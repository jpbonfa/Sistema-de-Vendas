package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.FuncionarioDAO;
import br.com.jpbonfa.vendas.model.Cidade;
import br.com.jpbonfa.vendas.model.Contato;
import br.com.jpbonfa.vendas.model.Endereco;
import br.com.jpbonfa.vendas.model.Estado;
import br.com.jpbonfa.vendas.model.Funcionario;
import br.com.jpbonfa.vendas.model.PessoaFisica;
import br.com.jpbonfa.vendas.service.FuncionarioConectado;
import br.com.jpbonfa.vendas.util.Constantes;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.Numeros;
import br.com.jpbonfa.vendas.util.ServiceUtil;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.FuncionarioView;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joaop
 */
public class FuncionarioController {

    private FuncionarioView viewFuncionario;
    private ArrayList<Cidade> listaCidade;
    private ArrayList<Estado> listaEstado;
    private ArrayList<Funcionario> listaFuncionario;
    private Funcionario funcionario;
    private Contato contato;
    private Endereco endereco;
    private boolean alterar = false;

    public FuncionarioController() {
    }

    public FuncionarioController(FuncionarioView viewFuncionario) {
        this.viewFuncionario = viewFuncionario;
    }

    public void carregarCidade() {
        listaCidade = new CidadeController().buscarTodos();
        this.viewFuncionario.getCbCidade().removeAllItems();
        this.viewFuncionario.getCbCidade().addItem(Constantes.SELECIONE);
        for (Cidade cidade : listaCidade) {
            this.viewFuncionario.getCbCidade().addItem(cidade.getNome());
        }
    }

    public void carregarEstado() {
        listaEstado = new EstadoController().buscarTodos();
        this.viewFuncionario.getCbEstado().removeAllItems();
        this.viewFuncionario.getCbEstado().addItem(Constantes.SELECIONE);
        for (Estado estado : listaEstado) {
            this.viewFuncionario.getCbEstado().addItem(estado.getUf());
        }
    }

    public void carregarUf() {
        this.viewFuncionario.getCbEstado().setSelectedItem(listaCidade.get(this.viewFuncionario.getCbCidade().getSelectedIndex()).getEstadoIdEstado().getUf());
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFuncionario.getTbFuncionarios().getModel();
        modelo.setRowCount(Numeros.ZERO);
        for (Funcionario funcionario : listaFuncionario) {
            // Adicioando a linha com os dados
            modelo.addRow(new String[]{funcionario.getPessoaFisicaIdPessoaFisica().getNome(), funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getNome(), funcionario.getContatoIdContato().getTelefone(), funcionario.getContatoIdContato().getCelular()});
        }
    }

    public void listarFuncionario() {
        try {
            listaFuncionario = buscarTodos();
            carregarTabela();
        } catch (Exception e) {
            //tratar erro de listagem
        }
    }

    public ArrayList<Funcionario> buscarTodos() {
        return new FuncionarioDAO().buscarTodos();
    }

    public void acaoBotaoNovo() {
        desbloquearCampos();
        this.viewFuncionario.getBtNovo().setEnabled(false);
        this.viewFuncionario.getBtAlterar().setEnabled(false);
        this.viewFuncionario.getBtExcluir().setEnabled(false);
        this.viewFuncionario.getBtSair().setEnabled(false);
    }

    public void acaoBotaoAlterar() {
        if (this.viewFuncionario.getTbFuncionarios().getSelectedRow() < Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneFuncionario);
        } else {
            alterar = true;
            desbloqueioAlterar();
            funcionario = listaFuncionario.get(this.viewFuncionario.getTbFuncionarios().getSelectedRow());
            carregarTela();
        }

    }

    public void acaoBotaoExcluir() {
        if (this.viewFuncionario.getTbFuncionarios().getSelectedRow() < Numeros.ZERO) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneFuncionario, Mensagem.selecione, Numeros.ZERO);
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.desejaExcluirFuncionario);
            if (opcao == JOptionPane.YES_OPTION) {
                try {
                    funcionario = listaFuncionario.get(this.viewFuncionario.getTbFuncionarios().getSelectedRow());
                    //comandos de exclusao
                    excluir(funcionario);
                    new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_FUNCIONARIO, FuncionarioConectado.funcionarioConectado);

                    new EnderecoController().excluir(funcionario.getEnderecoIdEndereco());
                    new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_ENDERECO, FuncionarioConectado.funcionarioConectado);

                    new PessoaFisicaController().excluir(funcionario.getPessoaFisicaIdPessoaFisica());
                    new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_PESSOA_FISICA, FuncionarioConectado.funcionarioConectado);

                    new ContatoController().excluir(funcionario.getContatoIdContato());
                    new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_CONTATO, FuncionarioConectado.funcionarioConectado);
                    
                    JOptionPane.showMessageDialog(null, Mensagem.funcionarioExcluido, Mensagem.sucesso, Numeros.UM);
                    listarFuncionario();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }

    public void acaoBotaoSair() {
        this.viewFuncionario.setVisible(false);

    }

    public void acaoBotaoCancelar() {
        limparCampos();
        bloquearCampos();
        carregarTabela();
        this.viewFuncionario.getBtNovo().setEnabled(true);
        this.viewFuncionario.getBtAlterar().setEnabled(true);
        this.viewFuncionario.getBtExcluir().setEnabled(true);
        this.viewFuncionario.getBtSair().setEnabled(true);
        this.viewFuncionario.getBtSalvar().setEnabled(false);
        this.viewFuncionario.getBtCancelar().setEnabled(false);
        this.viewFuncionario.getBtSalvar().setEnabled(false);
        this.viewFuncionario.getBtCancelar().setEnabled(false);
    }

    public void acaoBotaoSalvar() {
        if (alterar) {
            if (validaAlterar()) {
                funcionario = listaFuncionario.get(this.viewFuncionario.getTbFuncionarios().getSelectedRow());
                endereco = funcionario.getEnderecoIdEndereco();
                contato = funcionario.getContatoIdContato();

                endereco.setEndereco(this.viewFuncionario.getTfLogradouro().getText());
                endereco.setNumero(Integer.parseInt(this.viewFuncionario.getTfNumero().getText()));
                endereco.setComplemento(this.viewFuncionario.getTfComplemento().getText());
                endereco.setBairro(this.viewFuncionario.getTfBairro().getText());
                endereco.setCep(this.viewFuncionario.getTfCep().getText());
                endereco.setCidadeIdCidade(listaCidade.get(this.viewFuncionario.getCbCidade().getSelectedIndex() - Numeros.UM));
                new EnderecoController().salvar(endereco);
                new LogUsuarioController().gerarLog(Constantes.UPDATE, Constantes.TABELA_ENDERECO, FuncionarioConectado.funcionarioConectado);

                contato.setTelefone(this.viewFuncionario.getTfTelefone().getText());
                contato.setCelular(this.viewFuncionario.getTfCelular().getText());
                contato.setEmail(this.viewFuncionario.getTfEmail().getText());
                new ContatoController().salvar(contato);
                new LogUsuarioController().gerarLog(Constantes.UPDATE, Constantes.TABELA_CONTATO, FuncionarioConectado.funcionarioConectado);

                funcionario.setSenha(this.viewFuncionario.getTfSenha().getText());
                salvar(funcionario);
                new LogUsuarioController().gerarLog(Constantes.UPDATE, Constantes.TABELA_FUNCIONARIO, FuncionarioConectado.funcionarioConectado);

                JOptionPaneUtil.sucesso(Mensagem.funcionarioAlterado);
                limparCampos();
                acaoBotaoCancelar();
                listarFuncionario();
            }
        } else {
            if (valida()) {
                Contato contato = new Contato();
                contato.setTelefone(this.viewFuncionario.getTfTelefone().getText());
                contato.setCelular(this.viewFuncionario.getTfCelular().getText());
                contato.setEmail(this.viewFuncionario.getTfEmail().getText());
                new ContatoController().salvar(contato);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_CONTATO, FuncionarioConectado.funcionarioConectado);

                Endereco endereco = new Endereco();
                endereco.setEndereco(this.viewFuncionario.getTfLogradouro().getText());
                endereco.setNumero(Integer.parseInt(this.viewFuncionario.getTfNumero().getText()));
                endereco.setComplemento(this.viewFuncionario.getTfComplemento().getText());
                endereco.setBairro(this.viewFuncionario.getTfBairro().getText());
                endereco.setCep(this.viewFuncionario.getTfCep().getText());
                endereco.setCidadeIdCidade(listaCidade.get(this.viewFuncionario.getCbCidade().getSelectedIndex() - Numeros.UM));
                new EnderecoController().salvar(endereco);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_ENDERECO, FuncionarioConectado.funcionarioConectado);
                PessoaFisica pf = new PessoaFisica();
                pf.setNome(this.viewFuncionario.getTfNome().getText());
                pf.setCpf(this.viewFuncionario.getTfCpf().getText());
                pf.setRg(this.viewFuncionario.getTfRg().getText());
                pf.setDataNascimento(this.viewFuncionario.getTfData().getText());
                new PessoaFisicaController().salvar(pf);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_PESSOA_FISICA, FuncionarioConectado.funcionarioConectado);

                Funcionario funcionario = new Funcionario();
                funcionario.setLogin(this.viewFuncionario.getTfLogin().getText());
                funcionario.setSenha(this.viewFuncionario.getTfSenha().getText());
                funcionario.setContatoIdContato(contato);
                funcionario.setEnderecoIdEndereco(endereco);
                funcionario.setPessoaFisicaIdPessoaFisica(pf);
                salvar(funcionario);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_FUNCIONARIO, FuncionarioConectado.funcionarioConectado);
                JOptionPaneUtil.sucesso(Mensagem.funcionarioSalvo);
                limparCampos();
                acaoBotaoCancelar();
                listarFuncionario();

            }
        }
    }

    public void salvar(Funcionario funcionario) {
        try {
            new FuncionarioDAO().salvar(funcionario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(Funcionario funcionario) {
        try {
            new FuncionarioDAO().excluir(funcionario);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Mensagem.funcionarioErroExcluir, Mensagem.erro, Numeros.ZERO);
            e.printStackTrace();
        }
    }

    public void bloquearCampos() {
        this.viewFuncionario.getTfCpf().setEditable(false);
        this.viewFuncionario.getTfRg().setEditable(false);
        this.viewFuncionario.getTfNome().setEditable(false);
        this.viewFuncionario.getTfData().setEditable(false);
        this.viewFuncionario.getTfLogradouro().setEditable(false);
        this.viewFuncionario.getTfNumero().setEditable(false);
        this.viewFuncionario.getTfComplemento().setEditable(false);
        this.viewFuncionario.getTfBairro().setEditable(false);
        this.viewFuncionario.getCbCidade().setEnabled(false);
        this.viewFuncionario.getCbEstado().setEnabled(false);
        this.viewFuncionario.getTfCep().setEditable(false);
        this.viewFuncionario.getTfTelefone().setEditable(false);
        this.viewFuncionario.getTfCelular().setEditable(false);
        this.viewFuncionario.getTfEmail().setEditable(false);
        this.viewFuncionario.getTfLogin().setEditable(false);
        this.viewFuncionario.getTfSenha().setEditable(false);
        this.viewFuncionario.getBtSalvar().setEnabled(false);
        this.viewFuncionario.getBtCancelar().setEnabled(false);
    }

    public void desbloquearCampos() {
        this.viewFuncionario.getTfCpf().setEditable(true);
        this.viewFuncionario.getTfRg().setEditable(true);
        this.viewFuncionario.getTfNome().setEditable(true);
        this.viewFuncionario.getTfData().setEditable(true);
        this.viewFuncionario.getTfLogradouro().setEditable(true);
        this.viewFuncionario.getTfNumero().setEditable(true);
        this.viewFuncionario.getTfComplemento().setEditable(true);
        this.viewFuncionario.getTfBairro().setEditable(true);
        this.viewFuncionario.getCbCidade().setEnabled(true);
        this.viewFuncionario.getCbEstado().setEnabled(true);
        this.viewFuncionario.getTfCep().setEditable(true);
        this.viewFuncionario.getTfTelefone().setEditable(true);
        this.viewFuncionario.getTfCelular().setEditable(true);
        this.viewFuncionario.getTfEmail().setEditable(true);
        this.viewFuncionario.getTfLogin().setEditable(true);
        this.viewFuncionario.getTfSenha().setEditable(true);
        this.viewFuncionario.getTfCpf().grabFocus();
        this.viewFuncionario.getBtSalvar().setEnabled(true);
        this.viewFuncionario.getBtCancelar().setEnabled(true);
    }

    private void limparCampos() {
        this.viewFuncionario.getTfCpf().setValue(null);
        this.viewFuncionario.getTfRg().setValue(null);
        this.viewFuncionario.getTfNome().setText(null);
        this.viewFuncionario.getTfData().setValue(null);
        this.viewFuncionario.getTfLogradouro().setText(null);
        this.viewFuncionario.getTfNumero().setText(null);
        this.viewFuncionario.getTfComplemento().setText(null);
        this.viewFuncionario.getTfBairro().setText(null);
        this.viewFuncionario.getCbCidade().setSelectedIndex(Numeros.ZERO);
        this.viewFuncionario.getCbEstado().setSelectedIndex(Numeros.ZERO);
        this.viewFuncionario.getTfCep().setValue(null);
        this.viewFuncionario.getTfTelefone().setValue(null);
        this.viewFuncionario.getTfCelular().setValue(null);
        this.viewFuncionario.getTfEmail().setText(null);
        this.viewFuncionario.getTfLogin().setText(null);;
        this.viewFuncionario.getTfSenha().setText(null);
    }

    private void desbloqueioAlterar() {
        desbloquearCampos();
        this.viewFuncionario.getTfLogin().setEditable(false);
        this.viewFuncionario.getTfCpf().setEditable(false);
        this.viewFuncionario.getTfRg().setEditable(false);
        this.viewFuncionario.getTfNome().setEditable(false);
        this.viewFuncionario.getTfData().setEditable(false);
        this.viewFuncionario.getBtNovo().setEnabled(false);
        this.viewFuncionario.getBtAlterar().setEnabled(false);
        this.viewFuncionario.getBtExcluir().setEnabled(false);
        this.viewFuncionario.getBtSair().setEnabled(false);
        this.viewFuncionario.getBtSalvar().setEnabled(true);
        this.viewFuncionario.getBtCancelar().setEnabled(true);

    }

    private boolean valida() {
        if (Valida.verificaCpfVazio(this.viewFuncionario.getTfCpf().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCpf);
            this.viewFuncionario.getTfCpf().grabFocus();
            return false;
        } else if (!Valida.validarCpf((this.viewFuncionario.getTfCpf().getText()))) {
            JOptionPaneUtil.erro(Mensagem.informeCpfValido);
            this.viewFuncionario.getTfCpf().grabFocus();
            return false;
        }

        if (Valida.verificaRgVazio(this.viewFuncionario.getTfRg().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeRg);
            this.viewFuncionario.getTfRg().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewFuncionario.getTfNome().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNome);
            this.viewFuncionario.getTfNome().grabFocus();
            return false;
        } else if (Valida.apenasLetras(this.viewFuncionario.getTfNome().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNomeValido);
            this.viewFuncionario.getTfNome().grabFocus();
            return false;
        }
        if (Valida.verificaDataVazio(this.viewFuncionario.getTfData().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeData);
            this.viewFuncionario.getTfData().grabFocus();
            return false;

        } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewFuncionario.getTfData().getText()))) {
            JOptionPaneUtil.erro(Mensagem.DataInvalida);
            this.viewFuncionario.getTfData().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewFuncionario.getTfLogradouro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogradouro);
            this.viewFuncionario.getTfLogradouro().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewFuncionario.getTfNumero().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNumero);
            this.viewFuncionario.getTfNumero().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewFuncionario.getTfBairro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeBairro);
            this.viewFuncionario.getTfBairro().grabFocus();
            return false;
        }
        if (this.viewFuncionario.getCbCidade().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeCidade);
            this.viewFuncionario.getCbCidade().grabFocus();
            return false;
        }
        if (this.viewFuncionario.getCbEstado().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeEstado);
            this.viewFuncionario.getCbEstado().grabFocus();
            return false;
        }
        if (Valida.verificaCepVazio(this.viewFuncionario.getTfCep().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCep);
            this.viewFuncionario.getTfCep().grabFocus();
            return false;
        }

        if (Valida.verificaTelefoneVazio(this.viewFuncionario.getTfTelefone().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeTelefone);
            this.viewFuncionario.getTfTelefone().grabFocus();
            return false;
        }
        if (Valida.verificaCelularVazio(this.viewFuncionario.getTfCelular().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCelular);
            this.viewFuncionario.getTfCelular().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewFuncionario.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmail);
            this.viewFuncionario.getTfEmail().grabFocus();
            return false;
        } else if (!Valida.validaEmail(this.viewFuncionario.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmailValido);
            this.viewFuncionario.getTfEmail().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewFuncionario.getTfLogin().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogin);
            this.viewFuncionario.getTfLogin().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewFuncionario.getTfSenha().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogin);
            this.viewFuncionario.getTfSenha().grabFocus();
            return false;
        }
        return true;
    }

    private boolean validaAlterar() {

        if (Valida.verificaStringVazio(this.viewFuncionario.getTfLogradouro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogradouro);
            this.viewFuncionario.getTfLogradouro().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewFuncionario.getTfNumero().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNumero);
            this.viewFuncionario.getTfNumero().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewFuncionario.getTfBairro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeBairro);
            this.viewFuncionario.getTfBairro().grabFocus();
            return false;
        }
        if (this.viewFuncionario.getCbCidade().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeCidade);
            this.viewFuncionario.getCbCidade().grabFocus();
            return false;
        }
        if (this.viewFuncionario.getCbEstado().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeEstado);
            this.viewFuncionario.getCbEstado().grabFocus();
            return false;
        }
        if (Valida.verificaCepVazio(this.viewFuncionario.getTfCep().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCep);
            this.viewFuncionario.getTfCep().grabFocus();
            return false;
        }

        if (Valida.verificaTelefoneVazio(this.viewFuncionario.getTfTelefone().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeTelefone);
            this.viewFuncionario.getTfTelefone().grabFocus();
            return false;
        }
        if (Valida.verificaCelularVazio(this.viewFuncionario.getTfCelular().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCelular);
            this.viewFuncionario.getTfCelular().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewFuncionario.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmail);
            this.viewFuncionario.getTfEmail().grabFocus();
            return false;
        } else if (!Valida.validaEmail(this.viewFuncionario.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmailValido);
            this.viewFuncionario.getTfEmail().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewFuncionario.getTfLogin().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogin);
            this.viewFuncionario.getTfLogin().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewFuncionario.getTfSenha().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogin);
            this.viewFuncionario.getTfSenha().grabFocus();
            return false;
        }
        return true;
    }

    public void carregarTela() {
        this.viewFuncionario.getTfCpf().setText(funcionario.getPessoaFisicaIdPessoaFisica().getCpf());
        this.viewFuncionario.getTfRg().setText(funcionario.getPessoaFisicaIdPessoaFisica().getRg());
        this.viewFuncionario.getTfNome().setText(funcionario.getPessoaFisicaIdPessoaFisica().getNome());
        this.viewFuncionario.getTfData().setText(funcionario.getPessoaFisicaIdPessoaFisica().getDataNascimento());
        this.viewFuncionario.getTfLogradouro().setText(funcionario.getEnderecoIdEndereco().getEndereco());
        this.viewFuncionario.getTfNumero().setText(funcionario.getEnderecoIdEndereco().getNumero() + Constantes.VAZIO);
        this.viewFuncionario.getTfComplemento().setText(funcionario.getEnderecoIdEndereco().getComplemento());
        this.viewFuncionario.getTfBairro().setText(funcionario.getEnderecoIdEndereco().getBairro());
        this.viewFuncionario.getCbCidade().setSelectedItem(funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
        this.viewFuncionario.getCbEstado().setSelectedItem(funcionario.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getUf());
        this.viewFuncionario.getTfCep().setText(funcionario.getEnderecoIdEndereco().getCep());
        this.viewFuncionario.getTfTelefone().setText(funcionario.getContatoIdContato().getTelefone());
        this.viewFuncionario.getTfCelular().setText(funcionario.getContatoIdContato().getCelular());
        this.viewFuncionario.getTfEmail().setText(funcionario.getContatoIdContato().getEmail());
        this.viewFuncionario.getTfLogin().setText(funcionario.getLogin());
        this.viewFuncionario.getTfSenha().setText(funcionario.getSenha());

    }

}
