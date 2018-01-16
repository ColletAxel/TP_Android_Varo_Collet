package com.axelcollet.tp_android_varo_collet;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Madoff on 13/12/2017.
 */

public class Fragment_vocabulary_game2 extends Fragment implements View.OnClickListener{

    private Button button_validate;
    private ArrayList<CarteVocabulaire> listCards;
    private enum gameState{PLAYING,NEXT_CARD}

    private EditText player_answer;
    private TextView word_to_find;


    private gameState state = gameState.PLAYING;
    private int current_index_word;
    private CarteVocabulaire current_card_to_find;
    private int Right_answer = 0;
    private int Total_answer = 0;


    public static Fragment_vocabulary_game2 newInstance(ArrayList<CarteVocabulaire> param) {

        Fragment_vocabulary_game2 myFragment = new Fragment_vocabulary_game2();
        Bundle args = new Bundle();
        args.putParcelableArrayList("param_1",param);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment

        View v = inflater.inflate(R.layout.fragment_vocabulary_game, parent, false);
        return v;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        button_validate = view.findViewById(R.id.button_validate);
        button_validate.setOnClickListener(this);
        player_answer = view.findViewById(R.id.EditText_player_answer);
        word_to_find = view.findViewById(R.id.TextView_word_tofind);

        getBundleArguments(getArguments());
        state = gameState.PLAYING;
        setCardToFind();
    }

    private void getBundleArguments(Bundle bundle) {
        if (bundle != null) {
            listCards = bundle.getParcelableArrayList("param_1");
        }else {
            Log.e("Name: ", "Error listCard NULL");
        }
    }

    private void setCardToFind(){
        if(!listCards.isEmpty()){
            current_index_word = new Random().nextInt(listCards.size());
            current_card_to_find = listCards.get(current_index_word);
            word_to_find.setText(current_card_to_find.getTraductionEN());
        }else{
            Log.e("list state ", "EMPTY LIST");
        }
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button_validate:
                Log.d("click: ", "IN CLICKEC Button_addCards");
                if(state == gameState.PLAYING){
                    if(current_card_to_find.getTraductionFR().equalsIgnoreCase(player_answer.getText().toString())){
                        Right_answer++;
                        button_validate.getBackground().setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_ATOP);
                    }else{
                        button_validate.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                    }
                    Total_answer++;
                    state = gameState.NEXT_CARD;
                    button_validate.setText("continue");

                }else if(state == gameState.NEXT_CARD){
                    state = gameState.PLAYING;
                    button_validate.setText("validate");

                    button_validate.getBackground().setColorFilter(Color.parseColor("#DDDDDD"), PorterDuff.Mode.SRC_ATOP); // set color filterter
                    setCardToFind();
                }
                break;
        }
    }
}
