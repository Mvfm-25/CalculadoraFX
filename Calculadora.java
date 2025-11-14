// Classe principal da calculadora.
// Parte front-end, as coisas bonitinhas.
// [mvfm]
//
// Criado : 14/11/2025  ||  Última vez alterado : 14/11/2025

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Calculadora extends Application {

    @Override
    public void start(Stage palco) throws Exception {
        // Carrega o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("calculadora.fxml"));
        
        // Configura a cena
        Scene cena = new Scene(root);
        
        // Adiciona o CSS, ainda não tem nada. Seria legal ter depois.
        // cena.getStylesheets().add(getClass().getResource("estilo.css").toExternalForm());
        
        // Configura o palco
        palco.setTitle("Calculadora");
        palco.setScene(cena);
        palco.setResizable(false);
        palco.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}