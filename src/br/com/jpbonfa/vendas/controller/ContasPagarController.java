package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.ServiceUtil;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.ContasAPagarView;

/**
 *
 * @author joaop
 */
public class ContasPagarController {

    private ContasAPagarView viewContasAPagar;

    public ContasPagarController() {
    }

    public ContasPagarController(ContasAPagarView viewContasAPagar) {
        this.viewContasAPagar = viewContasAPagar;
    }

    public void acaoBotaoAlterar() {
        desbloquearCampos();
        this.viewContasAPagar.getBtSalvar().setEnabled(true);
        this.viewContasAPagar.getBtCancelar().setEnabled(true);
        this.viewContasAPagar.getBtAlterar().setEnabled(false);
        this.viewContasAPagar.getBtSair().setEnabled(false);

    }

    public void acaoBotaoSalvar() {
        if (validaAlterar()) {

        }

    }

    public void acaoBotaoCancelar() {
        limparCampos();
        bloquearCampos();
        this.viewContasAPagar.getBtSalvar().setEnabled(false);
        this.viewContasAPagar.getBtCancelar().setEnabled(false);
        this.viewContasAPagar.getBtAlterar().setEnabled(true);
        this.viewContasAPagar.getBtSair().setEnabled(true);
    }

    public void acaoBotaoSair() {

        this.viewContasAPagar.setVisible(false);

    }

    public void bloquearCampos() {
        this.viewContasAPagar.getTfDataPagamento().setEditable(false);
        this.viewContasAPagar.getTfDataVencimento().setEditable(false);
        this.viewContasAPagar.getBtSalvar().setEnabled(false);
        this.viewContasAPagar.getBtCancelar().setEnabled(false);
        this.viewContasAPagar.getRbPago().setEnabled(false);
        this.viewContasAPagar.getRbNaoPago().setEnabled(false);

    }

    public void desbloquearCampos() {
        this.viewContasAPagar.getTfDataPagamento().setEditable(true);
        this.viewContasAPagar.getTfDataVencimento().setEditable(true);
        this.viewContasAPagar.getRbPago().setEnabled(true);
        this.viewContasAPagar.getRbNaoPago().setEnabled(true);
        this.viewContasAPagar.getBtSalvar().setEnabled(true);
        this.viewContasAPagar.getBtCancelar().setEnabled(true);

    }

    public void limparCampos() {
        this.viewContasAPagar.getTfDataPagamento().setValue(null);
        this.viewContasAPagar.getTfDataVencimento().setValue(null);
        this.viewContasAPagar.getButtonGroup1().clearSelection();

    }

    private boolean validaAlterar() {

        if (Valida.verificaDataVazio(this.viewContasAPagar.getTfDataVencimento().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeData);
            this.viewContasAPagar.getTfDataVencimento().grabFocus();
            return false;
        } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewContasAPagar.getTfDataVencimento().getText()))) {
            JOptionPaneUtil.erro(Mensagem.DataInvalida);
            this.viewContasAPagar.getTfDataVencimento().grabFocus();
            return false;
        }
        if (Valida.verificaDataVazio(this.viewContasAPagar.getTfDataPagamento().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeData);
            this.viewContasAPagar.getTfDataPagamento().grabFocus();
            return false;
        } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewContasAPagar.getTfDataPagamento().getText()))) {
            JOptionPaneUtil.erro(Mensagem.DataInvalida);
            this.viewContasAPagar.getTfDataPagamento().grabFocus();
            return false;
        }
        if (Valida.verificaRadioButtonSelecionado(this.viewContasAPagar.getButtonGroup1())) {
            JOptionPaneUtil.erro(Mensagem.informePago);
            return false;
        }

        return true;
    }

}
