package com.axelcollet.tp_android_varo_collet;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Madoff on 28/11/2017.
 */

public class CarteVocabulaire implements Parcelable {

    //private variables
    int id;
    String traductionFR;
    String traductionEN;

    public CarteVocabulaire(){}

    public CarteVocabulaire(int id, String traductionFR, String traductionEN){
        this.id = id;
        this.traductionFR = traductionFR;
        this.traductionEN = traductionEN;
    }

    public CarteVocabulaire(String traductionFR, String traductionEN){
        this.traductionFR = traductionFR;
        this.traductionEN = traductionEN;
    }

    public int getID(){return this.id;}
    public void setID(int id){ this.id = id;}

    public String getTraductionFR(){return this.traductionFR;}
    public void setTraductionFR(String traductionFR){this.traductionFR = traductionFR;}

    public String getTraductionEN(){return this.traductionEN;}
    public void setTraductionEN(String traductionEN){this.traductionEN = traductionEN;}

    //Parcable part

    @Override
    public int describeContents()
    {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(traductionFR);
        dest.writeString(traductionEN);

    }

    public CarteVocabulaire(Parcel in) {
        this.id = in.readInt();
        this.traductionFR = in.readString();
        this.traductionEN = in.readString();
    }

    public static final Creator<CarteVocabulaire> CREATOR = new Creator<CarteVocabulaire>()
    {
        @Override
        public CarteVocabulaire createFromParcel(Parcel source)
        {
            return new CarteVocabulaire(source);
        }

        @Override
        public CarteVocabulaire[] newArray(int size)
        {
            return new CarteVocabulaire[size];
        }
    };
}
