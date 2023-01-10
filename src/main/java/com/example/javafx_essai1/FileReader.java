package com.example.javafx_essai1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class FileReader{
    private File F;
    private int Line = 0;
    Scanner S;

    FileReader(String filename) throws IOException{
        this.F = new File(filename);
        this.S = new Scanner(this.F);
    }

    public boolean filetest(){
        if(!this.F.exists()){
            try{
                this.F.createNewFile();
            } catch(IOException e){
                e.printStackTrace();
                return false;
            }
        }  
        return true;
    }  

    public File getFile(){
        return this.F;
    }

    public Scanner getScanner(){
        return this.S;
    }

    public String getLineInString() throws IOException{
        if (!this.S.hasNextLine()){
            this.closeReader();      
            return null;
        }
        else{
            String line = this.S.nextLine();
            this.Line++;
            return line;
        }
    }

    public String[] getStringInArray(String a){
        return a.split(":|_|,|\\{|\\}");
    }

    public int getLine(){
        return this.Line;
    }

    public void closeReader(){
        this.S.close();
    }

}