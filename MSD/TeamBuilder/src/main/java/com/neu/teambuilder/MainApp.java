package com.neu.teambuilder;

import javafx.application.Application;

import static javafx.application.Application.launch;

import java.sql.Connection;
import java.sql.DriverManager;

import com.neu.teambuilder.dao.core.IResultList;
import com.neu.teambuilder.dao.jdbc.SqlExecutor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
	
    @Override
    public void start(Stage stage) throws Exception {    	
    	Parent root = FXMLLoader.load(getClass().getResource("/fxml/search_screen.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("file:./src/main/resources/styles/Styles.css"); 
        
        stage.setTitle("Team Builder");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
