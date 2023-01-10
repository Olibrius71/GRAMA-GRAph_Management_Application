package com.example.javafx_essai1;

import com.jfoenix.controls.JFXListView;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PageDeuxVoisinsController implements Initializable {

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
        put("Burger-King-Limonest","https://www.burgerking.fr/restaurant/lyon-limonest");
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

    private Fonctionnalites Graph;

    private ArrayList<Places> placesArray;

    private ArrayList<String> nameOfPlacesArray = new ArrayList<String>();

    public void fillGraph(Fonctionnalites Graph_to_insert) {
        this.Graph = Graph_to_insert;
        this.placesArray = this.Graph.getPlacesArray();
        for (Places endroit_ajout: this.placesArray) {
            nameOfPlacesArray.add(endroit_ajout.getName());
        }
        fillLists();
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
        icon.setIconColor(Color.SLATEGREY);
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

    private Places endroit;

    private String endroit_name;

    @FXML
    private JFXListView<String> villesList;
    @FXML
    private JFXListView<String> restaurantsList;
    @FXML
    private JFXListView<String> loisirsList;

    public void fillLists() {
        for (Places endroit_fill: this.placesArray) {
            if (endroit_fill.getType().equals("Ville")) {
                villesList.getItems().add(endroit_fill.getName());
            }
            else if (endroit_fill.getType().equals("Restaurant")) {
                restaurantsList.getItems().add(endroit_fill.getName());
            }
            else {
                loisirsList.getItems().add(endroit_fill.getName());
            }
        }
        villesList.setPrefHeight(5*59.5);
        restaurantsList.setPrefHeight(5*59.5);
        loisirsList.setPrefHeight(5*59.5);
    }

    @FXML
    private FontIcon arrowIcon1;
    @FXML
    private FontIcon arrowIcon2;
    @FXML
    private FontIcon arrowIcon3;
    @FXML
    private FontIcon arrowIcon4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arrowIcon1.setRotate(270);
        arrowIcon2.setRotate(270);
        arrowIcon3.setRotate(270);
        arrowIcon4.setRotate(270);

        villesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                endroit_name = villesList.getSelectionModel().getSelectedItem();
                endroit = getRequestedPlace(endroit_name);

                descVille.setText(endroit_name + " est une " + endroit.getType());
                //current_button.setVisible(true);
                putIcon(descVille,endroit.getType());
                villeLink.setText(siteLinksContainer.get(endroit_name));
                villeLeurSite.setText("Leur site:");
            }
        });

        restaurantsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                endroit_name = restaurantsList.getSelectionModel().getSelectedItem();
                endroit = getRequestedPlace(endroit_name);

                descRestaurant.setText(endroit_name + " est un " + endroit.getType());
                //current_button.setVisible(true);
                putIcon(descRestaurant,endroit.getType());
                restaurantLink.setText(siteLinksContainer.get(endroit_name));
                restaurantLeurSite.setText("Leur site:\nNotation Google:");
                restaurantRating.setRating(noteContainer.get(endroit_name));
                restaurantRating.setVisible(true);

            }
        });

        loisirsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                endroit_name = loisirsList.getSelectionModel().getSelectedItem();
                endroit = getRequestedPlace(endroit_name);

                descLoisir.setText(endroit_name + " est un " + endroit.getType());
                //current_button.setVisible(true);
                putIcon(descLoisir,endroit.getType());
                loisirLink.setText(siteLinksContainer.get(endroit_name));
                loisirLeurSite.setText("Leur site:\nNotation Google:");
                loisirRating.setRating(noteContainer.get(endroit_name));
                loisirRating.setVisible(true);
            }
        });
    }


    @FXML
    private MFXButton backButton;

    @FXML
    public void closeApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void closeFunctionnality(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    private Label descVille;
    @FXML
    private Label villeLeurSite;
    @FXML
    private Hyperlink villeLink;

    @FXML
    private Label descRestaurant;
    @FXML
    private Label restaurantLeurSite;
    @FXML
    private Hyperlink restaurantLink;
    @FXML
    private Rating restaurantRating;

    @FXML
    private Label descLoisir;
    @FXML
    private Label loisirLeurSite;
    @FXML
    private Hyperlink loisirLink;
    @FXML
    private Rating loisirRating;

    @FXML
    public void goToWebsiteVille(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(villeLink.getText()));
    }
    @FXML
    public void goToWebsiteRestaurant(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(restaurantLink.getText()));
    }
    @FXML
    public void goToWebsiteLoisir(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(loisirLink.getText()));
    }
}
