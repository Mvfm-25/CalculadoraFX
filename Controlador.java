// Parte de back-end da calculadora final.
// Primeiro implementar com apenas dois operandos, procurando expandir isso depois.
// [mvfm]
//
// Criado : 14/11/2025  ||  Última vez alterado : 14/11/2025

// Importando das bibliotecas javafx.
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class Controlador {

    public TextField display;
    public VBox root;
    public Button clear, mult, div, sub, add, igual;

    // Inicializando assim só pra ter certeza que vai fucnionar.
    public String opearador = "";
    public String operando1 = "";
    public String operando2 = "";

    // Variável pra saber se o próximo número é o primeiro ou o segundo.
    public boolean primeiroNumero = true;

    // Limpa a tela, reseta o estado dos operandos.
    public void limpaTela(ActionEvent evento) {
        operando1 = "";
        operando2 = "";
        display.setText("");
        atualizaTela();
    }

    // Método(s) principal pra atualizar a tela.
    private void atualizaTela() {
        display.setText(operando1 + operando2);
    }
    private void atualizaTela(String s) {
        display.setText(s);
    }

    // Métodos para operações. 
    // Muita coisa escrita, mas não sei como não fazer isso de outra forma.
    public void adicao(ActionEvent evento) {
        opearador = "+";
        primeiroNumero = false;
        atualizaTela();
    }
    public void subtracao(ActionEvent evento) {
        opearador = "-";
        primeiroNumero = false;
        atualizaTela();
    }
    public void multiplicacao(ActionEvent evento) {
        opearador = "*";
        primeiroNumero = false;
        atualizaTela();
    }
    public void divisao(ActionEvent evento) {
        opearador = "/";
        primeiroNumero = false;
        atualizaTela();
    }

    // Métodos para os números.
    // De novo, muita coisa escrita, mas não sei como não fazer isso de outra jeito.
    public void numeroZero(ActionEvent evento) {
        if (primeiroNumero) {
            operando1 += "0";
        } else {
            operando2 += "0";
        }
        atualizaTela();
    }
    public void numeroUm(ActionEvent evento) {
        if (primeiroNumero) {
            operando1 += "1";
        } else {
            operando2 += "1";
        }
        atualizaTela();
    }
    public void numeroDois(ActionEvent evento) {
        if (primeiroNumero) {
            operando1 += "2";
        } else {
            operando2 += "2";
        }
        atualizaTela();
    }
    public void numeroTres(ActionEvent evento) {
        if (primeiroNumero) {
            operando1 += "3";
        } else {
            operando2 += "3";
        }
        atualizaTela();
    }
    public void numeroQuatro(ActionEvent evento) {
        if (primeiroNumero) {
            operando1 += "4";
        } else {
            operando2 += "4";
        }
        atualizaTela();
    }
    public void numeroCinco(ActionEvent evento) {
        if (primeiroNumero) {
            operando1 += "5";
        } else {
            operando2 += "5";
        }
        atualizaTela();
    }
    public void numeroSeis(ActionEvent evento) {
        if (primeiroNumero) {
            operando1 += "6";
        } else {
            operando2 += "6";
        }
        atualizaTela();
    }
    public void numeroSete(ActionEvent evento) {
        if (primeiroNumero) {
            operando1 += "7";
        } else {
            operando2 += "7";
        }
        atualizaTela();
    }
    public void numeroOito(ActionEvent evento) {
        if (primeiroNumero) {
            operando1 += "8";
        } else {
            operando2 += "8";
        }
        atualizaTela();
    }
    public void numeroNove(ActionEvent evento) {
        if (primeiroNumero) {
            operando1 += "9";
        } else {
            operando2 += "9";
        }
        atualizaTela();
    }

}
