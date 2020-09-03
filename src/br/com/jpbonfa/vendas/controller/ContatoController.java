package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.dao.ContatoDAO;
import br.com.jpbonfa.vendas.model.Contato;
import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import java.util.ArrayList;

/**
 *
 * @author joaop
 */
public class ContatoController {

    public ContatoController() {
    }

    public ArrayList<Contato> buscarTodos() {
        try {
            return new ContatoDAO().buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroListarContatos);
        }
        return null;
    }

    public void excluir(Contato contato) {
        try {
            new ContatoDAO().excluir(contato);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroExcluirContatos);
        }

    }

    public void salvar(Contato contato) {
        try {
            new ContatoDAO().salvar(contato);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPaneUtil.erro(Mensagem.erroSalvarContatos);
        }

    }
}
