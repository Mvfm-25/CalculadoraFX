// Parte de back-end da calculadora final.
// Primeiro implementar com apenas dois operandos, procurando expandir isso depois.
// [mvfm]
//
// Criado : 14/11/2025  ||  Última vez alterado : 14/11/2025

// Importando das bibliotecas javafx.
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class Controlador {

    public TextField display;
    public VBox root;
    public Button clear, mult, div, sub, add, igual;

    // Inicializando assim só pra ter certeza que vai fucnionar.
    public String operador = "";
    public String operando1 = "";
    public String operando2 = "";

    // Variável pra saber se o próximo número é o primeiro ou o segundo.
    public boolean primeiroNumero = true;

    // Pra saber se o que precisar ser renderizado no display é o resultado.
    public boolean mostrandoResultado = false;

    // Limpa a tela, reseta o estado de tudo.
    public void limpaEstado() {
        operando1 = "";
        operando2 = "";
        primeiroNumero = true;
        mostrandoResultado = false;
        display.setText("");
        atualizaTela();
    }

    @FXML
    public void limpaTela(ActionEvent evento) {
        limpaEstado();
        atualizaTela("");
    }

    // Método(s) principal pra atualizar a tela.
    private void atualizaTela() {
        if(primeiroNumero){
            display.setText(operando1);
        } else {
            display.setText(operando1 + " " + operador + " " + operando2);
        }
    }
    private void atualizaTela(String s) {
        display.setText(s);
    }

    // Métodos para operações. 
    // Mais bonitinho agora.
    @FXML
    public void adicao(ActionEvent evento) {
        defineOperador("+");
    }
    @FXML
    public void subtracao(ActionEvent evento) {
        defineOperador("-");
    }
    @FXML
    public void multiplicacao(ActionEvent evento) {
        defineOperador("*");
    }
    @FXML
    public void divisao(ActionEvent evento) {
        defineOperador("/");
    }

    private void defineOperador(String op){
        if(!operando1.isEmpty()){
            operador = op;
            primeiroNumero = false;
            mostrandoResultado = false;
            atualizaTela(operando1 + " " + operador);
        }
    }

    // Método para os números.
    // Ah! Agora bem mais legal.
    @FXML
    public void adicionaNumero(ActionEvent evento){
        Button botao = (Button) evento.getSource();
        String digito = botao.getText();

        // Se já mostrou o resultado, limpa tudo antes.
        if(mostrandoResultado){
            limpaEstado();
        }

        if(primeiroNumero){
            operando1 += digito;
        } else {
            operando2 += digito;
        }
        atualizaTela();
    }

    // O método esperto que faz as contas.
    @FXML
    public void calcula(ActionEvent evento){

        // Não faz nada, por não ter nada pra fazer.
        if(operando1.isEmpty() || operando2.isEmpty() || operador.isEmpty()){
            return; 
        }

        try{
            double num1 = Double.parseDouble(operando1);
            double num2 = Double.parseDouble(operando2);
            double resultado = 0;

            // Só operações simples por enquanto.
            switch(operador){
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if(num2 == 0){
                        atualizaTela("Erro: Divisão por zero");
                        return;
                    }
                    resultado = num1 / num2;
                    break;
            }

            // Formatação do resultado.
            String resultadoStr;
            if(resultado == (long) resultado){
                resultadoStr = String.valueOf((long) resultado);
            } else {
                resultadoStr = String.valueOf(resultado);
            }

            atualizaTela(resultadoStr);

            // Limpa tudo pra próxima operação.
            operando1 = resultadoStr;
            operando2 = "";
            operador = "";
            primeiroNumero = true;
            mostrandoResultado = true;
        } catch (NumberFormatException e){
            atualizaTela("Erro: Formato inválido");
            limpaEstado();
        }



    }

}
