package com.axelcollet.tp_android_varo_collet;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_vocabulary_game extends Fragment {
    private TupleVocabulaire tv1 = new TupleVocabulaire("work","travail");
    private TupleVocabulaire tv2 = new TupleVocabulaire("play","jouer");
    private TupleVocabulaire tv3 = new TupleVocabulaire("Hello","Bonjour");
    private TupleVocabulaire tv4 = new TupleVocabulaire("credit card","carte de crédit");
    private int posList = 0;
    private TupleVocabulaire tvActuel;
    private EditText viewReponse;
    private Button buttonValidate;

    private List<TupleVocabulaire> tvList = new ArrayList<TupleVocabulaire>(10);

    private TextView viewMot;
    public Fragment_vocabulary_game() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_vocabulary_game, container, false);
        final Button buttonValidate = rootView.findViewById(R.id.button_validate);
        viewReponse = rootView.findViewById(R.id.EditText_player_answer);
        viewMot = rootView.findViewById(R.id.TextView_word_tofind);
        initTuble(tvActuel);
        buttonValidate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (buttonValidate.getText() == getResources().getString(R.string.validate)) {
                    buttonValidate.setText(getResources().getString(R.string.next));
                    verifieTuple(tvActuel);
                } else {
                    buttonValidate.setText(getResources().getString(R.string.validate));

                    tvActuel = nextMot();
                    initTuble(tvActuel);
                }
            }
        });
        return rootView;
    }

    private void initTuble(TupleVocabulaire tv){
        viewReponse.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        viewMot.setText("\n\n\n" + tv.Mot);
    }

    private void verifieTuple (TupleVocabulaire tv){

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



