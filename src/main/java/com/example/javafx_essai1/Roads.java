package com.example.javafx_essai1;

public class Roads {
    /* Définition des attributs */
    private int Type;
    private int kilometers;
    public Places Place1;
    public Places Place2;
    
    /* Constructeur */
    Roads(int Type, int kilometers, Places Place1, Places Place2){
        this.kilometers=kilometers;
        this.Type=Type;
        this.Place1=Place1;
        this.Place2=Place2;
    }

    /* Méthodes basiques */

    public Places getPlace1(){
        return this.Place1;
    }

    public Places getPlace2(){
        return this.Place2;
    }

    public String getType(){
        switch (Type){
            case 0 : return "Autoroute";
            case 1 : return "Nationale";
            case 2 : return "Departementale";
            default : return "Erreur";
        }
    }

    public int getKilometers(){
        return this.kilometers;
    }



    public void setPlace1(Places PL){
        this.Place1 = PL;
    }

    public void setPlace2(Places PL){
        this.Place2 = PL;
    }

    public void setKilometers(int kilometers){
        this.kilometers = kilometers;
    }


    public void setType(int number){
        if (number>2 || number<0) return;
        this.Type=number;
    }



    public String toString() {
        return "   "+this.Place1 + " --> " + this.Place2;
    }

    public String toString2() {
        return "Une "+this.getType().toLowerCase()+" de "+ this.kilometers +" kilometres va de "+this.Place1.getName()+" a "+ this.Place2.getName();
    }
    
    public String toStringReversed() {
        return "Une "+this.getType().toLowerCase()+" de "+ this.kilometers +" kilometres va de "+this.Place2.getName()+" a "+ this.Place1.getName();
    }
    
}
