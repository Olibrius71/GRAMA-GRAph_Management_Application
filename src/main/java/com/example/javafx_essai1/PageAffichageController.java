package com.example.javafx_essai1;

import animatefx.animation.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.effects.DepthLevel;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollToEvent;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;


import javax.swing.text.html.CSS;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import static io.github.palexdev.materialfx.utils.NumberUtils.clamp;
import static javafx.scene.input.MouseButton.FORWARD;


public class PageAffichageController implements Initializable {



    private HashMap<String,FontIcon> placeIconCorrespondance = new HashMap<String,FontIcon>();

    private HashMap<String,Label> placeLabelCorrespondance = new HashMap<String,Label>();




    private Fonctionnalites Graph;

    private ArrayList<Places> placesArray;

    private ArrayList<String> nameOfPlacesArray = new ArrayList<String>();

    public void fillGraph(Fonctionnalites Graph_to_insert) {
        this.Graph = Graph_to_insert;
        this.placesArray = this.Graph.getPlacesArray();
        fillHashMaps();
    }

    public void fillHashMaps() {
        placeIconCorrespondance.put("Villeurbanne",villeurbanneIcon);
        placeIconCorrespondance.put("Caluire",caluireIcon);
        placeIconCorrespondance.put("Rillieux",rillieuxIcon);
        placeIconCorrespondance.put("Marie-9e",marie9eIcon);
        placeIconCorrespondance.put("Fourviere",fourviereIcon);
        placeIconCorrespondance.put("Meyzieu",meyzieuIcon);
        placeIconCorrespondance.put("Macon",maconIcon);
        placeIconCorrespondance.put("Andrezieux",andrezieuxIcon);
        placeIconCorrespondance.put("Saint-etienne",saintEtienneIcon);
        placeIconCorrespondance.put("Venissieux",venissieuxIcon);
        placeIconCorrespondance.put("Roanne",roanneIcon);
        placeIconCorrespondance.put("Balbigny",balbignyIcon);
        placeIconCorrespondance.put("Digoin",digoinIcon);
        placeIconCorrespondance.put("Craponne",craponneIcon);
        placeIconCorrespondance.put("Villefranche",villefrancheIcon);
        placeIconCorrespondance.put("Genas",genasIcon);
        placeIconCorrespondance.put("L'arbresle",larbresleIcon);
        placeIconCorrespondance.put("Les-Echets",lesEchetsIcon);
        placeIconCorrespondance.put("Lapalisse",laPalisseIcon);
        placeIconCorrespondance.put("Burger-king-Limonest",burgerKingLimonestIcon);
        placeIconCorrespondance.put("McDo-Decines",mcDoDecinesIcon);
        placeIconCorrespondance.put("Subway-Vaulx",subwayVaulxIcon);
        placeIconCorrespondance.put("KFC-Saint-Priest",kfcSaintPriestIcon);
        placeIconCorrespondance.put("Wok-Villeurbanne",wokVilleurbanneIcon);
        placeIconCorrespondance.put("La-Marinade",laMarinadeIcon);
        placeIconCorrespondance.put("Confluence",confluenceIcon);
        placeIconCorrespondance.put("Escape-Game-Perrache",escapeGamePerracheIcon);
        placeIconCorrespondance.put("Laser-Game-Genas",laserGameGenasIcon);
        placeIconCorrespondance.put("Bowling-Bron",bowlingBronIcon);
        placeIconCorrespondance.put("Cinema-Brignais",cinemaBrignaisIcon);
        placeIconCorrespondance.put("Parc-de-la-tete-d'or",parcDeLaTeteDOrIcon);

        placeLabelCorrespondance.put("Villeurbanne",villeurbanneLabel);
        placeLabelCorrespondance.put("Caluire",caluireLabel);
        placeLabelCorrespondance.put("Rillieux",rillieuxLabel);
        placeLabelCorrespondance.put("Marie-9e",marie9eLabel);
        placeLabelCorrespondance.put("Fourviere",fourviereLabel);
        placeLabelCorrespondance.put("Meyzieu",meyzieuLabel);
        placeLabelCorrespondance.put("Macon",maconLabel);
        placeLabelCorrespondance.put("Andrezieux",andrezieuxLabel);
        placeLabelCorrespondance.put("Saint-etienne",saintEtienneLabel);
        placeLabelCorrespondance.put("Venissieux",venissieuxLabel);
        placeLabelCorrespondance.put("Roanne",roanneLabel);
        placeLabelCorrespondance.put("Balbigny",balbignyLabel);
        placeLabelCorrespondance.put("Digoin",digoinLabel);
        placeLabelCorrespondance.put("Craponne",craponneLabel);
        placeLabelCorrespondance.put("Villefranche",villefrancheLabel);
        placeLabelCorrespondance.put("Genas",genasLabel);
        placeLabelCorrespondance.put("L'arbresle",larbresleLabel);
        placeLabelCorrespondance.put("Les-Echets",lesEchetsLabel);
        placeLabelCorrespondance.put("Lapalisse",laPalisseLabel);
        placeLabelCorrespondance.put("Burger-king-Limonest",burgerKingLimonestLabel);
        placeLabelCorrespondance.put("McDo-Decines",mcDoDecinesLabel);
        placeLabelCorrespondance.put("Subway-Vaulx",subwayVaulxLabel);
        placeLabelCorrespondance.put("KFC-Saint-Priest",kfcSaintPriestLabel);
        placeLabelCorrespondance.put("Wok-Villeurbanne",wokVilleurbanneLabel);
        placeLabelCorrespondance.put("La-Marinade",laMarinadeLabel);
        placeLabelCorrespondance.put("Confluence",confluenceLabel);
        placeLabelCorrespondance.put("Escape-Game-Perrache",escapeGamePerracheLabel);
        placeLabelCorrespondance.put("Laser-Game-Genas",laserGameGenasLabel);
        placeLabelCorrespondance.put("Bowling-Bron",bowlingBronLabel);
        placeLabelCorrespondance.put("Cinema-Brignais",cinemaBrignaisLabel);
        placeLabelCorrespondance.put("Parc-de-la-tete-d'or",parcDeLaTeteDOrLabel);

        placeLineCorrespondance.put("Villeurbanne",new Line[]{routeVilleurbanneLesEchets,routeMaconVilleurbanne,routeVilleurbanneLaserGameGenas});
        placeLineCorrespondance.put("Caluire",new Line[]{routeCaluireEscapeGamePerrache,routeLarbresleCaluire,routeMarie9eCaluire,routeRillieuxCaluire});
        placeLineCorrespondance.put("Rillieux",new Line[]{routeConfluenceRillieux,routeRillieuxCaluire,routeMarie9eRillieux,routeRillieuxBowlingBron});
        placeLineCorrespondance.put("Marie-9e",new Line[]{routeMarie9eRillieux,routeMarie9eCaluire,routeFourviereMarie9e});
        placeLineCorrespondance.put("Fourviere",new Line[]{routeFourviereMarie9e,routeMcDoDecinesFourviere,routeLesEchetsFourviere});
        placeLineCorrespondance.put("Meyzieu",new Line[]{routeMeyzieuMacon,routeMeyzieuMcDoDecines,routeVillefrancheMeyzieu});
        placeLineCorrespondance.put("Macon",new Line[]{routeMaconVilleurbanne,routeMaconDigoin,routeMaconLesEchets,routeMeyzieuMacon});
        placeLineCorrespondance.put("Andrezieux",new Line[]{routeAndrezieuxSaintEtienne,routeLarbresleAndrezieux});
        placeLineCorrespondance.put("Saint-etienne",new Line[]{routeAndrezieuxSaintEtienne,routeSaintEtienneVenissieux});
        placeLineCorrespondance.put("Venissieux",new Line[]{routeSaintEtienneVenissieux,routeVenissieuxCinemaBrignais,routeVenissieuxParcDeLaTeteDOr});
        placeLineCorrespondance.put("Roanne",new Line[]{routeRoanneBalbigny,routeLaPalisseRoanne,routeRoanneCraponne});
        placeLineCorrespondance.put("Balbigny",new Line[]{routeRoanneBalbigny,routeBalbignyLarbresle});
        placeLineCorrespondance.put("Digoin",new Line[]{routeMaconDigoin,routeDigoinLaPalisse});
        placeLineCorrespondance.put("Craponne",new Line[]{routeRoanneCraponne,routeSubwayVaulxCraponne,routeCraponneKfcSaintPriest});
        placeLineCorrespondance.put("Villefranche",new Line[]{routeVillefrancheMeyzieu,routeLaMarinadeVillefranche});
        placeLineCorrespondance.put("Genas",new Line[]{routeWokVilleurbanneGenas});
        placeLineCorrespondance.put("L'arbresle",new Line[]{routeBalbignyLarbresle,routeLarbresleAndrezieux,routeLarbresleCaluire});
        placeLineCorrespondance.put("Les-Echets",new Line[]{routeMaconLesEchets,routeVilleurbanneLesEchets,routeLesEchetsConfluence,routeLesEchetsFourviere});
        placeLineCorrespondance.put("Lapalisse",new Line[]{routeDigoinLaPalisse,routeLaPalisseRoanne});
        placeLineCorrespondance.put("Burger-king-Limonest",new Line[]{routeCinemaBrignaisBurgerKingLimonest});
        placeLineCorrespondance.put("McDo-Decines",new Line[]{routeMeyzieuMcDoDecines,routeMcDoDecinesFourviere,routeMcDoDecinesConfluence});
        placeLineCorrespondance.put("Subway-Vaulx",new Line[]{routeSubwayVaulxCraponne});
        placeLineCorrespondance.put("KFC-Saint-Priest",new Line[]{routeCraponneKfcSaintPriest});
        placeLineCorrespondance.put("Wok-Villeurbanne",new Line[]{routeEscapeGamePerracheWokVilleurbanne,routeWokVilleurbanneGenas});
        placeLineCorrespondance.put("La-Marinade",new Line[]{routeLaMarinadeVillefranche});
        placeLineCorrespondance.put("Confluence",new Line[]{routeMcDoDecinesConfluence,routeLesEchetsConfluence,routeConfluenceRillieux});
        placeLineCorrespondance.put("Escape-Game-Perrache",new Line[]{routeEscapeGamePerracheWokVilleurbanne,routeCaluireEscapeGamePerrache});
        placeLineCorrespondance.put("Laser-Game-Genas",new Line[]{routeVilleurbanneLaserGameGenas});
        placeLineCorrespondance.put("Bowling-Bron",new Line[]{routeRillieuxBowlingBron});
        placeLineCorrespondance.put("Cinema-Brignais",new Line[]{routeCinemaBrignaisBurgerKingLimonest,routeVenissieuxCinemaBrignais});
        placeLineCorrespondance.put("Parc-de-la-tete-d'or",new Line[]{routeVenissieuxParcDeLaTeteDOr});
    }

