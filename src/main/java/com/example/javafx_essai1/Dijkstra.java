package com.example.javafx_essai1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class Djikstra {

    private ArrayList<Roads> Liste;
    private ArrayList<Places> Liste2;

    Djikstra(ArrayList <Roads> Liste, ArrayList <Places> Liste2){
        this.Liste = Liste;
        this.Liste2 = Liste2;
        /* On initialise les sommets */
        for(int i = 0; i < Liste2.size(); i++){
            Liste2.get(i).setPrevious(null);
            Liste2.get(i).setMinKilometers(-1);
            Liste2.get(i).setRealeased(false);
        }
    }

    /* Fonction à appeler pour les sommets */
    public ArrayList<Roads> getResult(Places A, Places B){
        System.out.println(A + "  " + B);

        //Si les deux lieux sont identiques, il n'y a rien à retourner
        if(A==B) return null;

        //Contient toutes les routes reliées à un sommet
        ArrayList <Roads> middle;

        /* On relache le sommet initial */
        realease(A,0);

        middle=browseRoads(A);

        /*  On repete l'opération pour chaque sommet à relacher  */
        for(int  j=0; j < Liste2.size(); j++){

            Places previous = A;
            middle=browseRoads(previous);
            Roads min_kilometers = middle.get(0);

            /* Pour tous les sommets reliés au précedent */
            for(int i = 0; i < middle.size(); i++){

                //On choisit le prochain sommet à traiter (non relaché)
                if(middle.get(i).getPlace1()==A && !middle.get(i).getPlace2().isRealeased()){
                    middle.get(i).getPlace2().setPrevious(A);
                    middle.get(i).getPlace2().setMinKilometers(middle.get(i).getKilometers());
                } else if (middle.get(i).getPlace2()==A && !middle.get(i).getPlace1().isRealeased()){
                    middle.get(i).getPlace1().setPrevious(A);
                    middle.get(i).getPlace1().setMinKilometers(middle.get(i).getKilometers());
                }

                //On retrouve la route à la distance minimale on l'affecte à min_kilometers
                if(middle.get(i).getKilometers()<min_kilometers.getKilometers()){
                    min_kilometers=middle.get(i);
                }

                //On met à jour le sommet précédent
                if(min_kilometers.getPlace1()==A) A=min_kilometers.getPlace2();
                else A=min_kilometers.getPlace1();
            }

            /* On relache le sommet à la distance min */
            realease(A,A.getMinKilometers()+min_kilometers.getKilometers());
        }

        /*On trouve le chemin le plus court qu'on met dans result */
        ArrayList <Roads> result = findPath(A, B);

        reinit();


        return result;
    }

    /* Fonction qui retrouve le plus court chemin avec les modifications
    faites par la fonction appelante sur les sommets */
    private ArrayList<Roads> findPath(Places A, Places B){
        System.out.println("caca");
        Roads middle = null;
        ArrayList<Roads> result = new ArrayList<Roads>();
        while(A!=B){
            int j=0;
            for(int i=0; i < Liste.size(); i++){
                if(Liste.get(i).getPlace1()==B && Liste.get(i).getPlace2()==B.getPrevious()){
                    j++;
                    if(j==1){
                        middle=Liste.get(i);
                    } else if (middle.getKilometers()>Liste.get(i).getKilometers()){
                        middle=Liste.get(i);
                    }
                } else if (Liste.get(i).getPlace2()==B && Liste.get(i).getPlace1()==B.getPrevious()){
                    j++;
                    if(j==1){
                        middle=Liste.get(i);
                    } else if (middle.getKilometers()>Liste.get(i).getKilometers()){
                        middle=Liste.get(i);
                    }
                }
            }
            System.out.println(A + "  " + B);
            System.out.println(11111);
            result.add(middle);
            System.out.println(B.getPrevious());
            B=B.getPrevious();
        }

        //Le chemin est dans l'odre inversé, on le remet à l'endroit puis on retourne
        Collections.reverse(result);
        return result;
    }

    /* On parcourt les routes qui sont reliés au sommet précédent */
    public ArrayList<Roads> browseRoads(Places A){
        ArrayList<Roads> RoadsWithThisPlace = new ArrayList<Roads>();
        for(Roads r : Liste){
            if(r.getPlace1()==A || r.getPlace2()==A){
                RoadsWithThisPlace.add(r);
            }
        }
        return RoadsWithThisPlace;
    }

    /* Fonction de relachement */
    public void realease(Places A,int distance){
        A.setPrevious(A);
        A.setMinKilometers(distance);
        A.setRealeased(true);
    }

    /* On remet les valeurs de Djikstra à l'initial */
    public void reinit(){
        for(Places P : Liste2){
            P.setPrevious(null);
            P.setMinKilometers(-1);
            P.setRealeased(false);
        }
    }
}