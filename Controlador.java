// Parte de back-end da calculadora final.
// Suporte para operações binárias e unárias & cálculos com múltiplos operadores!
// [mvfm]
//
// Criado : 14/11/2025  ||  Última vez alterado : 19/11/2025

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Controlador {

    public TextField display;
    public VBox root;
    public Button clear, mult, div, sub, add, igual;

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
        operador = "";
        primeiroNumero = true;
        mostrandoResultado = false;
        display.setText("");
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

    // Métodos para operações binárias (precisam de 2 números)
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
    
    @FXML
    public void exponencial(ActionEvent evento){
        defineOperador("^");
    }

    // Métodos para operações unárias (precisam de 1 número só)
    @FXML
    public void raizQuadrada(ActionEvent evento){
        calculaOperacaoUnaria("√");
    }
    
    @FXML
    public void aoQuadrado(ActionEvent evento){
        calculaOperacaoUnaria("x²");
    }

    private void defineOperador(String op){
        if(operando1.isEmpty()){
            return;
        }
        
        // Se já tem uma operação pendente (operador e operando2), calcula primeiro
        if(!operador.isEmpty() && !operando2.isEmpty()){
            calculaResultadoIntermediario();
        }
        
        operador = op;
        primeiroNumero = false;
        mostrandoResultado = false;
        atualizaTela(operando1 + " " + operador);
    }
    
    // Calcula resultado intermediário para operações encadeadas
    private void calculaResultadoIntermediario(){
        try{
            double num1 = Double.parseDouble(operando1);
            double num2 = Double.parseDouble(operando2);
            double resultado = executaOperacao(num1, num2, operador);
            
            String resultadoStr = formatarResultado(resultado);
            operando1 = resultadoStr;
            operando2 = "";
            
        } catch (NumberFormatException e){
            atualizaTela("Erro: Formato inválido");
            limpaEstado();
        }
    }

    // Método para operações unárias - calcula imediatamente
    private void calculaOperacaoUnaria(String op){
        if(operando1.isEmpty()){
            return;
        }

        try{
            double num1 = Double.parseDouble(operando1);
            double resultado = 0;

            switch(op){
                case "√":
                    if(num1 < 0){
                        atualizaTela("Erro: Raiz de número negativo");
                        return;
                    }
                    resultado = Math.sqrt(num1);
                    break;
                case "x²":
                    resultado = Math.pow(num1, 2);
                    break;
            }

            // Formatação do resultado
            String resultadoStr = formatarResultado(resultado);
            atualizaTela(op + "(" + operando1 + ") = " + resultadoStr);

            // Prepara para próxima operação
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

    // Método para os números.
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

    // O método esperto que faz as contas binárias.
    @FXML
    public void calcula(ActionEvent evento){

        // Não faz nada, por não ter nada pra fazer.
        if(operando1.isEmpty() || operando2.isEmpty() || operador.isEmpty()){
            return; 
        }

        try{
            double num1 = Double.parseDouble(operando1);
            double num2 = Double.parseDouble(operando2);
            double resultado = executaOperacao(num1, num2, operador);

            // Formatação do resultado
            String resultadoStr = formatarResultado(resultado);
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
    
    // Método auxiliar que executa a operação binária
    private double executaOperacao(double num1, double num2, String op){
        switch(op){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if(num2 == 0){
                    atualizaTela("Erro: Divisão por zero");
                    throw new ArithmeticException("Divisão por zero");
                }
                return num1 / num2;
            case "^":
                return Math.pow(num1, num2);
            default:
                atualizaTela("Erro: Operador desconhecido");
                throw new IllegalArgumentException("Operador desconhecido");
        }
    }

    // Método auxiliar para formatar resultados
    private String formatarResultado(double resultado){
        if(resultado == (long) resultado){
            return String.valueOf((long) resultado);
        } else {
            return String.valueOf(resultado);
        }
    }
}