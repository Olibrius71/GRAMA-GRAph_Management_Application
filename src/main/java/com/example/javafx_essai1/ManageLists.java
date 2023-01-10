package com.example.javafx_essai1;

import java.io.IOException;
import java.util.*;

public class ManageLists {
    private ArrayList<Places> PlacesArray = new ArrayList<Places>();
    private ArrayList<Roads> RoadsArray = new ArrayList<Roads>();

    public ManageLists(String filename) throws IOException{ 
        this.fillArrayLists(filename);
    }

    public int converter(String type){
        switch (type) {
            case "A":
            case "V":
                return 0;
            case "N":
            case "R":
                return 1;
            case "D":
            case "L":
                return 2;
            default: 
                return 0;
        }
    }

    public void fillArrayLists(String filename) throws IOException{
        FileReader reader = new FileReader(filename);
        if (reader.filetest()){
            String tmp;
            int index=-1;
            while ((tmp = reader.getLineInString())!=null){
                String[] tab = reader.getStringInArray(tmp);
                for(int i = 0; i < tab.length-2; i+=2){
                    if(i == 0){
                        Places P1 = new Places(tab[i+1] , this.converter(tab[i]),true);
                        PlacesArray.add(P1);
                        index++;
                    }else{
                        i+=2;
                        Places P1 = new Places(tab[i+1], this.converter(tab[i]),false);
                        int indice_p1 = PlacesArray.indexOf(P1);

                        Roads R1 = new Roads(this.converter(tab[i-2]), Integer.valueOf(tab[i-1]), PlacesArray.get(index), P1);

                        if(indice_p1!=-1) {
                            R1.setPlace2(PlacesArray.get(indice_p1));
                        }                        

                        RoadsArray.add(R1);
                        
                    }
                }
            }
        }else{
            new Exception("Erreur à l'ouverture du fichier");
        }
        reader.closeReader();
        this.putIds();
        this.deleteDuplicateRoads();
    }   

    public void putIds() {
        for (Places sommet: PlacesArray) {
            String nom_sommet = sommet.getName();
            int id_sommet = sommet.getId();
            for (Roads routes_sommet: RoadsArray) {
                Places sommet_a_update = routes_sommet.getPlace2();
                if (sommet_a_update.getName().equals(nom_sommet)) {
                    sommet_a_update.setId(id_sommet);
                }
            }
        }
    }

    /*
    public void fillArrayLists2(String filename) throws IOException{
        FileReader reader = new FileReader(filename);
        if (reader.filetest()){
            String tmp;
            int index=-1;
            while ((tmp = reader.getLineInString())!=null){
                String[] tab = reader.getStringInArray(tmp);
                for(int i = 0; i < tab.length-2; i+=2){
                    if(i == 0){
                        Places P1 = new Places(tab[i+1] , this.converter(tab[i]));
                        if(!PlacesArray.contains(P1))
                        PlacesArray.add(P1);
                        index++;
                    }else{
                        i+=2;
                        Places P1 = new Places(tab[i+1], this.converter(tab[i]));
                        Roads R1 = new Roads(this.converter(tab[i-2]), Integer.valueOf(tab[i-1]), PlacesArray.get(index), P1);
                        if(!RoadsArray.contains(R1))
                            RoadsArray.add(R1);
                    }
                }
            }
        }else{
            new Exception("Erreur à l'ouverture du fichier");
        }
        /*
        reader.closeReader();
        this.deleteDuplicateRoads2();
        this.deleteDuplicatePlacesInRoads();
        this.setPlacesReference();
        */
        /* this.createMatrice(); */
   // }
    
    


    public Matrice createMatrice(){
        Matrice M1 = new Matrice(RoadsArray, PlacesArray);
        return M1;
    }


    public void deleteDuplicateRoads(){
        int index_route1 = 0;
        int index_route2;
        ArrayList<Roads> to_remove = new ArrayList<Roads>();
        ArrayList<Integer> deja_traites = new ArrayList<Integer>();
        for (Roads route1: RoadsArray) {
            index_route2 = 0;
            int id_1 = route1.getPlace1().getId();
            int id_2 = route1.getPlace2().getId();
            //System.out.println("\n    "+id_1 +" "+id_2);
            for (Roads route2: RoadsArray) {
                int id_3 = route2.getPlace1().getId();
                int id_4 = route2.getPlace2().getId();
                //System.out.println(" "+id_3+" "+id_4);
                if (index_route1!=index_route2 && !deja_traites.contains(index_route1) && (id_1==id_4 && id_2==id_3)) {
                    to_remove.add(RoadsArray.get(index_route2));
                    deja_traites.add(index_route2);
                    break;
                }
                index_route2++;
            }
            index_route1++;
        }

        RoadsArray.removeAll(to_remove);
    }





    public ArrayList<Roads> getRoadsArray(){
        return this.RoadsArray;
    }

    public ArrayList<Places> getPlacesArray(){
        return this.PlacesArray;
    }

    public void printRoad(String PL){
        int nbr = 0;
        for (int i = 0; i <this.getRoadsArray().size(); i++){
            if(this.RoadsArray.get(i).getPlace1().getName().equals(PL))
                System.out.println(this.getRoadsArray().get(i));
            if(this.RoadsArray.get(i).getPlace2().getName().equals(PL)){
                System.out.println(this.getRoadsArray().get(i).toStringReversed());
                nbr++;
            }
        }        
        if(nbr==0) System.out.println("Aucune route partant de \""+PL+"\" n\'a ete trouvee, le lieu n'existe peut etre pas");
        System.out.println("\n");
    }

    public void printPlace(String PL){
        int nbr = 0;
        for (int i = 0; i <this.getPlacesArray().size(); i++){
            if(this.getPlacesArray().get(i).getName().equals(PL)){
                System.out.println(this.getPlacesArray().get(i));
                nbr++;
                break;
            }
        }
        if(nbr==0) System.out.println("Aucun lieu du nom de \""+PL+"\" n\'a ete trouve, verifiez l'ortographe et si le lieu existe");
        System.out.println("\n");
    }

    public int countPlaces(){
        return this.getPlacesArray().size();
    }

    public int countRoads(){
        return this.getRoadsArray().size();
    }
}


