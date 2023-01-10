package com.example.javafx_essai1;

import java.util.*;


public class Matrice {
    public static ArrayList<ArrayList<Roads>> matrice = new ArrayList<ArrayList<Roads>>();

    public Matrice(ArrayList<Roads> mat, ArrayList<Places> places) {
        for(int i = 0;i<places.size();i++) {
            ArrayList<Roads> ajout = new ArrayList<Roads>();
            for(int j = 0;j<places.size();j++)
                ajout.add(null);
            matrice.add(ajout);
        }
        for(int ref = 0;ref<places.size();ref++) {

            for (Roads route : mat) {
                if (route.getPlace1().getId()==ref+1) {
                    matrice.get(ref).set(route.getPlace2().getId()-1,route);
                    matrice.get(route.getPlace2().getId()-1).set(ref,route);
                }
            }
        }
    }

    public String toString() {
        return "" + matrice;
    }

    public void printRowPerRow() {
        System.out.println();
        for (ArrayList<Roads> ligne: matrice) {
            System.out.println(ligne);
        }
    }

    public void getMatricedAdjacence() {
        for (ArrayList<Roads> ligne: matrice) {
            for (Roads route: ligne) {
                if (route==null) {
                    System.out.print(0+",");
                }
                else System.out.print(1+",");
            }
            System.out.println();
        }
    }
}