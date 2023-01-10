package com.example.javafx_essai1;



import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.util.Duration;
import org.controlsfx.control.Rating;
import org.kordamp.ikonli.javafx.FontIcon;
import shichimifx.SplitPaneDividerSlider;


public class PageComparaisonController implements Initializable {

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

    private Fonctionnalites Graph;

    private ArrayList<Places> placesArray;

    private ArrayList<String> nameOfPlacesArray = new ArrayList<String>();

    private ArrayList<String> tempSearchPlaces = new ArrayList<String>();


    private ArrayList<Places> resultsFirstPlace_open;
    private ArrayList<Places> resultsSecondPlace_open;

    private ArrayList<Places> resultsFirstPlace_gastronomy;
    private ArrayList<Places> resultsSecondPlace_gastronomy;

    private ArrayList<Places> resultsFirstPlace_culture;
    private ArrayList<Places> resultsSecondPlace_culture;


    private Places endroit;
    private String endroit_name;

    private Places endroit2;
    private String endroit_name2;


    /**
     * remplis la variable Graph qui servira à obtenir les voisins
     * @param Graph_to_insert graph (de la classe fonctionnalite) obtenu par le controlleur Accueil
     */
    public void fillGraph(Fonctionnalites Graph_to_insert) {
        this.Graph = Graph_to_insert;
        this.placesArray = this.Graph.getPlacesArray();
        fillChoix(this.placesArray);
    }

    /**
    remplis les combobox
    @param endroits l'arraylist des endroits
     */
    public void fillChoix(ArrayList<Places> endroits) {
        for(Places endroit_ajout: endroits) {
            this.mfxFilterComboBox_left.getItems().add(endroit_ajout.getName());
            this.mfxComboBox_left.getItems().add(endroit_ajout.getName());
            this.mfxFilterComboBox_right.getItems().add(endroit_ajout.getName());
            this.mfxComboBox_right.getItems().add(endroit_ajout.getName());
            nameOfPlacesArray.add(endroit_ajout.getName());
        }
        /*
        this.mfxComboBox.setGraphicTextGap(12.76);
        this.mfxComboBox.setLeadingIcon(iconCity);
        */
    }

    /**
    @param placeName le nom de l'endroit en question
    @return l'object endroit grâce à son nom
     */
    public Places getRequestedPlace(String placeName) {
        for(Places sommet: placesArray) {
            if (sommet.getName().equals(placeName)) {
                return sommet;
            }
        }
        return null;
    }

