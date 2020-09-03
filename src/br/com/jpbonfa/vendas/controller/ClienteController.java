package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.ClienteDAO;
import br.com.jpbonfa.vendas.model.Cidade;
import br.com.jpbonfa.vendas.model.Cliente;
import br.com.jpbonfa.vendas.model.Contato;
import br.com.jpbonfa.vendas.model.Endereco;
import br.com.jpbonfa.vendas.model.Estado;
import br.com.jpbonfa.vendas.model.PessoaFisica;
import br.com.jpbonfa.vendas.model.PessoaJuridica;
import br.com.jpbonfa.vendas.service.FuncionarioConectado;
import br.com.jpbonfa.vendas.util.Constantes;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.Numeros;
import br.com.jpbonfa.vendas.util.ServiceUtil;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.ClienteView;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author joaop
 */
public class ClienteController {

    private ClienteView viewCliente;
    private MaskFormatter CNPJMask;
    private MaskFormatter CPFMask;
    private MaskFormatter RGMask;
    private MaskFormatter IEMask;
    private ArrayList<Cidade> listaCidade;
    private ArrayList<Estado> listaEstado;
    private ArrayList<Cliente> listaCliente;
    private Cliente cliente;
    private Contato contato;
    private Endereco endereco;
    private boolean alterar = false;

    public ClienteController() {

    }

    public ClienteController(ClienteView viewCliente) {
        this.viewCliente = viewCliente;
    }

    public void carregarCidade() {
        listaCidade = new CidadeController().buscarTodos();
        this.viewCliente.getCbCidade().removeAllItems();
        this.viewCliente.getCbCidade().addItem(Constantes.SELECIONE);
        for (Cidade cidade : listaCidade) {
            this.viewCliente.getCbCidade().addItem(cidade.getNome());
        }
    }

    public void carregarEstado() {
        listaEstado = new EstadoController().buscarTodos();
        this.viewCliente.getCbEstado().removeAllItems();
        this.viewCliente.getCbEstado().addItem(Constantes.SELECIONE);
        for (Estado estado : listaEstado) {
            this.viewCliente.getCbEstado().addItem(estado.getUf());
        }
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCliente.getTbCliente().getModel();
        modelo.setRowCount(Numeros.ZERO);
        for (Cliente cliente : listaCliente) {
            if (cliente.getTipoPessoa().equals(Constantes.LETRA_F)) {
                modelo.addRow(new String[]{cliente.getPessoaFisicaIdPessoaFisica().getNome(), cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome(), cliente.getContatoIdContato().getCelular(), cliente.getContatoIdContato().getEmail() + Constantes.VAZIO,});
            } else {
                modelo.addRow(new String[]{cliente.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial(), cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome(), cliente.getContatoIdContato().getCelular(), cliente.getContatoIdContato().getEmail() + Constantes.VAZIO,});
            }

        }
    }

    public void listarCliente() {
        try {
            listaCliente = buscarTodos();
            carregarTabela();
        } catch (Exception e) {
            //tratar erro de listagem
        }
    }

    public ArrayList<Cliente> buscarTodos() {
        return new ClienteDAO().buscarTodos();
    }

    public void acaoBotaoNovo() {
        desbloquearCampos();
        this.viewCliente.getRbFisico().setSelected(true);
        this.viewCliente.getBtNovo().setEnabled(false);
        this.viewCliente.getBtAlterar().setEnabled(false);
        this.viewCliente.getBtExcluir().setEnabled(false);
        this.viewCliente.getBtSair().setEnabled(false);

    }

