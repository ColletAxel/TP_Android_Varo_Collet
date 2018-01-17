package com.axelcollet.tp_android_varo_collet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madoff on 28/11/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables

    private static final int DATABASE_VERSION = 1;                           // Database Version
    private static final String DATABASE_NAME = "Vocabulaire_DB";            // Database Name
    private static final String TABLE_TRADUCTION = "carte_de_traduction";    // Contacts table name
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FR = "traduction_francaise";
    private static final String KEY_EN = "traduction_anglaise";
    private static final String KEY_SCORE = "score";

    // Use in order to select the language of research card in database
    public enum Langue{FR,EN}

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TRADUCTION + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_FR + " TEXT,"
                + KEY_EN + " TEXT," + KEY_SCORE + " INTEGER"  + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRADUCTION);

        // Create tables again
        onCreate(db);
    }


    // All Operations(Create, Read, Update, Delete)

    // Adding new Card
    void addCard(CarteVocabulaire cv) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FR, cv.getTraductionFR());
        values.put(KEY_EN, cv.getTraductionEN());
        values.put(KEY_SCORE, cv.getScore());
        db.insert(TABLE_TRADUCTION, null, values);  // Inserting Row
        db.close();                                              // Closing database connection
    }

    void updateCardScore(int id, int score){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_SCORE,score);
        db.update(TABLE_TRADUCTION, newValues,KEY_ID + "=?" ,new String[] { String.valueOf(id) });

        Log.e("updateCardScore ", "Fonction updateCardScore executer");

    }


    // Getting single Card
    CarteVocabulaire getCard_byID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TRADUCTION, new String[] { KEY_ID, KEY_FR, KEY_EN, KEY_SCORE }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            CarteVocabulaire cv = new CarteVocabulaire(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),Integer.parseInt(cursor.getString(3)));
            return cv;
        }else{
            return null;
        }
    }

    // Cette fonction n'est pas encore operationel, elle fonctionne mais elle peut planter
    CarteVocabulaire getCard_byFR(String traductionFR){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TRADUCTION, new String[] { KEY_ID, KEY_FR, KEY_EN, KEY_SCORE}, KEY_FR + "=?", new String[] { traductionFR }, null, null, null, null);
        if (cursor != null) {
            Log.e("getCard_byFR ", String.valueOf(cursor.getCount()));
            return new CarteVocabulaire(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),Integer.parseInt(cursor.getString(3)));
        }else{
            Log.e("getCard_byFR ", "NULLLL");
            return null;
        }
    }

    int isCard_inDataBase(String traductionFR, Langue langue  ){

        String KEY = "";
        if(langue == Langue.FR){KEY = KEY_FR;}
        else if(langue == Langue.EN){KEY = KEY_EN;}

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TRADUCTION, new String[] { KEY_ID, KEY_FR, KEY_EN, KEY_SCORE }, KEY + "=?", new String[] { traductionFR }, null, null, null, null);
        if (cursor != null) {
            if(cursor.getCount() > 0){
                return cursor.getCount();
            }else{
                return cursor.getCount();
            }
        }else{
            return -1;
        }
    }



    // Getting All Card
    public ArrayList<CarteVocabulaire> getAllCard() {
        ArrayList<CarteVocabulaire> CarteVocabulaireList = new ArrayList<CarteVocabulaire>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRADUCTION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CarteVocabulaire cv = new CarteVocabulaire();
                cv.setID(Integer.parseInt(cursor.getString(0)));
                cv.setTraductionFR(cursor.getString(1));
                cv.setTraductionEN(cursor.getString(2));
                cv.setScore(Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                CarteVocabulaireList.add(cv);
            } while (cursor.moveToNext());
        }

        // return contact list
        return CarteVocabulaireList;
    }

    // Updating single contact
    public int updateCard(CarteVocabulaire Carte) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FR, Carte.getTraductionFR());
        values.put(KEY_EN, Carte.getTraductionEN());
        values.put(KEY_SCORE, Carte.getScore());

        // updating row
        return db.update(TABLE_TRADUCTION, values, KEY_ID + " = ?", new String[] { String.valueOf(Carte.getID()) });
    }

    // Deleting single contact
    public void deleteCard(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRADUCTION, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }


    // Getting contacts Count
    public int getCardsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TRADUCTION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }


}
