package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.FuncionarioDAO;
import br.com.jpbonfa.vendas.model.Funcionario;
import br.com.jpbonfa.vendas.service.FuncionarioConectado;
import br.com.jpbonfa.vendas.util.Constantes;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.Numeros;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.LoginView;
import br.com.jpbonfa.vendas.view.MenuView;

/**
 *
 * @author joaop
 */
public class LoginController {

    private LoginView viewLogin;

    public LoginController() {
    }

    public LoginController(LoginView viewLogin) {
        this.viewLogin = viewLogin;
    }

    public void acaoBotaoCancelar() {
        System.exit(Numeros.ZERO);
    }

    public void acaoBotaoConfirmar() {
        if (valida()) {
            if (verificaLogin(this.viewLogin.getTfLogin().getText(), this.viewLogin.getTfSenha().getText())) {
                this.viewLogin.setVisible(false);
                new MenuView();
            }
        }

    }

    private boolean valida() {
        if (Valida.verificaStringVazio(this.viewLogin.getTfLogin().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogin);
            return false;
        } else if (Valida.apenasLetras(this.viewLogin.getTfLogin().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeLogin);
            return false;
        }
        if (Valida.verificaStringVazio(this.viewLogin.getTfSenha().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeSenha);
            return false;

        }
        return true;
    }

    public boolean verificaLogin(String login, String senha) {
        for (Funcionario user : new FuncionarioDAO().buscarTodos()) {
            if (user.getLogin().equals(login) && user.getSenha().equals(senha)) {
                FuncionarioConectado.funcionarioConectado = user;
                new LogUsuarioController().gerarLog(Constantes.LOGIN, Constantes.VAZIO, user);
                return true;
            }
        }
        JOptionPaneUtil.erro(Mensagem.erroLogin);
        return false;
    }
}