    public void acaoBotaoAlterar() {

        if (this.viewCliente.getTbCliente().getSelectedRow() < Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.selecioneCliente);
        } else {
            alterar = true;
            cliente = listaCliente.get(this.viewCliente.getTbCliente().getSelectedRow());
            carregarTela();
            desbloqueioAlterar();
        }

    }

    public void acaoBotaoExcluir() {
        if (this.viewCliente.getTbCliente().getSelectedRow() < Numeros.ZERO) {
            JOptionPane.showMessageDialog(null, Mensagem.selecioneCliente, Mensagem.selecione, Numeros.ZERO);
        } else {
            int opcao = JOptionPane.showConfirmDialog(null, Mensagem.desejaExcluirCliente);
            if (opcao == JOptionPane.YES_OPTION) {
                try {
                    cliente = listaCliente.get(this.viewCliente.getTbCliente().getSelectedRow());
                    //comandos de exclusao
                    excluir(cliente);
                    new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_CLIENTE, FuncionarioConectado.funcionarioConectado);
                    if (cliente.getTipoPessoa().equals(Constantes.LETRA_J)) {
                        new PessoaJuridicaController().excluir(cliente.getPessoaJuridicaIdPessoaJuridica());
                        new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_PESSOA_JURIDICA, FuncionarioConectado.funcionarioConectado);
                    } else {
                        new PessoaFisicaController().excluir(cliente.getPessoaFisicaIdPessoaFisica());
                        new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_PESSOA_FISICA, FuncionarioConectado.funcionarioConectado);
                    }
                    new EnderecoController().excluir(cliente.getEnderecoIdEndereco());
                    new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_ENDERECO, FuncionarioConectado.funcionarioConectado);

                    new ContatoController().excluir(cliente.getContatoIdContato());
                    new LogUsuarioController().gerarLog(Constantes.DELETE, Constantes.TABELA_CONTATO, FuncionarioConectado.funcionarioConectado);

                    JOptionPane.showMessageDialog(null, Mensagem.clienteExcluido, Mensagem.sucesso, Numeros.UM);
                    listarCliente();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void acaoBotaoSair() {
        this.viewCliente.setVisible(false);
    }

    public void acaoBotaoCancelar() {
        limparCampos();
        bloquearCampos();
        this.viewCliente.getBtNovo().setEnabled(true);
        this.viewCliente.getBtAlterar().setEnabled(true);
        this.viewCliente.getBtExcluir().setEnabled(true);
        this.viewCliente.getBtSair().setEnabled(true);
        this.viewCliente.getBtSalvar().setEnabled(false);
        this.viewCliente.getBtCancelar().setEnabled(false);
        alterar = false;
        pessoaFisica();
        carregarTabela();
    }

    public void acaoBotaoSalvar() {
        if (alterar) {
            if (validaAlterar()) {
                //alterar um cliente
                cliente = listaCliente.get(this.viewCliente.getTbCliente().getSelectedRow());
                endereco = cliente.getEnderecoIdEndereco();
                contato = cliente.getContatoIdContato();

                endereco.setEndereco(this.viewCliente.getTfLogradouro().getText());
                endereco.setNumero(Integer.parseInt(this.viewCliente.getTfNumero().getText()));
                endereco.setComplemento(this.viewCliente.getTfComplemento().getText());
                endereco.setBairro(this.viewCliente.getTfBairro().getText());
                endereco.setCep(this.viewCliente.getTfCep().getText());
                new EnderecoController().salvar(endereco);
                new LogUsuarioController().gerarLog(Constantes.UPDATE, Constantes.TABELA_ENDERECO, FuncionarioConectado.funcionarioConectado);

                contato.setTelefone(this.viewCliente.getTfTelefone().getText());
                contato.setCelular(this.viewCliente.getTfCelular().getText());
                contato.setEmail(this.viewCliente.getTfEmail().getText());
                new ContatoController().salvar(contato);
                new LogUsuarioController().gerarLog(Constantes.UPDATE, Constantes.TABELA_CONTATO, FuncionarioConectado.funcionarioConectado);

                JOptionPaneUtil.sucesso(Mensagem.clienteAlterado);
                limparCampos();
                acaoBotaoCancelar();
                listarCliente();
            }
        } else {
            if (valida()) {
                //cliete novo
                Cliente cliente = new Cliente();
                Endereco endereco = new Endereco();
                endereco.setEndereco(this.viewCliente.getTfLogradouro().getText());
                endereco.setNumero(Integer.parseInt(this.viewCliente.getTfNumero().getText()));
                endereco.setComplemento(this.viewCliente.getTfComplemento().getText());
                endereco.setBairro(this.viewCliente.getTfBairro().getText());
                endereco.setCep(this.viewCliente.getTfCep().getText());
                endereco.setCidadeIdCidade(listaCidade.get(this.viewCliente.getCbCidade().getSelectedIndex() - Numeros.UM));
                new EnderecoController().salvar(endereco);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_ENDERECO, FuncionarioConectado.funcionarioConectado);

                Contato contato = new Contato();
                contato.setTelefone(this.viewCliente.getTfTelefone().getText());
                contato.setCelular(this.viewCliente.getTfCelular().getText());
                contato.setEmail(this.viewCliente.getTfEmail().getText());
                new ContatoController().salvar(contato);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_CONTATO, FuncionarioConectado.funcionarioConectado);

                if (this.viewCliente.getRbFisico().isSelected()) {
                    PessoaFisica pf = new PessoaFisica();
                    pf.setNome(this.viewCliente.getTfNome().getText());
                    pf.setCpf(this.viewCliente.getTfCpf().getText());
                    pf.setRg(this.viewCliente.getTfRg().getText());
                    pf.setDataNascimento(this.viewCliente.getTfDataNascimento().getText());
                    new PessoaFisicaController().salvar(pf);
                    new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_PESSOA_FISICA, FuncionarioConectado.funcionarioConectado);
                    cliente.setTipoPessoa(Constantes.LETRA_F);
                    cliente.setPessoaFisicaIdPessoaFisica(pf);

                } else {
                    PessoaJuridica pj = new PessoaJuridica();
                    pj.setRazaoSocial(this.viewCliente.getTfNome().getText());
                    pj.setCnpj(this.viewCliente.getTfCpf().getText());
                    pj.setInscricaoEstadual(this.viewCliente.getTfRg().getText());
                    pj.setDataFundacao(this.viewCliente.getTfDataNascimento().getText());
                    new PessoaJuridicaController().salvar(pj);
                    new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_PESSOA_JURIDICA, FuncionarioConectado.funcionarioConectado);
                    cliente.setTipoPessoa(Constantes.LETRA_J);
                    cliente.setPessoaJuridicaIdPessoaJuridica(pj);
                }

                cliente.setContatoIdContato(contato);
                cliente.setEnderecoIdEndereco(endereco);

                salvar(cliente);
                new LogUsuarioController().gerarLog(Constantes.INSERT, Constantes.TABELA_CLIENTE, FuncionarioConectado.funcionarioConectado);
                limparCampos();
                acaoBotaoCancelar();
                listarCliente();
            }
        }
    }

    private void salvar(Cliente cliente) {
        try {
            new ClienteDAO().salvar(cliente);
            JOptionPaneUtil.sucesso(Mensagem.clienteSalvo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void excluir(Cliente cliente) {
        try {
            new ClienteDAO().excluir(cliente);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, Mensagem.clienteErroExcluir, Mensagem.erro, Numeros.ZERO);
            e.printStackTrace();
        }
    }

    public void bloquearCampos() {
        this.viewCliente.getRbFisico().setEnabled(false);
        this.viewCliente.getRbJuridico().setEnabled(false);
        this.viewCliente.getTfCpf().setEditable(false);
        this.viewCliente.getTfRg().setEditable(false);
        this.viewCliente.getTfNome().setEditable(false);
        this.viewCliente.getTfDataNascimento().setEditable(false);
        this.viewCliente.getTfLogradouro().setEditable(false);
        this.viewCliente.getTfNumero().setEditable(false);
        this.viewCliente.getTfComplemento().setEditable(false);
        this.viewCliente.getTfBairro().setEditable(false);
        this.viewCliente.getTfCep().setEditable(false);
        this.viewCliente.getCbCidade().setEnabled(false);
        this.viewCliente.getCbEstado().setEnabled(false);
        this.viewCliente.getTfTelefone().setEditable(false);
        this.viewCliente.getTfCelular().setEditable(false);
        this.viewCliente.getTfEmail().setEditable(false);
        this.viewCliente.getBtSalvar().setEnabled(false);
        this.viewCliente.getBtCancelar().setEnabled(false);
    }

    public void desbloquearCampos() {
        this.viewCliente.getRbFisico().setEnabled(true);
        this.viewCliente.getRbJuridico().setEnabled(true);
        this.viewCliente.getTfCpf().setEditable(true);
        this.viewCliente.getTfRg().setEditable(true);
        this.viewCliente.getTfNome().setEditable(true);
        this.viewCliente.getTfDataNascimento().setEditable(true);
        this.viewCliente.getTfLogradouro().setEditable(true);
        this.viewCliente.getTfNumero().setEditable(true);
        this.viewCliente.getTfComplemento().setEditable(true);
        this.viewCliente.getTfBairro().setEditable(true);
        this.viewCliente.getTfCep().setEditable(true);
        this.viewCliente.getCbCidade().setEnabled(true);
        this.viewCliente.getCbEstado().setEnabled(true);
        this.viewCliente.getTfTelefone().setEditable(true);
        this.viewCliente.getTfCelular().setEditable(true);
        this.viewCliente.getTfEmail().setEditable(true);
        this.viewCliente.getBtSalvar().setEnabled(true);
        this.viewCliente.getBtCancelar().setEnabled(true);
    }

    public void limparCampos() {

        this.viewCliente.getButtonGroup1().clearSelection();
        this.viewCliente.getTfCpf().setValue(null);
        this.viewCliente.getTfRg().setValue(null);
        this.viewCliente.getTfNome().setText(null);
        this.viewCliente.getTfDataNascimento().setValue(null);
        this.viewCliente.getTfLogradouro().setText(null);
        this.viewCliente.getTfNumero().setText(null);
        this.viewCliente.getTfComplemento().setText(null);
        this.viewCliente.getTfBairro().setText(null);
        this.viewCliente.getTfCep().setValue(null);
        this.viewCliente.getCbCidade().setSelectedIndex(Numeros.ZERO);
        this.viewCliente.getCbEstado().setSelectedIndex(Numeros.ZERO);
        this.viewCliente.getTfTelefone().setValue(null);
        this.viewCliente.getTfCelular().setValue(null);
        this.viewCliente.getTfEmail().setText(null);

    }

    public void desbloqueioAlterar() {
        desbloquearCampos();
        this.viewCliente.getRbFisico().setEnabled(false);
        this.viewCliente.getRbJuridico().setEnabled(false);
        this.viewCliente.getTfCpf().setEditable(false);
        this.viewCliente.getTfRg().setEditable(false);
        this.viewCliente.getTfNome().setEditable(false);
        this.viewCliente.getTfDataNascimento().setEditable(false);
        this.viewCliente.getBtNovo().setEnabled(false);
        this.viewCliente.getBtAlterar().setEnabled(false);
        this.viewCliente.getBtExcluir().setEnabled(false);
        this.viewCliente.getBtSair().setEnabled(false);
        this.viewCliente.getBtSalvar().setEnabled(true);
        this.viewCliente.getBtCancelar().setEnabled(true);

    }

    public void pessoaJuridica() {
        try {
            CNPJMask = new MaskFormatter(Constantes.MASK_FORMAT_CNPJ);
            IEMask = new MaskFormatter(Constantes.MASK_FORMAT_IE);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        this.viewCliente.getLbCpf().setText(Constantes.CNPJ);
        this.viewCliente.getLbRg().setText(Constantes.IE);
        this.viewCliente.getLbNome().setText(Constantes.RAZAO_SOCIAL);
        this.viewCliente.getLbData().setText(Constantes.DATA_FUNDACAO);
        this.viewCliente.getTfCpf().setFormatterFactory(new DefaultFormatterFactory(CNPJMask));
        this.viewCliente.getTfRg().setFormatterFactory(new DefaultFormatterFactory(IEMask));
    }

    public void pessoaFisica() {
        try {
            CPFMask = new MaskFormatter(Constantes.MASK_FORMAT_CPF);
            RGMask = new MaskFormatter(Constantes.MASK_FORMAT_RG);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        this.viewCliente.getLbCpf().setText(Constantes.CPF);
        this.viewCliente.getLbRg().setText(Constantes.RG);
        this.viewCliente.getLbNome().setText(Constantes.NOME);
        this.viewCliente.getLbData().setText(Constantes.DATA_NASCIMENTO);
        this.viewCliente.getTfCpf().setFormatterFactory(new DefaultFormatterFactory(CPFMask));
        this.viewCliente.getTfRg().setFormatterFactory(new DefaultFormatterFactory(RGMask));

    }

    private boolean valida() {

        if (this.viewCliente.getRbFisico().isSelected()) {
            if (Valida.verificaCpfVazio(this.viewCliente.getTfCpf().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeCpf);
                this.viewCliente.getTfCpf().grabFocus();
                return false;
            } else if (!Valida.validarCpf(this.viewCliente.getTfCpf().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeCpfValido);
                this.viewCliente.getTfCpf().grabFocus();
                return false;
            }
            if (Valida.verificaRgVazio(this.viewCliente.getTfRg().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeRg);
                this.viewCliente.getTfRg().grabFocus();
                return false;
            }
            if (Valida.verificaStringVazio(this.viewCliente.getTfNome().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeNome);
                this.viewCliente.getTfNome().grabFocus();
                return false;
            } else if (Valida.apenasLetras(this.viewCliente.getTfNome().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeNomeValido);
                this.viewCliente.getTfNome().grabFocus();
                return false;
            }
            if (Valida.verificaDataVazio(this.viewCliente.getTfDataNascimento().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeData);
                this.viewCliente.getTfDataNascimento().grabFocus();
                return false;

            } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewCliente.getTfDataNascimento().getText()))) {
                JOptionPaneUtil.erro(Mensagem.DataInvalida);
                this.viewCliente.getTfDataNascimento().grabFocus();
                return false;
            }
        } else {
            if (Valida.verificaCnpjVazio(this.viewCliente.getTfCpf().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeCnpj);
                this.viewCliente.getTfCpf().grabFocus();
                return false;
            }
            if (Valida.verificaIeVazio(this.viewCliente.getTfRg().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeIe);
                this.viewCliente.getTfRg().grabFocus();
                return false;
            }
            if (Valida.verificaStringVazio(this.viewCliente.getTfNome().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeRazao);
                this.viewCliente.getTfNome().grabFocus();
                return false;
            }
            if (Valida.verificaDataVazio(this.viewCliente.getTfDataNascimento().getText())) {
                JOptionPaneUtil.erro(Mensagem.informeDataFundacao);
                this.viewCliente.getTfDataNascimento().grabFocus();
                return false;

            } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewCliente.getTfDataNascimento().getText()))) {
                JOptionPaneUtil.erro(Mensagem.DataFundacaoInvalida);
                this.viewCliente.getTfDataNascimento().grabFocus();
                return false;
            }

        }
        if (Valida.verificaStringVazio(this.viewCliente.getTfLogradouro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogradouro);
            this.viewCliente.getTfLogradouro().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewCliente.getTfNumero().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNumero);
            this.viewCliente.getTfNumero().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewCliente.getTfBairro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeBairro);
            this.viewCliente.getTfBairro().grabFocus();
            return false;
        }
        if (Valida.verificaCepVazio(this.viewCliente.getTfCep().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCep);
            this.viewCliente.getTfCep().grabFocus();
            return false;
        }
        if (this.viewCliente.getCbCidade().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeCidade);
            this.viewCliente.getCbCidade().grabFocus();
            return false;
        }
        if (this.viewCliente.getCbEstado().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeEstado);
            this.viewCliente.getCbEstado().grabFocus();
            return false;
        }
        if (Valida.verificaTelefoneVazio(this.viewCliente.getTfTelefone().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeTelefone);
            this.viewCliente.getTfTelefone().grabFocus();
            return false;
        }
        if (Valida.verificaCelularVazio(this.viewCliente.getTfCelular().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCelular);
            this.viewCliente.getTfCelular().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewCliente.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmail);
            this.viewCliente.getTfEmail().grabFocus();
            return false;
        } else if (!Valida.validaEmail(this.viewCliente.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmailValido);
            this.viewCliente.getTfEmail().grabFocus();
            return false;
        }
        return true;
    }

    private boolean validaAlterar() {

        if (Valida.verificaStringVazio(this.viewCliente.getTfLogradouro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogradouro);
            this.viewCliente.getTfLogradouro().grabFocus();
            return false;
        }
        if (Valida.verificaIntZero(this.viewCliente.getTfNumero().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeNumero);
            this.viewCliente.getTfNumero().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewCliente.getTfBairro().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeBairro);
            this.viewCliente.getTfBairro().grabFocus();
            return false;
        }
        if (Valida.verificaCepVazio(this.viewCliente.getTfCep().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCep);
            this.viewCliente.getTfCep().grabFocus();
            return false;
        }
        if (this.viewCliente.getCbCidade().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeCidade);
            this.viewCliente.getCbCidade().grabFocus();
            return false;
        }
        if (this.viewCliente.getCbEstado().getSelectedIndex() == Numeros.ZERO) {
            JOptionPaneUtil.erro(Mensagem.informeEstado);
            this.viewCliente.getCbEstado().grabFocus();
            return false;
        }
        if (Valida.verificaTelefoneVazio(this.viewCliente.getTfTelefone().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeTelefone);
            this.viewCliente.getTfTelefone().grabFocus();
            return false;
        }
        if (Valida.verificaCelularVazio(this.viewCliente.getTfCelular().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeCelular);
            this.viewCliente.getTfCelular().grabFocus();
            return false;
        }
        if (Valida.verificaStringVazio(this.viewCliente.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmail);
            this.viewCliente.getTfEmail().grabFocus();
            return false;
        } else if (!Valida.validaEmail(this.viewCliente.getTfEmail().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeEmailValido);
            this.viewCliente.getTfEmail().grabFocus();
            return false;
        }
        return true;
    }

    public void carregarTela() {
        if (cliente.getTipoPessoa().equals(Constantes.LETRA_F)) {
            pessoaFisica();
            this.viewCliente.getRbFisico().setSelected(true);
            this.viewCliente.getTfCpf().setText(cliente.getPessoaFisicaIdPessoaFisica().getCpf());
            this.viewCliente.getTfRg().setText(cliente.getPessoaFisicaIdPessoaFisica().getRg());
            this.viewCliente.getTfNome().setText(cliente.getPessoaFisicaIdPessoaFisica().getNome());
            this.viewCliente.getTfDataNascimento().setText(cliente.getPessoaFisicaIdPessoaFisica().getDataNascimento());
        } else {
            pessoaJuridica();
            this.viewCliente.getRbJuridico().setSelected(true);
            this.viewCliente.getTfCpf().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getCnpj());
            this.viewCliente.getTfRg().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getInscricaoEstadual());
            this.viewCliente.getTfNome().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getRazaoSocial());
            this.viewCliente.getTfDataNascimento().setText(cliente.getPessoaJuridicaIdPessoaJuridica().getDataFundacao());
        }
        this.viewCliente.getTfLogradouro().setText(cliente.getEnderecoIdEndereco().getEndereco());
        this.viewCliente.getTfNumero().setText(cliente.getEnderecoIdEndereco().getNumero() + Constantes.VAZIO);
        this.viewCliente.getTfComplemento().setText(cliente.getEnderecoIdEndereco().getComplemento());
        this.viewCliente.getTfBairro().setText(cliente.getEnderecoIdEndereco().getBairro());
        this.viewCliente.getCbCidade().setSelectedItem(cliente.getEnderecoIdEndereco().getCidadeIdCidade().getNome());
        this.viewCliente.getCbEstado().setSelectedItem(cliente.getEnderecoIdEndereco().getCidadeIdCidade().getEstadoIdEstado().getUf());
        this.viewCliente.getTfCep().setText(cliente.getEnderecoIdEndereco().getCep());
        this.viewCliente.getTfTelefone().setText(cliente.getContatoIdContato().getTelefone());
        this.viewCliente.getTfCelular().setText(cliente.getContatoIdContato().getCelular());
        this.viewCliente.getTfEmail().setText(cliente.getContatoIdContato().getEmail());
    }

}
