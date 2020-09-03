package br.com.jpbonfa.vendas.controller;

import br.com.jpbonfa.vendas.util.JOptionPaneUtil;
import br.com.jpbonfa.vendas.util.Mensagem;
import br.com.jpbonfa.vendas.util.ServiceUtil;
import br.com.jpbonfa.vendas.util.Valida;
import br.com.jpbonfa.vendas.view.VendaPrazoView;

/**
 *
 * @author joaop
 */
public class VendaPrazoController {

    private VendaPrazoView viewVendaPrazo;

    public VendaPrazoController() {
    }

    public VendaPrazoController(VendaPrazoView viewVendaPrazo) {
        this.viewVendaPrazo = viewVendaPrazo;
    }

    public void AcaoBotaoConfirmar() {
        if (valida()) {
            //procedimentos para salvar os dados

        }

    }

    private boolean valida() {
        if (Valida.verificaDataVazio(this.viewVendaPrazo.getTfAReceber().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeData);
            return false;

        } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewVendaPrazo.getTfAReceber().getText()))) {
            JOptionPaneUtil.erro(Mensagem.DataInvalida);
            this.viewVendaPrazo.getTfAReceber().grabFocus();
            return false;
        }
        if (Valida.verificaRadioButtonSelecionado(this.viewVendaPrazo.getGrupoBotaoVencido())) {
            JOptionPaneUtil.erro(Mensagem.informeVencido);
            return false;

        }
        if (Valida.verificaDataVazio(this.viewVendaPrazo.getTfVencimento().getText())) {
            JOptionPaneUtil.erro(Mensagem.informeData);
            return false;

        } else if (!Valida.validaData(ServiceUtil.quebraData(this.viewVendaPrazo.getTfVencimento().getText()))) {
            JOptionPaneUtil.erro(Mensagem.DataInvalida);
            this.viewVendaPrazo.getTfVencimento().grabFocus();
            return false;
        }

        if (Valida.verificaRadioButtonSelecionado(this.viewVendaPrazo.getGrupoBotaoPago())) {
            JOptionPaneUtil.erro(Mensagem.informePago);
            return false;

        }

        return true;
    }

}
