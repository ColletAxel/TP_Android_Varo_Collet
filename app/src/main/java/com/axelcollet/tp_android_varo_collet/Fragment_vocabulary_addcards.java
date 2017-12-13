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
import android.widget.Toast;


/**
 * Created by Madoff on 13/12/2017.
 */

public class Fragment_vocabulary_addcards extends Fragment implements View.OnClickListener{

    Button button_addCards;


    OnHeadlineSelectedListener mCallback;

    // Container Activity must implement this interface for communicate with fragment
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(String traductionFR, String traductionEN);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View v = inflater.inflate(R.layout.fragment_vocabulary_addcards, parent, false);
        button_addCards =  v.findViewById(R.id.button_addCards);
        button_addCards.setOnClickListener(this);
        return v;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here

    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button_addCards:
                Log.d("click: ", "IN CLICKEC Button_addCards");
                mCallback.onArticleSelected("chat","cat");
                break;
        }
    }

}
