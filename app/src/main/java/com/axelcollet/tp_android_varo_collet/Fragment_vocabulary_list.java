package com.axelcollet.tp_android_varo_collet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madoff on 10/12/2017.
 */

public class Fragment_vocabulary_list extends Fragment {


    private ArrayList<CarteVocabulaire> listCards;
    private RecyclerView recyclerView;
    private MyAdapter adapterSongList = new MyAdapter(listCards);

    public static Fragment_vocabulary_list newInstance(ArrayList<CarteVocabulaire> param) {

        Fragment_vocabulary_list myFragment = new Fragment_vocabulary_list();
        Bundle args = new Bundle();
        args.putParcelableArrayList("param_1",param);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View v = inflater.inflate(R.layout.fragment_vocabulary_list, parent, false);
        getBundleArguments(getArguments());
        Log.d("SIZE LIST: ", Integer.toString(listCards.size()));

        recyclerView = v.findViewById(R.id.listRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterSongList);
        MyAdapter mAdapter = new MyAdapter(listCards);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator

        return v;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here

         // Get Arugments given to fragment
        for (CarteVocabulaire cn : listCards) {
            String log = "Id: "+cn.getID()+" ,Francais: " + cn.getTraductionFR() + " ,English: " + cn.getTraductionEN();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }

    private void getBundleArguments(Bundle bundle) {
        if (bundle != null) {
            listCards = bundle.getParcelableArrayList("param_1");
        }else {
            Log.e("Name: ", "Error listCard NULL");
        }
    }

}
