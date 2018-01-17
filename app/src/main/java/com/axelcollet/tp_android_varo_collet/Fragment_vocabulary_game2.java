package com.axelcollet.tp_android_varo_collet;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Paint;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Madoff on 13/12/2017.
 */

public class Fragment_vocabulary_game2 extends Fragment implements View.OnClickListener{

    private Button button_validate;
    private ArrayList<CarteVocabulaire> listCards;
    private enum gameState{PLAYING,NEXT_CARD,UNPLAYABLE}

    private EditText player_answer;
    private TextView word_to_find;
    private ProgressBar progress_bar_game;


    private gameState state = gameState.PLAYING;
    private int current_index_word;
    private CarteVocabulaire current_card_to_find;
    private int Right_answer = 0;
    private int Total_answer = 0;

    dataFragmentGameToContainer mCallback;


    public static Fragment_vocabulary_game2 newInstance(ArrayList<CarteVocabulaire> param) {

        Fragment_vocabulary_game2 myFragment = new Fragment_vocabulary_game2();
        Bundle args = new Bundle();
        args.putParcelableArrayList("param_1",param);
        myFragment.setArguments(args);
        return myFragment;
    }

    // Container Activity must implement this interface for communicate with fragment
    public interface dataFragmentGameToContainer {
        public void updateCardScore(int id, int score);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (dataFragmentGameToContainer) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener");
        }
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

        progress_bar_game = view.findViewById(R.id.ProgressBarGame);

        getBundleArguments(getArguments());
        setCardToFind();

    }

    private void getBundleArguments(Bundle bundle) {
        if (bundle != null) {
            listCards = bundle.getParcelableArrayList("param_1");
            state = gameState.PLAYING;
            Log.e("getBundleArguments ", String.valueOf(listCards.size()));
        }else {
            Log.e("Name: ", "Error listCard NULL");
            state = gameState.UNPLAYABLE;
        }
    }

    private void setCardToFind(){
        if(!listCards.isEmpty()){
            Log.e("setCardTofind ", String.valueOf(listCards.size()));
            current_index_word = new Random().nextInt(listCards.size());
            current_card_to_find = listCards.get(current_index_word);
            word_to_find.setText(current_card_to_find.getTraductionEN());
            state = gameState.PLAYING;
        }else{
            Log.e("list state ", "EMPTY LIST");
            state = gameState.UNPLAYABLE;
        }
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button_validate:
                Log.d("click: ", "IN CLICKEC Button_addCards");

                if(state == gameState.PLAYING){
                    Total_answer++;
                    progress_bar_game.setMax(Total_answer);     // Mise à jours de la barre de score
                    progress_bar_game.setSecondaryProgress(Total_answer);
                    if(current_card_to_find.getTraductionFR().equalsIgnoreCase(player_answer.getText().toString())){
                        Right_answer++;
                        int new_card_score = current_card_to_find.getScore();
                        if(new_card_score > 9){
                            new_card_score = 10;
                        }else{
                            new_card_score += 1;
                        }
                        listCards.get(current_index_word).setScore(new_card_score);
                        mCallback.updateCardScore(current_card_to_find.getID(),new_card_score);
                        button_validate.getBackground().setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_ATOP);
                    }else{
                        int new_card_score = current_card_to_find.getScore();
                        if(new_card_score < -9){
                            new_card_score = -10;
                        }else{
                            new_card_score -= 1;
                        }
                        player_answer.setPaintFlags(player_answer.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        word_to_find.append("\n Réponse : " + current_card_to_find.getTraductionFR());
                        listCards.get(current_index_word).setScore(new_card_score);
                        mCallback.updateCardScore(current_card_to_find.getID(),new_card_score);
                        button_validate.getBackground().setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                    }

                    progress_bar_game.setProgress(Right_answer);// Mise à jours de la barre de score

                    state = gameState.NEXT_CARD;
                    button_validate.setText(getString(R.string.button_game_continue));

                }else if(state == gameState.NEXT_CARD){
                    state = gameState.PLAYING;
                    player_answer.setPaintFlags(0);
                    player_answer.setText("");
                    button_validate.setText(R.string.button_game_validation);
                    button_validate.getBackground().setColorFilter(Color.parseColor("#DDDDDD"), PorterDuff.Mode.SRC_ATOP); // set color filterter
                    setCardToFind();
                }else if(state == gameState.UNPLAYABLE){
                    Toast.makeText(getActivity(), "Vous n’avez aucun mots d’enregistré ", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
