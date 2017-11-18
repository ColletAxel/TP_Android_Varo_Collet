package com.axelcollet.tp_android_varo_collet;

/**
 * Created by Axel Collet on 15.11.2017.
 */

public class TupleVocabulaire {
    public String Mot = "break";
    public String MotTraduit = "casser";
    public String Langue = "Anglais";
    public String LangueTraduction = "French";
    public int sucess = 0;
    public int fail = 0;

    public TupleVocabulaire(String Mot, String MotTraduit){
        this.Mot = Mot;
        this.MotTraduit =MotTraduit;
    }
    public TupleVocabulaire (String Mot, String MotTraduit,String Langue, String LangueTraduction){
        this.Mot = Mot;
        this.MotTraduit =MotTraduit;
        this.Langue = Langue;
        this.LangueTraduction =LangueTraduction;
    }

    public boolean VerifieTuble (String Mot1,String Mot2){

        boolean vr;
        if (Mot1 ==this.Mot){
            vr = Mot2 == this.MotTraduit;
        }else if (Mot2 == this.Mot){
            vr = Mot1 == this.MotTraduit;
        }else{
            vr = false;
        }
        return vr;
    }
}