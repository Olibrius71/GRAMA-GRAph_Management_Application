package com.example.javafx_essai1;

import java.util.*;

public class Fonctionnalites {
    private Matrice mat;
    private ArrayList<Places> placesArray = new ArrayList<Places>();
    private ArrayList<Roads> roadsArray = new ArrayList<Roads>();

    public Fonctionnalites(Matrice mat, ArrayList<Places> placesArray, ArrayList<Roads> roadsArray){
        this.mat = mat;
        this.placesArray = placesArray;
        this.roadsArray = roadsArray;
    }


    public Matrice getMat() {
        return this.mat;
    }

    public ArrayList<Places> getPlacesArray() {
        return this.placesArray;
    }

    public ArrayList<Roads> getRoadsArray() {
        return this.roadsArray;
    }



    public ArrayList<Places> getVoisins(Places pl){
        ArrayList<Places> voisins = new ArrayList<Places>();
        ArrayList<Roads> voisins_eventuels = new ArrayList<Roads>();
        voisins_eventuels = this.mat.matrice.get(pl.getId()-1);
        for(int route = 0;route<this.placesArray.size();route++) {
            if (voisins_eventuels.get(route)!=null) {
                if (voisins_eventuels.get(route).getPlace1().getId()==pl.getId()) {
                    voisins.add(voisins_eventuels.get(route).getPlace2());
                }
                else {
                    voisins.add(voisins_eventuels.get(route).getPlace1());
                }
            }
        }
        return voisins;
    }

    public ArrayList<Places> getVoisins2Dist(Places pl){
        ArrayList<Places> voisins_2dist = new ArrayList<Places>();
        ArrayList<Places> voisins_1dist = new ArrayList<Places>();
        voisins_1dist = getVoisins(pl);
        ArrayList<Places> voisins_eventuels_2dist = new ArrayList<Places>();
        ArrayList<Integer> ids_added = new ArrayList<Integer>();
        for(Places voisin: voisins_1dist) {
            voisins_eventuels_2dist = getVoisins(voisin);
            for(Places voisin_final: voisins_eventuels_2dist) {
                if (voisin_final.getId()!=pl.getId() && !ids_added.contains(voisin_final.getId())) {
                    voisins_2dist.add(voisin_final);
                    ids_added.add(voisin_final.getId());
                }
            }
        }
        return voisins_2dist;
    }

    public ArrayList<Places> getVoisins2DistOfType(Places pl,String type){
        ArrayList<Places> voisins_2dist = new ArrayList<Places>();
        ArrayList<Places> voisins_1dist = new ArrayList<Places>();
        voisins_1dist = getVoisins(pl);
        ArrayList<Places> voisins_eventuels_2dist = new ArrayList<Places>();
        ArrayList<Integer> ids_added = new ArrayList<Integer>();
        for(Places voisin: voisins_1dist) {
            voisins_eventuels_2dist = getVoisins(voisin);
            for(Places voisin_final: voisins_eventuels_2dist) {
                if (voisin_final.getType().equals(type) && voisin_final.getId()!=pl.getId() && !ids_added.contains(voisin_final.getId())) {
                    voisins_2dist.add(voisin_final);
                    ids_added.add(voisin_final.getId());
                }
            }
        }
        return voisins_2dist;
    }


    public boolean isVoisin2Dist(Places pl1, Places pl2){
        return getVoisins2Dist(pl1).contains(pl2);
    }