    /**
     * Met un icon sur le label entré en paramètre du type entré en paramètre
     * @param label label sur lequel mettre l'icon
     * @param type type de l'icon (restaurant, ville ou loisirs)
     */
    public void putIcon(Label label, String type) {
        FontIcon icon = new FontIcon();
        icon.setIconColor(Color.MISTYROSE);
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
    private SplitPane splitPane;

    @FXML
    private AnchorPane splitPaneLeft;
    @FXML
    private AnchorPane splitPaneMiddle;
    @FXML
    private AnchorPane splitPaneRight;


    /**
     * Méthode appellée au moment de la génération de la page pour initialiser ce qui doit l'être (ici c'est des affectations de listeners)
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        ChangeListener<Number> Placements = (observable, oldValue, newValue) -> {
            resultsToggleButton.setLayoutX((double) newValue/2-resultsToggleButton.getPrefWidth()/2);
            comparisonChoice.setLayoutX((double) newValue/2-comparisonChoice.getPrefWidth()/2);
            secondPlaceResultsContainer.setLayoutX((double) newValue-10-secondPlaceResultsContainer.getPrefWidth());
            resultsInfo.setLayoutX((double) newValue/2-resultsInfo.getPrefWidth()/2);
        };


        splitPaneMiddle.widthProperty().addListener(Placements);


        SplitPaneDividerSlider splitPaneAnimationLeft = new SplitPaneDividerSlider(splitPane,0, SplitPaneDividerSlider.Direction.LEFT);
        SplitPaneDividerSlider splitPaneAnimationRight = new SplitPaneDividerSlider(splitPane,1, SplitPaneDividerSlider.Direction.RIGHT);

        resultsToggleButton.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            if (endroit!=null && endroit2!=null) {
                splitPaneAnimationLeft.setAimContentVisible(!t1);
                splitPaneAnimationRight.setAimContentVisible(!t1);
                if (t1) {
                    showResults();
                }
                else {
                    firstPlaceResultsContainer.setVisible(false);
                    secondPlaceResultsContainer.setVisible(false);
                    resultsInfo.setVisible(false);
                    comparisonChoice.setVisible(false);
                }
            }
            else {
                resultsToggleButton.setSelected(false);
                placesNotChosenWarning();
            }
        });

        firstResultList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String new_endroit_name = firstResultList.getSelectionModel().getSelectedItem();
                Places new_endroit = getRequestedPlace(new_endroit_name);

                firstDescEndroit.setText(new_endroit_name + " est un " + new_endroit.getType());
               // current_button.setVisible(true);
                putIcon(firstDescEndroit,new_endroit.getType());
                firstLinkToPlaceSite.setText(siteLinksContainer.get(new_endroit_name));
                if (!new_endroit.getType().equals("Ville")) {
                    firstLeurSite.setText("Leur site:\n\nNotation Google:");
                    firstRating.setRating(noteContainer.get(new_endroit_name));
                    firstRating.setVisible(true);
                }
                else {
                    firstRating.setVisible(false);
                    firstLeurSite.setText("Leur site:");
                }

            }
        });

        secondResultList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String new_endroit_name2 = secondResultList.getSelectionModel().getSelectedItem();
                Places new_endroit2 = getRequestedPlace(new_endroit_name2);

                secondDescEndroit.setText(new_endroit_name2 + " est un " + new_endroit2.getType());
                //current_button.setVisible(true);
                putIcon(secondDescEndroit,new_endroit2.getType());
                secondLinkToPlaceSite.setText(siteLinksContainer.get(new_endroit_name2));
                if (!new_endroit2.getType().equals("Ville")) {
                    secondLeurSite.setText("Leur site:\n\nNotation Google:");
                    secondRating.setRating(noteContainer.get(new_endroit_name2));
                    secondRating.setVisible(true);
                }
                else {
                    secondRating.setVisible(false);
                    secondLeurSite.setText("Leur site:");
                }
            }
        });


    }


    @FXML
    private MFXFilterComboBox<String> mfxFilterComboBox_left;

    @FXML
    private MFXComboBox<String> mfxComboBox_left;


    @FXML
    private MFXFilterComboBox<String> mfxFilterComboBox_right;

    @FXML
    private MFXComboBox<String> mfxComboBox_right;


    /**
     * Dès qu'on clique sur le 1er combobox, met en place la méthode pour attendre que l'utilisateur clique sur l'endroit désiré
     */
    @FXML
    public void getEndroit(MouseEvent event) {
        mfxFilterComboBox_left.setOnAction(this::putEndroit);
    }

    /**
     * Dès qu'on clique sur le 2ème combobox, met en place la méthode pour attendre que l'utilisateur clique sur l'endroit désiré
     */
    @FXML
    public void getEndroit2(MouseEvent event) {
        mfxComboBox_left.setOnAction(this::putEndroit2);
    }

    /**
     * Dès qu'on clique sur le 3ème combobox, met en place la méthode pour attendre que l'utilisateur clique sur l'endroit désiré
     */
    @FXML
    public void getEndroit3(MouseEvent event) {
        mfxFilterComboBox_right.setOnAction(this::putEndroit3);
    }

    /**
     * Dès qu'on clique sur le 4ème combobox, met en place la méthode pour attendre que l'utilisateur clique sur l'endroit désiré
     */
    @FXML
    public void getEndroit4(MouseEvent event) {
        mfxComboBox_right.setOnAction(this::putEndroit4);
    }

    @FXML
    private Label tellChosenPlace_left;
    @FXML
    private Label tellChosenPlace_right;

    /**
     * attend que l'utilisateur sélectionne un endroit dans la 1ère combobox pour le mettre dans la variable qui sera utilisé et de mettre à jour le label pour indiquer à l'utilisateur sa sélection
     * @param event nécessite l'élément qui a été sélectionné
     */
    public void putEndroit(ActionEvent event) {
        this.endroit_name = mfxFilterComboBox_left.getValue();
        this.endroit = getRequestedPlace(endroit_name);
        tellChosenPlace_left.setText("Endroit 1: "+this.endroit_name);
        // new Bounce(result).play();
    }

    /**
     * attend que l'utilisateur sélectionne un endroit dans la 2ème combobox pour le mettre dans la variable qui sera utilisé et de mettre à jour le label pour indiquer à l'utilisateur sa sélection
     * @param event nécessite l'élément qui a été sélectionné
     */
    public void putEndroit2(ActionEvent event) {
        endroit_name = mfxComboBox_left.getValue();
        endroit = getRequestedPlace(endroit_name);
        tellChosenPlace_left.setText("Endroit 1: "+endroit_name);
        // new Bounce(result).play();
    }

    /**
     * attend que l'utilisateur sélectionne un endroit dans la 3ème combobox pour le mettre dans la variable qui sera utilisé et de mettre à jour le label pour indiquer à l'utilisateur sa sélection
     * @param event nécessite l'élément qui a été sélectionné
     */
    public void putEndroit3(ActionEvent event) {
        this.endroit_name2 = mfxFilterComboBox_right.getValue();
        this.endroit2 = getRequestedPlace(endroit_name2);
        tellChosenPlace_right.setText("Endroit 2: "+this.endroit_name2);
        // new Bounce(result).play();
    }

    /**
     * attend que l'utilisateur sélectionne un endroit dans la 4ème combobox pour le mettre dans la variable qui sera utilisé et de mettre à jour le label pour indiquer à l'utilisateur sa sélection
     * @param event nécessite l'élément qui a été sélectionné
     */
    public void putEndroit4(ActionEvent event) {
        this.endroit_name2 = mfxComboBox_right.getValue();
        this.endroit2 = getRequestedPlace(endroit_name2);
        tellChosenPlace_right.setText("Endroit 2: "+this.endroit_name2);
        // new Bounce(result).play();
    }


    /**
     * extinction générale on ferme tout
     * @param event le bouton a été cliqué
     */
    @FXML
    public void closeApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private MFXButton backButton;

    /**
     * on retourne à l'écran d'accueil
     * @param event le bouton retour a été cliqué
     */
    @FXML
    public void closeFunctionnality(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Label labelWarning;
    @FXML
    private FontIcon iconWarningLeft;
    @FXML
    private FontIcon iconWarningRight;

    /**
     * Met un warning clignotant si on tente de générer les résultats alors que on a pas encore sélectionné les 2 endroits
     */
    public void placesNotChosenWarning() {
        labelWarning.setText("VOUS DEVEZ CHOISIR 2 ENDROITS POUR AVOIR LES RESULTATS");
        iconWarningLeft.setVisible(true);
        iconWarningRight.setVisible(true);
        Flash flash = new Flash(labelWarning);
        flash.setOnFinished(e -> {
            try {
                Thread.sleep(1400);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            labelWarning.setText("");
            iconWarningLeft.setVisible(false);
            iconWarningRight.setVisible(false);
            if (endroit==null) {
                Flip anim = new Flip(mfxComboBox_left);
                anim.setSpeed(2.8);
                anim.setCycleCount(16);
                anim.play();
                new Flip(mfxFilterComboBox_left).play();
                Flip anim2 = new Flip(mfxFilterComboBox_left);
                anim2.setSpeed(2.8);
                anim2.setCycleCount(16);
                anim2.play();
            }
            if (endroit2==null) {
                Flip anim3 = new Flip(mfxComboBox_right);
                anim3.setSpeed(2.8);
                anim3.setCycleCount(16);
                anim3.play();

                Flip anim4 = new Flip(mfxFilterComboBox_right);
                anim4.setSpeed(2.8);
                anim4.setCycleCount(16);
                anim4.play();
            }
        });
        new Flash(iconWarningLeft).play();
        flash.play();
    }

    @FXML
    private Pane firstPlaceResultsContainer;
    @FXML
    private Pane secondPlaceResultsContainer;

    @FXML
    private MFXButton resultsButton;
    @FXML
    private JFXButton jfxResultsButton;

    @FXML
    private MFXToggleButton resultsToggleButton;

    private boolean sidesHidden = false;


    @FXML
    private Label firstDescEndroit;
    @FXML
    private Label firstLeurSite;

    @FXML
    private Hyperlink firstLinkToPlaceSite;

    @FXML
    private Rating firstRating;

    /**
     * ouvre le navigateur du pc et renvoie sur le lien de l'endroit
     * @param event on a cliqué sur le 1er lien
     * @throws URISyntaxException renvoie une exception si le lien est invalide
     */
    @FXML
    public void goToWebsiteFirst(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(firstLinkToPlaceSite.getText()));
    }

    /**
     * ouvre le navigateur du pc et renvoie sur le lien de l'endroit
     * @param event on a cliqué sur le 2ème lien
     * @throws URISyntaxException renvoie une exception si le lien est invalide
     */
    @FXML
    public void goToWebsiteSecond(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI(secondLinkToPlaceSite.getText()));
    }

    @FXML
    private Label secondDescEndroit;
    @FXML
    private Label secondLeurSite;

    @FXML
    private Hyperlink secondLinkToPlaceSite;

    @FXML
    private Rating secondRating;


    @FXML
    private HBox comparisonChoice;


    @FXML
    private Label firstPlaceNameInResults;
    @FXML
    private Label secondPlaceNameInResults;

    @FXML
    private Label resultsInfo;

    /**
     * joue l'animation d'ouverture et met les résultats et les rend visibles
     */
    public void showResults() {
        firstPlaceResultsContainer.setVisible(true);
        secondPlaceResultsContainer.setVisible(true);
        resultsInfo.setVisible(true);
        comparisonChoice.setVisible(true);

        resultsFirstPlace_open = Graph.getVoisins2DistOfType(endroit,"Ville");
        resultsSecondPlace_open = Graph.getVoisins2DistOfType(endroit2,"Ville");
        resultsFirstPlace_gastronomy = Graph.getVoisins2DistOfType(endroit,"Restaurant");
        resultsSecondPlace_gastronomy = Graph.getVoisins2DistOfType(endroit2,"Restaurant");
        resultsFirstPlace_culture = Graph.getVoisins2DistOfType(endroit,"Loisir");
        resultsSecondPlace_culture = Graph.getVoisins2DistOfType(endroit2,"Loisir");


        firstResultList.setVisible(true);
        secondResultList.setVisible(true);
        fillListsOpen();
    }

    /**
     * affiche les résultats de la comparaison dans le label au milieu
     */
    public void putTextResults(String type) {
        if(type.equals("Ville")) {
            resultsInfo.setText(Graph.plusOuverte(endroit,endroit2) + "\n\n  - "+endroit_name+": " + resultsFirstPlace_open.size() + " villes à 2-Distance" + "\n\n  - "+endroit_name2+": " + resultsSecondPlace_open.size() + " villes à 2-Distance");
            return;
        }
        else if(type.equals("Restaurant")) {
            resultsInfo.setText(Graph.plusGastronomique(endroit,endroit2) + "\n\n  - "+endroit_name+": " + resultsFirstPlace_gastronomy.size() + " restaurants à 2-Distance" + "\n\n  - "+endroit_name2+": " + resultsSecondPlace_gastronomy.size() + " restaurants à 2-Distance");
            return;
        }
        resultsInfo.setText(Graph.plusCulturelle(endroit,endroit2) + "\n\n  - "+endroit_name+": " + resultsFirstPlace_culture.size() + " centres de loisirs à 2-Distance" + "\n\n  - "+endroit_name2+": " + resultsSecondPlace_culture.size() + " centres de loisirs à 2-Distance");
    }




    @FXML
    private JFXListView<String> firstResultList;
    @FXML
    private JFXListView<String> secondResultList;

    /**
     * remplit les listes (appeléé dès qu'on clique sur le radiobutton correspondant et à l'affichage des résultats)
     * @see #showResults
     */
    @FXML
    public void fillListsOpen() {
        firstPlaceNameInResults.setText(endroit_name+":");
        secondPlaceNameInResults.setText(endroit_name2+":");
        putTextResults("Ville");
        String new_endroit_name;
        firstResultList.getItems().clear();
        for (Places endroit: resultsFirstPlace_open) {
            new_endroit_name = endroit.getName();
            firstResultList.getItems().add(new_endroit_name);
        }

        if (firstResultList.getItems().size()==0) {
            firstPlaceNameInResults.setText(firstPlaceNameInResults.getText()+"\n\n " + firstPlaceNameInResults.getText() + " n'a pas de villes à 2-Distance");
        }
        if (firstResultList.getItems().size()<=5) {
            firstResultList.setPrefHeight(firstResultList.getItems().size() * 59.5);
        }
        else firstResultList.setPrefHeight(5 * 59.5);

        /* 2e liste */

        secondResultList.getItems().clear();
        for (Places endroit2: resultsSecondPlace_open) {
            new_endroit_name = endroit2.getName();
            secondResultList.getItems().add(new_endroit_name);
        }

        if (secondResultList.getItems().size()==0) {
            secondPlaceNameInResults.setText(secondPlaceNameInResults.getText()+"\n\n " + secondPlaceNameInResults.getText() + " n'a pas de villes à 2-Distance");
        }
        if (secondResultList.getItems().size()<=5) {
            secondResultList.setPrefHeight(secondResultList.getItems().size() * 59.5);
        }
        else secondResultList.setPrefHeight(5 * 59.5);

    }

    /**
     * remplis les listes (appeléé dès qu'on clique sur le radiobutton correspondant)
     */
    @FXML
    public void fillListsGastronomy() {
        System.out.println(endroit + "  " + endroit2);
        firstPlaceNameInResults.setText(endroit_name+":");
        secondPlaceNameInResults.setText(endroit_name2+":");
        putTextResults("Restaurant");
        String new_endroit_name;
        firstResultList.getItems().clear();
        for (Places endroit: resultsFirstPlace_gastronomy) {
            new_endroit_name = endroit.getName();
            firstResultList.getItems().add(new_endroit_name);
        }

        if (firstResultList.getItems().size()==0) {
            firstPlaceNameInResults.setText(firstPlaceNameInResults.getText()+"\n\n " + firstPlaceNameInResults.getText() + " n'a pas de villes à 2-Distance");
        }
        if (firstResultList.getItems().size()<=5) {
            firstResultList.setPrefHeight(firstResultList.getItems().size() * 59.5);
        }
        else firstResultList.setPrefHeight(5 * 59.5);

        /* 2e liste */

        secondResultList.getItems().clear();
        for (Places endroit2: resultsSecondPlace_gastronomy) {
            new_endroit_name = endroit2.getName();
            secondResultList.getItems().add(new_endroit_name);
        }

        if (secondResultList.getItems().size()==0) {
            secondPlaceNameInResults.setText(secondPlaceNameInResults.getText()+"\n\n " + secondPlaceNameInResults.getText() + " n'a pas de villes à 2-Distance");
        }
        if (secondResultList.getItems().size()<=5) {
            secondResultList.setPrefHeight(secondResultList.getItems().size() * 59.5);
        }
        else secondResultList.setPrefHeight(5 * 59.5);
    }

    /**
     * remplis les listes (appeléé dès qu'on clique sur le radiobutton correspondant)
     */
    @FXML
    public void fillListsCulture() {
        firstPlaceNameInResults.setText(endroit_name+":");
        secondPlaceNameInResults.setText(endroit_name2+":");
        putTextResults("Loisir");
        String new_endroit_name;
        firstResultList.getItems().clear();
        for (Places endroit: resultsFirstPlace_culture) {
            new_endroit_name = endroit.getName();
            firstResultList.getItems().add(new_endroit_name);
        }

        if (firstResultList.getItems().size()==0) {
            firstPlaceNameInResults.setText(firstPlaceNameInResults.getText()+"\n\n " + firstPlaceNameInResults.getText() + " n'a pas de villes à 2-Distance");
        }
        if (firstResultList.getItems().size()<=5) {
            firstResultList.setPrefHeight(firstResultList.getItems().size() * 59.5);
        }
        else firstResultList.setPrefHeight(5 * 59.5);

        /* 2e liste */

        secondResultList.getItems().clear();
        for (Places endroit2: resultsSecondPlace_culture) {
            new_endroit_name = endroit2.getName();
            secondResultList.getItems().add(new_endroit_name);
        }

        if (secondResultList.getItems().size()==0) {
            secondPlaceNameInResults.setText(secondPlaceNameInResults.getText()+"\n\n " + secondPlaceNameInResults.getText() + " n'a pas de villes à 2-Distance");
        }
        if (secondResultList.getItems().size()<=5) {
            secondResultList.setPrefHeight(secondResultList.getItems().size() * 59.5);
        }
        else secondResultList.setPrefHeight(5 * 59.5);
    }
}
