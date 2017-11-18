package com.axelcollet.tp_android_varo_collet;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private List<TupleVocabulaire> tvList = new ArrayList<TupleVocabulaire>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity mActivity = this;

        final Button buttonValidate = (Button) findViewById(R.id.button_validate);



        tvList.add(tv1);
        tvList.add(tv2);
        tvList.add(tv3);
        tvList.add(tv4);
        tvActuel = tvList.get(posList);

        initTuble(tvActuel);
        buttonValidate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (buttonValidate.getText()==getResources().getString(R.string.validate)){
                    buttonValidate.setText(getResources().getString(R.string.next));

                    verifieTuple(tvActuel);
                }else{
                    buttonValidate.setText(getResources().getString(R.string.validate));

                    tvActuel = nextMot();
                    initTuble(tvActuel);
                }


            }
        });

    }

    private void initTuble(TupleVocabulaire tv){
        final TextView viewMot = (TextView)findViewById(R.id.textView);
        final EditText viewReponse = (EditText)findViewById(R.id.editText);
        viewReponse.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        viewMot.setText(tv.Mot);

    }

    private void verifieTuple (TupleVocabulaire tv){
        final EditText viewReponse = (EditText)findViewById(R.id.editText);
        final TextView viewMot = (TextView)findViewById(R.id.textView);
        if (tv.VerifieTuble(viewMot.getText().toString(),viewReponse.getText().toString())){
            viewReponse.setBackgroundColor(Color.GREEN);
        }else{
            viewReponse.setBackgroundColor(Color.RED);
            viewReponse.setText(tv.MotTraduit);

        }
    }

    private TupleVocabulaire nextMot(){
        posList++;

        if (!(posList< tvList.size())){
            posList =0;
        }
        return tvList.get(posList);
    }
}
// TODO: 15.11.2017 faire classe carton
// TODO: 15.11.2017 faire classe qui contient plusieurs carton genre une liste des ids
// TODO: 15.11.2017 faire activity menu
// TODO: 15.11.2017 passer la liste de carton choisi dans le menu a cette activité
// TODO: 15.11.2017 renomer cette activité ?!?
// TODO: 15.11.2017  stats réussite / echec d'un carton