    private HashMap<String, Line[]> placeLineCorrespondance = new HashMap<String, Line[]>();

    public Places getRequestedPlace(String placeName) {
        for(Places sommet: placesArray) {
            if (sommet.getName().equals(placeName)) {
                return sommet;
            }
        }
        return null;
    }

    @FXML
    private MFXComboBox<String> backgroundColorChoice;


    @FXML
    private MFXScrollPane root;


    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        labelContainer.addAll(new ArrayList<Label>(){{add(andrezieuxLabel);add(marie9eLabel);add(laMarinadeLabel);add(laPalisseLabel);add(larbresleLabel);add(laserGameGenasLabel);add(balbignyLabel);add(bowlingBronLabel);add(burgerKingLimonestLabel);add(craponneLabel);add(wokVilleurbanneLabel);add(villeurbanneLabel);add(villefrancheLabel);add(caluireLabel);add(rillieuxLabel);add(fourviereLabel);add(meyzieuLabel);add(maconLabel);add(saintEtienneLabel);add(venissieuxLabel);add(roanneLabel);add(digoinLabel);add(genasLabel);add(lesEchetsLabel);add(mcDoDecinesLabel);add(subwayVaulxLabel);add(kfcSaintPriestLabel);add(confluenceLabel);add(escapeGamePerracheLabel);add(cinemaBrignaisLabel);add(parcDeLaTeteDOrLabel);}});
        iconContainer.addAll(new ArrayList<FontIcon>(){{add(andrezieuxIcon);add(marie9eIcon);add(laMarinadeIcon);add(laPalisseIcon);add(larbresleIcon);add(laserGameGenasIcon);add(balbignyIcon);add(bowlingBronIcon);add(burgerKingLimonestIcon);add(craponneIcon);add(wokVilleurbanneIcon);add(villeurbanneIcon);add(villefrancheIcon);add(caluireIcon);add(rillieuxIcon);add(fourviereIcon);add(meyzieuIcon);add(maconIcon);add(saintEtienneIcon);add(venissieuxIcon);add(roanneIcon);add(digoinIcon);add(genasIcon);add(lesEchetsIcon);add(mcDoDecinesIcon);add(subwayVaulxIcon);add(kfcSaintPriestIcon);add(confluenceIcon);add(escapeGamePerracheIcon);add(cinemaBrignaisIcon);add(parcDeLaTeteDOrIcon);}});
        linesContainer.addAll(new ArrayList<Line>(){{add(routeLaMarinadeVillefranche);add(routeMeyzieuMacon);add(routeVillefrancheMeyzieu);add(routeMeyzieuMcDoDecines);add(routeMcDoDecinesConfluence);add(routeMaconDigoin);add(routeDigoinLaPalisse);add(routeLaPalisseRoanne);add(routeRoanneCraponne);add(routeCraponneKfcSaintPriest);add(routeSubwayVaulxCraponne);add(routeMaconVilleurbanne);add(routeMaconLesEchets);add(routeVilleurbanneLesEchets);add(routeVilleurbanneLaserGameGenas);add(routeLesEchetsFourviere);add(routeLesEchetsConfluence);add(routeMcDoDecinesFourviere);add(routeFourviereMarie9e);add(routeConfluenceRillieux);add(routeMarie9eRillieux);add(routeRillieuxBowlingBron);add(routeRillieuxCaluire);add(routeMarie9eCaluire);add(routeCaluireEscapeGamePerrache);add(routeEscapeGamePerracheWokVilleurbanne);add(routeWokVilleurbanneGenas);add(routeRoanneBalbigny);add(routeBalbignyLarbresle);add(routeLarbresleCaluire);add(routeLarbresleAndrezieux);add(routeAndrezieuxSaintEtienne);add(routeSaintEtienneVenissieux);add(routeVenissieuxParcDeLaTeteDOr);add(routeVenissieuxCinemaBrignais);add(routeCinemaBrignaisBurgerKingLimonest);}});

