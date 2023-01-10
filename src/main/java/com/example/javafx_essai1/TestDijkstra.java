package com.example.javafx_essai1;

import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

public class TestDijkstra {

    private Djikstra dijkstra;
    private ArrayList<Places> placesArray;
    private ArrayList<Roads> roadsArray;
    Places endroit;


    public TestDijkstra() {
    }
    @BeforeClass
    public static void setUpClass() {
    }
    @AfterClass
    public static void tearDownClass() {
    }
    @Before
    public void setUp() throws IOException {
        ManageLists Graph2 = new ManageLists("C:/Users/Anis/Documents/Intellij_javafx/javafx_essai1/src/main/java/com/example/javafx_essai1/knots.csv");
        placesArray = Graph2.getPlacesArray();
        roadsArray = Graph2.getRoadsArray();
        ArrayList<Roads> roadsArray = Graph2.getRoadsArray();
        dijkstra = new Djikstra(roadsArray,placesArray);
        endroit = placesArray.get(0);
    }
    @After
    public void tearDown() {
    }

    @Test
    public void getResultTest() {
        /*
        Nous avons vérifié les résultats à l'avance et le plus court chemin entre les 2 1ers endroits de placesArray (Villeurbanne et Caluire)
        est le chemin qui contient les 17e, 4e et 3e endroits de placesArray (Les-Echets, Fourvière et Marie-9e)
         */
        assertEquals(new ArrayList<Places>(){{add(placesArray.get(17));add(placesArray.get(4));add(placesArray.get(3));}},dijkstra.getResult(placesArray.get(0),placesArray.get(1)));
    }

    @Test
    public void releaseTest() {
        dijkstra.realease(endroit,20);
        assertEquals(Integer.valueOf(20),endroit.getMinKilometers());
        assertTrue(endroit.isRealeased());
    }

    @Test
    public void reInitTest() {
        for(Places endroit: placesArray) {
            endroit.setPrevious(placesArray.get(0));
            endroit.setMinKilometers(99);
            endroit.setRealeased(true);
        }
        dijkstra.reinit();
        for (Places endroit: placesArray) {
            assertNull(endroit.getPrevious());
            assertEquals(Integer.valueOf(-1),endroit.getMinKilometers());
            assertFalse(endroit.isRealeased());
        }
    }


    @Test
    public void browseRoadsTest() {
        assertEquals(new ArrayList<Roads>(){{add(roadsArray.get(0));add(roadsArray.get(1));add(roadsArray.get(2));}},dijkstra.browseRoads(endroit));
    }
}
