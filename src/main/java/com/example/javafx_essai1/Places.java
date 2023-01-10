package com.example.javafx_essai1;

class Places{
    /* Définition des attributs */
    private String Name;
    private int Type;
    private int id;
    private static int id_ref = 1;
    private Places previous;
    private Integer min_kilometers = -1;
    private boolean realeased = false;

    public Places getPrevious() {
        return this.previous;
    }

    public void setPrevious(Places previous) {
        this.previous = previous;
    }

    public Integer getMinKilometers() {
        return this.min_kilometers;
    }

    public void setMinKilometers(int min_kilometers) {
        this.min_kilometers = min_kilometers;
    }

    public boolean isRealeased() {
        return this.realeased;
    }

    public void setRealeased(boolean realeased) {
        this.realeased = realeased;
    }

    private boolean want_to_give_id;

    /* Constructeur */
    Places(String Name, int Type, boolean want_to_give_id) {
        this.Name=Name;
        this.Type=Type;
        if (want_to_give_id) {
            this.id = id_ref;
            id_ref++;
        }
        else this.id = 0;
    }


    /* Méthodes basiques */
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String Name){
        this.Name=Name;
    }

    public String getName(){
        return this.Name;
    }

    public int getTypeNbr() { return this.Type; }

    public String getType(){
        switch (this.Type){
            case 0 : return "Ville";
            case 1 : return "Restaurant";
            case 2 : return "Loisir";
        }
        return "Erreur";
    }

    public void setType(int number){
        if (number>2 || number<0) return;
        this.Type = number;
    }

    public String toString(){
        return this.Name+this.id;
    } 

    public String toString2() {
        String phrase_correcte;
        if(this.Type==0) phrase_correcte = "une";
        else phrase_correcte = "un";
        return "Le lieu \""+this.Name+"\" est "+phrase_correcte+" "+this.getType();
    }
}