package br.com.jpbonfa.vendas.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;

/**
 *
 * @author joaop
 */
public class Valida {

    public static boolean validaEmail(String args) {
        int index = args.indexOf("@");
        if (index > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean verificaRadioButtonSelecionado(ButtonGroup args) {
        if (args.isSelected(null)) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean verificaStringVazio(String args) {// inicio metodo
        if (args.trim().equals(Constantes.VAZIO)) {// inicio do if
            return true;
        } else {
            return false;
        } // fim do if

    }// fim do metodo

    public static boolean verificaIntZero(String string) {// inicio do metodo
        try {
            int args = Integer.parseInt(string);

            if (args == 0) {// if
                return true;
            } // fim do if

        } catch (Exception e) {
            return true;
        }
        return false;

    }// inicio do metodo

    public static boolean verificaDoubleZero(String string) {// inicio metodo
        try {
            double args = Double.parseDouble(string);
            if (args == 0) {// inicio do if
                return true;
            } // fim do if

        } catch (Exception e) {
            return true;
        }
        return false;

    }// fim metodo

    public static boolean verificaCpfVazio(String cpf) {// inicio do metodo
        if (cpf.equals(Constantes.FORMATO_CPF)) {// inicio do if
            return true;
        } else {
            return false;
        } // fim do if
    }// fim do metodo

    public static boolean verificaCnpjVazio(String cpf) {// inicio do metodo
        if (cpf.equals("  .   .   /    -  ")) {// inicio do if
            return true;
        } else {
            return false;
        } // fim do if
    }// fim do metodo

    public static boolean verificaIeVazio(String cpf) {// inicio do metodo
        if (cpf.equals("   .   .   .   ")) {// inicio do if
            return true;
        } else {
            return false;
        } // fim do if
    }// fim do metodo

    public static boolean verificaRgVazio(String rg) {// inicio do metodo
        if (rg.equals("  .   .   - ")) {// inicio do if
            return true;
        } else {
            return false;
        } // fim do if

    }// fim do metodo

    public static boolean verificaCepVazio(String cep) {// inicio do metodo
        if (cep.equals("     -   ")) {// inicio do if
            return true;
        } else {
            return false;
        }
    }// fim do metodo

    public static boolean verificaTelefoneVazio(String telefone) {// inicio do metodo
        if (telefone.equals("(  )    -    ")) {// inicio do if
            return true;
        } else {
            return false;
        }
    }// fim do metodo

    public static boolean verificaCelularVazio(String celular) {// inicio do metodo
        if (celular.equals("(  )     -    ")) {// inicio do if
            return true;
        } else {
            return false;
        }
    }// fim do metodo

    public static boolean verificaDataVazio(String data) {// inicio do metodo
        if (data.equals("  /  /    ")) {// inicio do if
            return true;
        } else {
            return false;
        } // fim do if

    }// fim do metodo]

    public static boolean apenasLetras(String string) {

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher match = pattern.matcher(string);

        if (match.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validaData(int[] args) {
        if (args[0] < 0 || args[0] > 31) {
            return false;
        }
        if (args[1] < 0 || args[1] > 12) {
            return false;

        }
        Calendar cal;
        cal = GregorianCalendar.getInstance();

        if (args[2] > cal.get(Calendar.YEAR)) {
            return false;
        }
        return true;
    }

    public static Boolean validarCpf(String strCpf) {

        if (verificaStringVazio(strCpf)) {
            return (false);
        }

        strCpf = strCpf.replace('.', ' ');
        strCpf = strCpf.replace('-', ' ');
        strCpf = strCpf.replaceAll(" ", "");

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (strCpf.equals("00000000000") || strCpf.equals("11111111111")
                || strCpf.equals("22222222222") || strCpf.equals("33333333333")
                || strCpf.equals("44444444444") || strCpf.equals("55555555555")
                || strCpf.equals("66666666666") || strCpf.equals("77777777777")
                || strCpf.equals("88888888888") || strCpf.equals("99999999999")
                || (strCpf.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
            // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (strCpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (strCpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == strCpf.charAt(9)) && (dig11 == strCpf.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (Exception erro) {
            return (false);
        }
    }
}
