package com.example.javafx_essai1;

import animatefx.animation.*;


import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PageAccueilController implements Initializable {

    private Fonctionnalites Graph;

    public void fillGraph(Fonctionnalites Graph_to_insert) {
        this.Graph = Graph_to_insert;
    }

    @FXML
    private Label titreAccueil;


    @FXML
    private MFXButton boutonVisualisation;

    @FXML
    private MFXButton boutonInfos;

    @FXML
    private MFXButton boutonVoisins;

    @FXML
    private MFXButton boutonComparaisons;


    private double screenWidth;

    private double screenHeight;



    public void setLabel(String truc) {
        titreAccueil.setText(truc);
    }

    @FXML
    public void closeApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private Label buttonDesc_top_left;
    @FXML
    private Label buttonDesc_top_right;
    @FXML
    private Label buttonDesc_bottom_left;
    @FXML
    private Label buttonDesc_bottom_right;

    public void initializePanePosition(Pane pane, MFXButton button) {
        double label_height = buttonDesc_top_left.getPrefHeight();
        double button_layoutX = button.getLayoutX();
        double button_layoutY = button.getLayoutY();

        double button_width = button.getPrefWidth();    //attention en cas de changement sur SceneBuilder,
        double button_height = button.getPrefHeight();  //surtout si c'est modifier un seul truc

        double ecart_cotes = 20.0;
        double ecart_hautbas = 15.0;

        pane.setPrefWidth(button_width+2*ecart_cotes);
        pane.setPrefHeight(button_height+label_height+2*ecart_hautbas+6); //6 = valeur de écart_bas dans la fonction en-dessous


        pane.setLayoutX(button_layoutX-ecart_cotes);
        pane.setLayoutY(button_layoutY-ecart_hautbas);
    }

    public void initializeDescPosition(Label label, MFXButton button) {
        double button_layoutX = button.getLayoutX();
        double button_layoutY = button.getLayoutY();

        double button_width = button.getPrefWidth();
        double button_height = button.getPrefHeight();

        double ecart_bas = 10;

        label.setPrefWidth(button_width);

        label.setLayoutX(button_layoutX);
        label.setLayoutY(button_layoutY+button_height+ecart_bas);
    }

    @FXML
    private Pane buttonBackgroundPane_top_left;
    @FXML
    private Pane buttonBackgroundPane_top_right;
    @FXML
    private Pane buttonBackgroundPane_bottom_left;
    @FXML
    private Pane buttonBackgroundPane_bottom_right;



    @FXML
    private AnchorPane ap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MFXButton[] buttons = {boutonVisualisation,boutonInfos,boutonVoisins,boutonComparaisons};
        Pane[] buttonBackgrounds = {buttonBackgroundPane_top_left,buttonBackgroundPane_top_right,buttonBackgroundPane_bottom_left,buttonBackgroundPane_bottom_right};
        Label[] buttonDescs = {buttonDesc_top_left,buttonDesc_top_right,buttonDesc_bottom_left,buttonDesc_bottom_right};

        for(int i = 0; i<buttons.length; i++)  {
            initializePanePosition(buttonBackgrounds[i],buttons[i]);
            initializeDescPosition(buttonDescs[i],buttons[i]);
        }

    }



    @FXML
    public void openVisualisation(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("page-affichage.fxml"));
        Scene newScene = new Scene(fxmlLoader.load());

        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setFullScreen(true);
        newStage.setResizable(false);

        newStage.show();

        PageAffichageController controllerAffichage;
        controllerAffichage = fxmlLoader.getController();
        controllerAffichage.fillGraph(Graph);
    }

    @FXML
    public void openInfos(ActionEvent event) throws IOException {
        //new Bounce(bouton1Voisins).play();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("page-deux-voisins.fxml"));
        Scene newScene = new Scene(fxmlLoader.load());

        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setFullScreen(true);
        newStage.setResizable(false);

        newStage.show();


        PageDeuxVoisinsController controllerInfos;
        controllerInfos = fxmlLoader.getController();
        controllerInfos.fillGraph(Graph);


    }

    @FXML
    public void openVoisins(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene newScene = new Scene(fxmlLoader.load());

        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setFullScreen(true);
        newStage.setResizable(false);

        newStage.show();


        HelloController controller1Voisins;
        controller1Voisins = fxmlLoader.getController();
        controller1Voisins.fillGraph(Graph);
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("page-deux-voisins.fxml"));
        Scene newScene = new Scene(fxmlLoader.load());

        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setFullScreen(true);
        newStage.setResizable(false);

        newStage.show();

        PageDeuxVoisinsController controller2Voisins;
        controller2Voisins = fxmlLoader.getController();
        controller2Voisins.fillGraph(Graph);

         */
    }

    @FXML
    public void openComparaison(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("page-comparaison.fxml"));
        Scene newScene = new Scene(fxmlLoader.load());

        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.setFullScreen(true);
        newStage.setResizable(false);

        newStage.show();

        PageComparaisonController controllerComparaison;
        controllerComparaison = fxmlLoader.getController();
        controllerComparaison.fillGraph(Graph);
    }
}
