package com.example.javafx_essai1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.fxml.FXML.*;

//import org.kordamp.ikonli.javafx.FontIcon;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import io.github.palexdev.materialfx.controls.MFXButton;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContext;


public class HelloApplication extends Application {

    static Logger logger = LogManager.getLogger(HelloApplication.class);

    @Override
    public void start(Stage primaryStage) throws IOException {

        System.out.println("start");

        logger.log(Level.FATAL,"Alerte");
        logger.info("Arabie Saoudite");





        /*
        File file = new File("log4j2.xml");
        LoggerContext context = LogManager.getContext(false);

        context.
        context.setConfigLocation(file.toURI());
         */


        ManageLists Graph2 = new ManageLists("C:/Users/Anis/Documents/Intellij_javafx/javafx_essai1/src/main/java/com/example/javafx_essai1/knots.csv");
        ArrayList<Places> placesArray = Graph2.getPlacesArray();
        ArrayList<Roads> roadsArray = Graph2.getRoadsArray();

        Matrice matrice = new Matrice(roadsArray,placesArray); //en 1er les routes, ensuite les endroits

        Fonctionnalites fonctionnalites = new Fonctionnalites(matrice,placesArray,roadsArray);  //d'abord la matrice ensuite les endroits puis les routes

        Djikstra D = new Djikstra(roadsArray,placesArray);
        //System.out.println(D.getResult(placesArray.get(0),placesArray.get(1)));



        //System.out.println(Screen.getPrimary().getBounds());

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("page-accueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        //scene.getStylesheets().add(getClass().getResource("style_accueil.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("style_accueil2.css").toExternalForm());

        //System.out.println(String.join("\n", Font.getFamilies()));

        primaryStage.setTitle("Graphe");

        primaryStage.fullScreenProperty().addListener((observableScene, oldScene, property) -> {
            if (!property) {
                primaryStage.setFullScreen(true);
            }
        });


        primaryStage.setOpacity(0.0);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.show();


        //FXMLLoader fxmlLoader_1voisins = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //fxmlLoader_1voisins.load();
        PageAccueilController controllerAccueil;
        controllerAccueil = fxmlLoader.getController();
        //System.out.println(fonctionnalites);
        controllerAccueil.fillGraph(fonctionnalites);

    }


    public static void main(String[] args) throws IOException{
        launch(args);
    }
}