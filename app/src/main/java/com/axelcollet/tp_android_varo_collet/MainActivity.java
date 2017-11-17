package com.axelcollet.tp_android_varo_collet;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;



// If you want you can add other log definition for info, warning etc
public class MainActivity extends AppCompatActivity {

    private TupleVocabulaire tv1 = new TupleVocabulaire("work","travail");
    private TupleVocabulaire tv2 = new TupleVocabulaire("play","jouer");
    private TupleVocabulaire tv3 = new TupleVocabulaire("Hello","Bonjour");
    private TupleVocabulaire tv4 = new TupleVocabulaire("credit card","carte de crédit");
    private int posList = 0;
    private TupleVocabulaire tvActuel;

    // TODO: 15.11.2017  faire fonctionner cette liste....
    List<TupleVocabulaire> tvList = new ArrayList<TupleVocabulaire>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tvList.add(tv1);
        tvList.add(tv2);
        tvList.add(tv3);
        tvList.add(tv4);
        final Button button_validate = (Button)findViewById(R.id.button_validate);
        final TextView viewMot = (TextView)findViewById(R.id.textView);
        final EditText viewReponse = (EditText)findViewById(R.id.editText);
        System.out.print("loool11");
        Log.d("creation","on creat exectuted");


        tvActuel = tvList.get(posList);
        viewMot.setText(tvActuel.Mot);
        button_validate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("\n\nTEST\n\n");
                Log.d("btn","le bouton a ete appuyer");
                button_validate.setText("Next");
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