        //backButton.layoutXProperty().bindBidirectional(root.hvalueProperty());
        //backButton.layoutYProperty().bindBidirectional(root.vvalueProperty());

       // https://stackoverflow.com/questions/50215543/sticky-headers-on-javafx-gridpane
        /*
        // keep header in position on top of the viewport
        InvalidationListener headerUpdater = o -> {
            final double ty = (grid.getHeight() - scrollPane.getViewportBounds().getHeight()) * scrollPane.getVvalue();
            for (Node header : headers) {
                header.setTranslateY(ty);
            }
        };
        grid.heightProperty().addListener(headerUpdater);
        scrollPane.viewportBoundsProperty().addListener(headerUpdater);
        scrollPane.vvalueProperty().addListener(headerUpdater);
        */





        // keep header in position on top of the viewport
        InvalidationListener headerUpdater = o -> {
            final double ty = (backButton.getHeight() - root.getViewportBounds().getHeight()) * root.getVvalue();
            backButton.setTranslateY(ty);
        };
        backButton.heightProperty().addListener(headerUpdater);
        root.viewportBoundsProperty().addListener(headerUpdater);
        root.vvalueProperty().addListener(headerUpdater);


        backgroundColorChoice.getItems().addAll("NOIR", "BLANC", "VERT", "ROSE");

