package com.example.javafx_essai1;


import com.almasb.fxgl.ui.FXGLListView;
import io.github.palexdev.materialfx.builders.layout.BackgroundBuilder;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyComboBox;
import javafx.application.*;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.*;


import animatefx.animation.*;
import animatefx.util.*;


import com.jfoenix.controls.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.*;
import org.controlsfx.control.SearchableComboBox.*;
import org.kordamp.ikonli.javafx.FontIcon;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private HashMap<String,String> siteLinksContainer = new HashMap<String,String>() {{
        put("Villeurbanne","https://www.villeurbanne.fr/");
        put("Caluire","https://www.ville-caluire.fr/");
        put("Rillieux","https://www.rillieuxlapape.fr/ville-de-rillieux-la-pape-3.html");
        put("Marie-9e","https://mairie9.lyon.fr/");
        put("Fourviere","https://www.fourviere.org/fr/");
        put("Meyzieu","https://www.meyzieu.fr/");
        put("Macon","https://www.macon.fr/");
        put("Andrezieux","https://www.andrezieux-boutheon.com/");
        put("Saint-etienne","https://www.saint-etienne.fr/");
        put("Venissieux","https://www.ville-venissieux.fr/");
        put("Roanne","https://www.aggloroanne.fr/site-officiel-roannais-agglomeration-et-ville-de-roanne-3.html");
        put("Balbigny","https://www.balbigny.fr/");
        put("Digoin","https://www.digoin.fr/");
        put("Craponne","https://www.mairie-craponne.fr/-1.html");
        put("Villefranche","http://www.villefranche.net/");
        put("Genas","https://genas.fr/");
        put("L'arbresle","https://www.mairie-larbresle.fr/");
        put("Les-Echets","https://www.miribel.fr/vivre-a-miribel/les-hameaux/les-echets");
        put("Lapalisse","http://www.ville-lapalisse.fr/fr/");
        put("Burger-king-Limonest","https://www.burgerking.fr/restaurant/lyon-limonest");
        put("McDo-Decines","https://www.mcdonalds.fr/restaurants/mcdonalds-decines/149");
        put("Subway-Vaulx","https://restaurants.subway.com/fr/france/vaulx-en-velin/ccial-carre-de-soie");
        put("KFC-Saint-Priest","https://www.kfc.fr/nos-restaurants/kfc-saint-priest");
        put("Wok-Villeurbanne","https://www.my-wok.com/villeurbanne");
        put("La-Marinade","https://mon-resto-halal.com/restaurant/la-marinade-villeurbanne");
        put("Confluence","https://www.lyon-confluence.fr/fr");
        put("Escape-Game-Perrache","https://lyon.escapegameover.fr/");
        put("Laser-Game-Genas","https://www.starlasergame3d.com/");
        put("Bowling-Bron","http://www.metropolis-lyon-bron.com/");
        put("Cinema-Brignais","https://www.cgrcinemas.fr/brignais/");
        put("Parc-de-la-tete-d'or","https://www.loisirs-parcdelatetedor.com/");
    }};

    private HashMap<String,Double> noteContainer = new HashMap<String,Double>() {{
        put("Burger-king-Limonest",3.7);
        put("McDo-Decines",2.8);
        put("Subway-Vaulx",3.3);
        put("KFC-Saint-Priest",3.6);
        put("Wok-Villeurbanne",4.4);
        put("La-Marinade",4.2);
        put("Confluence",4.5);
        put("Escape-Game-Perrache",4.8);
        put("Laser-Game-Genas",4.3);
        put("Bowling-Bron",3.7);
        put("Cinema-Brignais",4.1);
        put("Parc-de-la-tete-d'or",4.6);
    }};
    @FXML
    private Rating rating;


    @FXML
    private Label result;


    @FXML
    private MFXComboBox<String> mfxComboBox;

    @FXML
    private MFXFilterComboBox<String> mfxFilterComboBox;


    private Fonctionnalites Graph;

    private ArrayList<Places> placesArray;

    private ArrayList<String> nameOfPlacesArray = new ArrayList<String>();

    private ArrayList<String> tempSearchPlaces = new ArrayList<String>();


    private Places endroit;
    private String endroit_name;


    @FXML
    private SplitPane splitPane;


    @FXML
    public void closeApp(ActionEvent event) {
        Platform.exit();
    }


    @FXML
    private FontIcon iconCity;

    public void fillChoix(ArrayList<Places> endroits) {
        for(Places endroit_ajout: endroits) {
            this.mfxComboBox.getItems().add(endroit_ajout.getName());
            this.mfxFilterComboBox.getItems().add(endroit_ajout.getName());
            nameOfPlacesArray.add(endroit_ajout.getName());
        }
    }


    public void fillGraph(Fonctionnalites Graph_to_insert) {
        this.Graph = Graph_to_insert;
        this.placesArray = this.Graph.getPlacesArray();
        fillChoix(placesArray);
        this.endroit = this.placesArray.get(0);
        this.mfxComboBox.setValue(this.endroit.getName());
    }

    public Places getRequestedPlace(String placeName) {
        for(Places sommet: placesArray) {
            if (sommet.getName().equals(placeName)) {
                return sommet;
            }
        }
        return null;
    }

    public void putIcon(Label label, String type) {
        FontIcon icon = new FontIcon();
        icon.setIconColor(Color.THISTLE);
        if (type.equals("Ville")) {
            icon.setIconLiteral("mdal-home_work");
            icon.setIconSize(32);
            label.setGraphic(icon);
            return;
        }
        if (type.equals("Restaurant")) {
            icon.setIconLiteral("ci-restaurant");
            icon.setIconSize(45);
            label.setGraphic(icon);
            return;
        }
        icon.setIconLiteral("map-icon-amusement-park");
        icon.setIconSize(40);
        label.setGraphic(icon);
    }

    @FXML
    private MFXRadioButton radioDirect;

    @FXML
    private Label leurSite;

    @FXML
    private JFXListView<String> resultList;

    @FXML
    private Label descEndroit;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    public void getNeighborsOfNeighbor(ActionEvent event) {
        setResultWithEndroit(null);
    }


    @FXML
    public void setResultWithEndroit(String inCaseOfError) {

        Tab current_tab = tabPane.getSelectionModel().getSelectedItem();
        AnchorPane current_anchor_pane = (AnchorPane) current_tab.getContent();
        Label result_to_change = (Label) current_anchor_pane.getChildren().get(0);
        JFXListView<String> list_to_change = (JFXListView<String>) current_anchor_pane.getChildren().get(1);


        if (inCaseOfError == null) {
            list_to_change.setBackground(new Background(new BackgroundFill(Color.TURQUOISE,null,null)));

            ArrayList<Places> requestResult;
            if (radioDirect.isSelected()) {
                requestResult = Graph.getVoisins(endroit);
                result_to_change.setText("Les voisins directs de "+ this.endroit_name + " sont:\n");
            }
            else {
                requestResult = Graph.getVoisins2Dist(endroit);
                result_to_change.setText("Les voisins à 2-Distance de "+ this.endroit_name + " sont:\n");
            }

            list_to_change.getItems().clear();
            for (Places endroit: requestResult) {
                this.endroit_name = endroit.getName();
                list_to_change.getItems().add(this.endroit_name);
            }

            if (list_to_change.getItems().size()<=5) {
                if (list_to_change.getItems().size()>2) list_to_change.setPrefHeight(list_to_change.getItems().size() * 59.5);
                else list_to_change.setPrefHeight(list_to_change.getItems().size()*59.65);
            }
            else list_to_change.setPrefHeight(5 * 59.5);

            list_to_change.setVisible(true);

            list_to_change.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                Tab current_tab = tabPane.getSelectionModel().getSelectedItem();
                AnchorPane current_anchor_pane = (AnchorPane) current_tab.getContent();
                JFXListView<String> list_to_change = (JFXListView<String>) current_anchor_pane.getChildren().get(1);
                Label current_desc = (Label) current_anchor_pane.getChildren().get(2);
                Label current_leur_site = (Label) current_anchor_pane.getChildren().get(3);
                Hyperlink current_link = (Hyperlink) current_anchor_pane.getChildren().get(4);
                MFXButton current_button = (MFXButton) current_anchor_pane.getChildren().get(5);
                Rating current_rating = (Rating) current_anchor_pane.getChildren().get(6);
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    endroit_name = list_to_change.getSelectionModel().getSelectedItem();
                    endroit = getRequestedPlace(endroit_name);
                    current_desc.setText(endroit_name + " est un " + endroit.getType());
                    current_button.setVisible(true);
                    putIcon(current_desc,endroit.getType());
                    current_link.setText(siteLinksContainer.get(endroit_name));
                    if (!endroit.getType().equals("Ville")) {
                        current_leur_site.setText("Leur site:\n\nNotation Google:");
                        current_rating.setRating(noteContainer.get(endroit_name));
                        current_rating.setVisible(true);
                    }
                    else {
                        current_rating.setVisible(false);
                        current_leur_site.setText("Leur site:");
                    }
                }
            });
        }

        else result_to_change.setText(inCaseOfError);

    }

    @FXML
    private MFXButton connaitreSesVoisins;

    @FXML
    private Hyperlink linkToPlaceSite;

    @FXML
    public void goToWebsite(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(linkToPlaceSite.getText()));
    }


    @FXML
    private AnchorPane splitPaneLeft;
    @FXML
    private AnchorPane splitPaneRight;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tooltip tooltipBoutonNouveau;
    @FXML
    private Tooltip tooltipFilterComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tooltipBoutonNouveau.setShowDelay(Duration.millis(225));
        tooltipFilterComboBox.setShowDelay(Duration.millis(225));

       // linkToPlaceSite.layoutYProperty().bindBidirectional(leurSite.layoutYProperty());


        splitPaneLeft.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.68));

        splitPaneRight.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.68));


        tabPane.getTabs().get(0).setText("Requête 1");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);

        /*
        resultList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            Tab current_tab = tabPane.getSelectionModel().getSelectedItem();
            AnchorPane current_anchor_pane = (AnchorPane) current_tab.getContent();
            JFXListView<String> list_to_change = (JFXListView<String>) current_anchor_pane.getChildren().get(1);
            Label current_desc = (Label) current_anchor_pane.getChildren().get(2);
            Label current_leur_site = (Label) current_anchor_pane.getChildren().get(3);
            Hyperlink current_link = (Hyperlink) current_anchor_pane.getChildren().get(4);
            MFXButton current_button = (MFXButton) current_anchor_pane.getChildren().get(5);
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                endroit_name = list_to_change.getSelectionModel().getSelectedItem();
                endroit = getRequestedPlace(endroit_name);
                current_desc.setText(endroit_name + " est un " + endroit.getType());
                current_button.setVisible(true);
                putIcon(current_desc,endroit.getType());
                current_leur_site.setText("Leur site:");
                current_link.setText(siteLinksContainer.get(endroit_name));
            }
        });
        */
    }


    @FXML
    public void getEndroit2(MouseEvent event) {
        mfxComboBox.setOnAction(this::putEndroit2);
    }

    @FXML
    public void getEndroit3(MouseEvent event) {
        mfxFilterComboBox.setOnAction(this::putEndroit3);
    }


    public void putEndroit2(ActionEvent event) {
        this.endroit_name = mfxComboBox.getValue();
        this.endroit = getRequestedPlace(endroit_name);
        setResultWithEndroit(null);
        // new Bounce(result).play();
    }

    public void putEndroit3(ActionEvent event) {
        this.endroit_name = mfxFilterComboBox.getValue();
        this.endroit = getRequestedPlace(endroit_name);
        setResultWithEndroit(null);
    }

    /*
    @FXML
    public void doneChoosing(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER) {
            this.endroit_name = myComboBox.getValue();
            this.endroit_name = endroit_name.substring(0, 1).toUpperCase() + endroit_name.substring(1).toLowerCase();
            System.out.println(this.endroit_name);
            this.endroit = getRequestedPlace(endroit_name);

            try {
                setResultWithEndroit(null);
            }
            catch(Exception e) {
                setResultWithEndroit("Il n'existe pas de ville de ce nom");
            }

            new Flash(result).playOnFinished(new JackInTheBox(result)).play();
        }
    }
    */

    @FXML
    private MFXButton backButton;

    private PageAccueilController mainController;

    @FXML
    public void closeFunctionnality(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private AnchorPane resultContainer;

    @FXML
    private Label labelWarning;
    @FXML
    private FontIcon iconWarningLeft;
    @FXML
    private FontIcon iconWarningRight;

    @FXML
    private MFXButton boutonNouveau;

    private int numberOfTabs = 2;
    private int overflowsHappened = 0;

    @FXML
    public void createNewTabWindow(ActionEvent event) {
        if (numberOfTabs<11) {
            Tab newTab = createTab();
            tabPane.getTabs().add(newTab);
            numberOfTabs++;
            tabPane.getSelectionModel().selectNext();
        }
        else {
            boutonNouveau.setDisable(true);
            labelWarning.setText("VOUS NE POUVEZ PAS EFFECTUER PLUS DE 10 REQUÊTES EN MÊME TEMPS");
            iconWarningLeft.setVisible(true);
            iconWarningRight.setVisible(true);
            Flash flash = new Flash(labelWarning);
            flash.setOnFinished(e -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                labelWarning.setText("");
                iconWarningLeft.setVisible(false);
                iconWarningRight.setVisible(false);
                tabPane.getTabs().remove(0,8+overflowsHappened);
                numberOfTabs = 2;
                overflowsHappened = 1;
                boutonNouveau.setDisable(false);
                new Wobble(splitPane).play();
            });
            new Flash(iconWarningLeft).play();
            flash.play();
        }
    }

    public Tab createTab() {
        Tab tab = new Tab("Requête " + numberOfTabs);
        AnchorPane ap = new AnchorPane();
        duplicateResultContainer(ap);
        ap.getStyleClass().add("resultContainers");
        tab.setContent(ap);
        return tab;
    }
    public void duplicateResultContainer(AnchorPane anchorPane) {
        Label label = new Label();
        label.setText("Effectuez une requête pour voir ici les résultats");
        label.getStyleClass().add("results");
        label.alignmentProperty().bind(result.alignmentProperty());
        label.layoutXProperty().bindBidirectional(result.layoutXProperty());
        label.layoutYProperty().bindBidirectional(result.layoutYProperty());
        label.prefWidthProperty().bind(result.prefWidthProperty());
        label.prefHeightProperty().bind(result.prefHeightProperty());
        label.setWrapText(true);


        JFXListView<String> jfxListView = new JFXListView<String>();
        jfxListView.getStyleClass().add("resultLists");
        jfxListView.layoutXProperty().bindBidirectional(resultList.layoutXProperty());
        jfxListView.layoutYProperty().bindBidirectional(resultList.layoutYProperty());
        jfxListView.prefWidthProperty().bind(resultList.prefWidthProperty());
        jfxListView.setVisible(false);



        Label label2 = new Label();
        label2.getStyleClass().add("results");
        label2.alignmentProperty().bind(descEndroit.alignmentProperty());
        label2.layoutXProperty().bindBidirectional(descEndroit.layoutXProperty());
        label2.layoutYProperty().bindBidirectional(descEndroit.layoutYProperty());
        label2.prefWidthProperty().bind(descEndroit.prefWidthProperty());
        label2.prefHeightProperty().bind(descEndroit.prefHeightProperty());
        label2.setContentDisplay(ContentDisplay.RIGHT);

        Label label3 = new Label();
        label3.getStyleClass().add("results");
        label3.alignmentProperty().bind(leurSite.alignmentProperty());
        label3.layoutXProperty().bindBidirectional(leurSite.layoutXProperty());
        label3.layoutYProperty().bindBidirectional(leurSite.layoutYProperty());
        label3.prefWidthProperty().bind(leurSite.prefWidthProperty());
        label3.prefHeightProperty().bind(leurSite.prefHeightProperty());

        Hyperlink hyperlink = new Hyperlink();
        hyperlink.setFont(new Font(22));
        hyperlink.setTextFill(Color.web("#00A4FF"));
        hyperlink.alignmentProperty().bind(linkToPlaceSite.alignmentProperty());
        hyperlink.layoutXProperty().bindBidirectional(linkToPlaceSite.layoutXProperty());
        hyperlink.layoutYProperty().bindBidirectional(linkToPlaceSite.layoutYProperty());
        hyperlink.prefWidthProperty().bind(linkToPlaceSite.prefWidthProperty());
        hyperlink.prefHeightProperty().bind(linkToPlaceSite.prefHeightProperty());

        MFXButton mfxButton = new MFXButton("CONNAITRE SES VOISINS");
        mfxButton.setFont(new Font(17));
        mfxButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getNeighborsOfNeighbor(event);
            }
        });

        mfxButton.getStyleClass().add("connaitreSesVoisinsClass");
        mfxButton.layoutXProperty().bindBidirectional(connaitreSesVoisins.layoutXProperty());
        mfxButton.layoutYProperty().bindBidirectional(connaitreSesVoisins.layoutYProperty());
        mfxButton.prefWidthProperty().bind(connaitreSesVoisins.prefWidthProperty());
        mfxButton.prefHeightProperty().bind(connaitreSesVoisins.prefHeightProperty());
        mfxButton.setVisible(false);



        Rating new_rating = new Rating();
        new_rating.layoutXProperty().bindBidirectional(rating.layoutXProperty());
        new_rating.layoutYProperty().bindBidirectional(rating.layoutYProperty());
        new_rating.setVisible(false);
        new_rating.setPartialRating(true);


        anchorPane.getChildren().addAll(label,jfxListView,label2,label3,hyperlink,mfxButton,new_rating);
    }


}