    public String plusOuverte(Places pl1, Places pl2) {
        ArrayList<Places> voisins_2dist_pl1 = new ArrayList<Places>();
        ArrayList<Places> voisins_2dist_pl2 = new ArrayList<Places>();
        voisins_2dist_pl1 = getVoisins2Dist(pl1);
        voisins_2dist_pl2 = getVoisins2Dist(pl2);

        int counter_villes_pl1 = 0;
        int counter_villes_pl2 = 0;

        for (Places endroit : voisins_2dist_pl1) {
            if (endroit.getType().equals("Ville")) {
                counter_villes_pl1++;
            }
        }
        for (Places endroit : voisins_2dist_pl2) {
            if (endroit.getType().equals("Ville")) {
                counter_villes_pl2++;
            }
        }

        if (counter_villes_pl1>counter_villes_pl2)
            return pl1.getName() + " Est plus Ouverte que " + pl2.getName();
        if (counter_villes_pl1<counter_villes_pl2)
            return pl1.getName() + " Est moins Ouverte que " + pl2.getName();
        return pl1.getName() + " Est aussi Ouverte que " + pl2.getName();
    }

    public String plusGastronomique(Places pl1, Places pl2) {
        ArrayList<Places> voisins_2dist_pl1 = new ArrayList<Places>();
        ArrayList<Places> voisins_2dist_pl2 = new ArrayList<Places>();
        voisins_2dist_pl1 = getVoisins2Dist(pl1);
        voisins_2dist_pl2 = getVoisins2Dist(pl2);

        int counter_restos_pl1 = 0;
        int counter_restos_pl2 = 0;

        for (Places endroit : voisins_2dist_pl1) {
            if (endroit.getType().equals("Restaurant")) {
                counter_restos_pl1++;
            }
        }
        for (Places endroit : voisins_2dist_pl2) {
            if (endroit.getType().equals("Restaurant")) {
                counter_restos_pl2++;
            }
        }

        if (counter_restos_pl1>counter_restos_pl2)
            return pl1.getName() + " Est plus Gastronomique que " + pl2.getName();
        if (counter_restos_pl1<counter_restos_pl2)
            return pl1.getName() + " Est moins Gastronomique que " + pl2.getName();
        return pl1.getName() + " Est aussi Gastronomique que " + pl2.getName();
    }

    public String plusCulturelle(Places pl1, Places pl2) {
        ArrayList<Places> voisins_2dist_pl1 = new ArrayList<Places>();
        ArrayList<Places> voisins_2dist_pl2 = new ArrayList<Places>();
        voisins_2dist_pl1 = getVoisins2Dist(pl1);
        voisins_2dist_pl2 = getVoisins2Dist(pl2);

        int counter_loisirs_pl1 = 0;
        int counter_loisirs_pl2 = 0;

        for (Places endroit : voisins_2dist_pl1) {
            if (endroit.getType().equals("Loisir")) {
                counter_loisirs_pl1++;
            }
        }
        for (Places endroit : voisins_2dist_pl2) {
            if (endroit.getType().equals("Loisir")) {
                counter_loisirs_pl2++;
            }
        }

        if (counter_loisirs_pl1>counter_loisirs_pl2)
            return pl1.getName() + " Est plus Culturelle que " + pl2.getName();
        if (counter_loisirs_pl1<counter_loisirs_pl2)
            return pl1.getName() + " Est moins Culturelle que " + pl2.getName();
        return pl1.getName() + " Est aussi Culturelle que " + pl2.getName();
    }

    /*
    public int getDistance(Places pl1, Places pl2) {
        int distance = 1;
        ArrayList<Places> voisins_successifs = new ArrayList<Places>();
        voisins_successifs = getVoisins(pl1);
        ArrayList<Places> voisins_temp = new ArrayList<Places>();
        while (!voisins_successifs.contains(pl2)) {
            System.out.println(2);
            System.out.println(voisins_successifs);
            for (Places voisin : voisins_successifs) {
                System.out.println(3);
                voisins_temp = getVoisins(voisin);
                System.out.println(voisins_temp);
                for (int i = 0;i<voisins_temp.size();i++) {
                    System.out.println(4);
                    if(!voisins_successifs.contains(voisins_temp.get(i))) {
                        System.out.println(5);
                        voisins_successifs.add(voisins_temp.get(i));
                    }
                }

            }
            distance++;
        }
        return distance;
    }
    */



    public String toString() {
        return "voilà: " + this.mat;
    }

}