        /*
        root.addEventHandler(ScrollEvent.SCROLL, event -> {
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0){
                zoomFactor = 2.0 - zoomFactor;
            }
            root.setScaleX(root.getScaleX() * zoomFactor);
            root.setScaleY(root.getScaleY() * zoomFactor);
        });

         */

        /*
        root.setOnScroll((ScrollEvent event) -> {
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0){
                zoomFactor = 2.0 - zoomFactor;
            }
            root.setScaleX(root.getScaleX() * zoomFactor);
            root.setScaleY(root.getScaleY() * zoomFactor);
        });
         */

        testToggle.selectedProperty().addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            System.out.println("Caca +"+t1);
        });

    }
    @FXML
    private ToggleButton testToggle;



    @FXML
    public void closeApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private MFXButton backButton;
    @FXML
    private MFXButton quitButton;

    @FXML
    public void closeFunctionnality(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }


    private String backgroundColor = "VERT";

    @FXML
    public void getBackgroundColor(MouseEvent event) {
        backgroundColorChoice.setOnAction(this::putBackgroundColor);
    }





    @FXML
    private Label andrezieuxLabel;
    @FXML
    private Label villeurbanneLabel;
    @FXML
    private Label caluireLabel;
    @FXML
    private Label rillieuxLabel;
    @FXML
    private Label marie9eLabel;
    @FXML
    private Label fourviereLabel;
    @FXML
    private Label meyzieuLabel;
    @FXML
    private Label maconLabel;
    @FXML
    private Label saintEtienneLabel;
    @FXML
    private Label venissieuxLabel;
    @FXML
    private Label roanneLabel;
    @FXML
    private Label balbignyLabel;
    @FXML
    private Label digoinLabel;
    @FXML
    private Label craponneLabel;
    @FXML
    private Label villefrancheLabel;
    @FXML
    private Label genasLabel;
    @FXML
    private Label larbresleLabel;
    @FXML
    private Label lesEchetsLabel;
    @FXML
    private Label laPalisseLabel;
    @FXML
    private Label burgerKingLimonestLabel;
    @FXML
    private Label mcDoDecinesLabel;
    @FXML
    private Label subwayVaulxLabel;
    @FXML
    private Label kfcSaintPriestLabel;
    @FXML
    private Label wokVilleurbanneLabel;
    @FXML
    private Label laMarinadeLabel;
    @FXML
    private Label confluenceLabel;
    @FXML
    private Label escapeGamePerracheLabel;
    @FXML
    private Label laserGameGenasLabel;
    @FXML
    private Label bowlingBronLabel;
    @FXML
    private Label cinemaBrignaisLabel;
    @FXML
    private Label parcDeLaTeteDOrLabel;

    private ArrayList<Label> labelContainer = new ArrayList<Label>();



    @FXML
    private FontIcon andrezieuxIcon;
    @FXML
    private FontIcon villeurbanneIcon;
    @FXML
    private FontIcon caluireIcon;
    @FXML
    private FontIcon rillieuxIcon;
    @FXML
    private FontIcon marie9eIcon;
    @FXML
    private FontIcon fourviereIcon;
    @FXML
    private FontIcon meyzieuIcon;
    @FXML
    private FontIcon maconIcon;
    @FXML
    private FontIcon saintEtienneIcon;
    @FXML
    private FontIcon venissieuxIcon;
    @FXML
    private FontIcon roanneIcon;
    @FXML
    private FontIcon balbignyIcon;
    @FXML
    private FontIcon digoinIcon;
    @FXML
    private FontIcon craponneIcon;
    @FXML
    private FontIcon villefrancheIcon;
    @FXML
    private FontIcon genasIcon;
    @FXML
    private FontIcon larbresleIcon;
    @FXML
    private FontIcon lesEchetsIcon;
    @FXML
    private FontIcon laPalisseIcon;
    @FXML
    private FontIcon burgerKingLimonestIcon;
    @FXML
    private FontIcon mcDoDecinesIcon;
    @FXML
    private FontIcon subwayVaulxIcon;
    @FXML
    private FontIcon kfcSaintPriestIcon;
    @FXML
    private FontIcon wokVilleurbanneIcon;
    @FXML
    private FontIcon laMarinadeIcon;
    @FXML
    private FontIcon confluenceIcon;
    @FXML
    private FontIcon escapeGamePerracheIcon;
    @FXML
    private FontIcon laserGameGenasIcon;
    @FXML
    private FontIcon bowlingBronIcon;
    @FXML
    private FontIcon cinemaBrignaisIcon;
    @FXML
    private FontIcon parcDeLaTeteDOrIcon;

    private ArrayList<FontIcon> iconContainer = new ArrayList<FontIcon>();

    public void putBackgroundColor(ActionEvent event) {
        backgroundColor = backgroundColorChoice.getValue();
        switch (backgroundColor) {
            case "NOIR":
                root.setStyle("-fx-background-color: black;");
                setLabelColor("Blanc");
                break;
            case "BLANC":
                root.setStyle("-fx-background-color: white;");
                setLabelColor("Noir");
                break;
            case "VERT":
                root.setStyle("-fx-background-color: green;");
                setLabelColor("Noir");
                break;
            case "ROSE":
                root.setStyle("-fx-background-color: pink;");
                setLabelColor("Noir");
        }
    }

    @FXML
    private Line routeLaMarinadeVillefranche;
    @FXML
    private Line routeMeyzieuMacon;
    @FXML
    private Line routeVillefrancheMeyzieu;
    @FXML
    private Line routeMeyzieuMcDoDecines;
    @FXML
    private Line routeMcDoDecinesConfluence;
    @FXML
    private Line routeMaconDigoin;
    @FXML
    private Line routeDigoinLaPalisse;
    @FXML
    private Line routeLaPalisseRoanne;
    @FXML
    private Line routeRoanneCraponne;
    @FXML
    private Line routeCraponneKfcSaintPriest;
    @FXML
    private Line routeSubwayVaulxCraponne;
    @FXML
    private Line routeMaconVilleurbanne;
    @FXML
    private Line routeMaconLesEchets;
    @FXML
    private Line routeVilleurbanneLesEchets;
    @FXML
    private Line routeVilleurbanneLaserGameGenas;
    @FXML
    private Line routeLesEchetsFourviere;
    @FXML
    private Line routeLesEchetsConfluence;
    @FXML
    private Line routeMcDoDecinesFourviere;
    @FXML
    private Line routeFourviereMarie9e;
    @FXML
    private Line routeConfluenceRillieux;
    @FXML
    private Line routeMarie9eRillieux;
    @FXML
    private Line routeRillieuxBowlingBron;
    @FXML
    private Line routeRillieuxCaluire;
    @FXML
    private Line routeMarie9eCaluire;
    @FXML
    private Line routeCaluireEscapeGamePerrache;
    @FXML
    private Line routeEscapeGamePerracheWokVilleurbanne;
    @FXML
    private Line routeWokVilleurbanneGenas;
    @FXML
    private Line routeRoanneBalbigny;
    @FXML
    private Line routeBalbignyLarbresle;
    @FXML
    private Line routeLarbresleCaluire;
    @FXML
    private Line routeLarbresleAndrezieux;
    @FXML
    private Line routeAndrezieuxSaintEtienne;
    @FXML
    private Line routeSaintEtienneVenissieux;
    @FXML
    private Line routeVenissieuxParcDeLaTeteDOr;
    @FXML
    private Line routeVenissieuxCinemaBrignais;
    @FXML
    private Line routeCinemaBrignaisBurgerKingLimonest;

    private ArrayList<Line> linesContainer = new ArrayList<Line>();


    public void setLabelColor(String color) {
        for (Label endroitName: labelContainer) {
            endroitName.setStyle((color.equals("Blanc")) ? "-fx-text-fill: white" : "-fx-text-fill: black" );
        }
        for (FontIcon endroitIcon: iconContainer) {
            endroitIcon.setIconColor((color.equals("Blanc")) ? Color.WHITE : Color.BLACK );
        }
        for (Line endroitLine: linesContainer) {
            endroitLine.setStroke((color.equals("Blanc")) ? Color.FLORALWHITE : Color.BLACK );
        }
    }


    @FXML
    public void zoomScrollPane(KeyEvent event) {
        if (event.isControlDown()) {

            /*
            root.addEventFilter(ScrollEvent.ANY, (ScrollEvent scrollEvent) -> {
                double zoomFactor = 1.05;
                double deltaY = scrollEvent.getDeltaY();
                if (deltaY < 0){
                    zoomFactor = 2.0 - zoomFactor;
                }
                anchorPane.setScaleX(anchorPane.getScaleX() * zoomFactor);
                anchorPane.setScaleY(anchorPane.getScaleY() * zoomFactor);
            });

             */
            //root.setOnScroll(this::getMouseUpOrDown);
        }

    }

    /*
    public void getMouseUpOrDown(ScrollEvent event) {
        System.out.println(event.getDeltaY()+event.getDeltaX());
    }
     */
    private String endroit_name;
    private Places endroit;

    private ArrayList<Places> neighbors;


    @FXML
    public void flashNeighbors(MouseEvent event) {
        if (neighbors!=null) {
            unflashNeighbors();
        }
        endroit_name = ((Node) event.getSource()).getAccessibleText();
        endroit = getRequestedPlace(endroit_name);
        neighbors = Graph.getVoisins(endroit);
        for (Places voisin: neighbors) {
            flashPlace(voisin);
        }
        Line[] lines_to_flash = placeLineCorrespondance.get(endroit.getName());
        for (Line line: lines_to_flash) {
            line.setStroke(Color.ORANGERED);
            Flash flash3 = new Flash(line);
            flash3.setCycleCount(2);
            flash3.play();
        }
    }

    public void unflashNeighbors() {
        for (Places voisin: neighbors) {
            FontIcon icon_to_flash = placeIconCorrespondance.get(voisin.getName());
            icon_to_flash.setIconColor((backgroundColor.equals("NOIR")) ? Color.WHITE : Color.BLACK);
            Label label_to_flash = placeLabelCorrespondance.get(voisin.getName());
            label_to_flash.setTextFill((backgroundColor.equals("NOIR")) ? Color.WHITE : Color.BLACK);
            Line[] lines_to_flash = placeLineCorrespondance.get(endroit.getName());
            for (Line line: lines_to_flash) {
                line.setStroke((backgroundColor.equals("NOIR")) ? Color.WHITE : Color.BLACK);
            }
        }
    }

    public void flashPlace(Places endroit) {
        FontIcon icon_to_flash = placeIconCorrespondance.get(endroit.getName());
        icon_to_flash.setIconColor(Color.ORANGERED);
        Flash flash1 = new Flash(icon_to_flash);
        flash1.setCycleCount(2);
        flash1.play();

        Label label_to_flash = placeLabelCorrespondance.get(endroit.getName());
        label_to_flash.setTextFill(Color.ORANGERED);
        Flash flash2 = new Flash(label_to_flash);
        flash2.setCycleCount(2);
        flash2.play();

    }


    @FXML
    public void issouCestMarrant(ActionEvent event) {
        for (Label endroitName: labelContainer) {
            RubberBand anim = new RubberBand(endroitName);
            anim.setSpeed(1.3);
            anim.setCycleCount(6);
            JackInTheBox anim2 = new JackInTheBox(endroitName);
            anim2.setCycleCount(4);
            anim2.setSpeed(1.3);
            anim.playOnFinished(anim2);
            anim.play();
        }
        for (FontIcon endroitIcon: iconContainer) {
            RubberBand anim = new RubberBand(endroitIcon);
            anim.setSpeed(1.3);
            anim.setCycleCount(6);
            JackInTheBox anim2 = new JackInTheBox(endroitIcon);
            anim2.setCycleCount(4);
            anim2.setSpeed(1.3);
            anim.playOnFinished(anim2);
            anim.play();
        }
        for (Line endroitLine: linesContainer) {
            RubberBand anim = new RubberBand(endroitLine);
            anim.setSpeed(1.3);
            anim.setCycleCount(6);
            JackInTheBox anim2 = new JackInTheBox(endroitLine);
            anim2.setCycleCount(4);
            anim2.setSpeed(1.3);
            anim.playOnFinished(anim2);
            anim.play();
        }
        Swing swing = new Swing(root);
        swing.setSpeed(1.6);
        swing.play();
    }

}
