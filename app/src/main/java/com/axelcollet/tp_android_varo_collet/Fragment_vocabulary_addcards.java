package com.axelcollet.tp_android_varo_collet;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Madoff on 13/12/2017.
 */

public class Fragment_vocabulary_addcards extends Fragment implements View.OnClickListener{

    private Button button_addCards;
    private TextView text_traductionFR;
    private TextView text_traductionEN;

    dataFragmentToContainer mCallback;

    // Container Activity must implement this interface for communicate with fragment
    public interface dataFragmentToContainer {
        public void sendNewCard(String traductionFR, String traductionEN);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (dataFragmentToContainer) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View v = inflater.inflate(R.layout.fragment_vocabulary_addcards, parent, false);

        return v;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        button_addCards =  view.findViewById(R.id.button_addCards);
        button_addCards.setOnClickListener(this);
        text_traductionFR = view.findViewById(R.id.EditText_word_FR);
        text_traductionFR.setOnClickListener(this);
        text_traductionEN = view.findViewById(R.id.EditText_word_EN);
        text_traductionEN.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button_addCards:
                Log.d("click: ", "IN CLICKEC Button_addCards");
                mCallback.sendNewCard(text_traductionFR.getText().toString(),text_traductionEN.getText().toString());
                break;
            case R.id.EditText_word_FR:
                Log.d("click: ", "IN CLICKEC EditText_word_FR");
                break;
            case R.id.EditText_word_EN:
                Log.d("click: ", "IN CLICKEC EditText_word_EN");
                break;
        }
    }

}
