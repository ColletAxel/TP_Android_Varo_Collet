package com.axelcollet.tp_android_varo_collet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TupleVocabulaire tv1 = new TupleVocabulaire("work","travail");
    private TupleVocabulaire tv2 = new TupleVocabulaire("play","jouer");
    private TupleVocabulaire tv3 = new TupleVocabulaire("Hello","Bonjour");
    private TupleVocabulaire tv4 = new TupleVocabulaire("credit card","carte de crédit");

    List<TupleVocabulaire> listTV = new ArrayList<TupleVocabulaire>();
    // TODO: 15.11.2017  faire fonctionner cette liste.... 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button_validate = (Button)findViewById(R.id.button);
        button_validate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //// TODO: 15.11.2017 vérifier le mot, passer au suivant
            }
        });

        final Button button_menu = (Button)findViewById(R.id.button3);
        button_validate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //// TODO: 15.11.2017 back  menu
            }
        });

    }
}

// TODO: 15.11.2017 faire classe carton
// TODO: 15.11.2017 faire classe qui contient plusieurs carton genre une liste des ids
// TODO: 15.11.2017 faire activity menu
// TODO: 15.11.2017 passer la liste de carton choisi dans le menu a cette activité
// TODO: 15.11.2017 renomer cette activité ?!?
// TODO: 15.11.2017  stats réussite / echec d'un carton
