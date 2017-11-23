package com.axelcollet.tp_android_varo_collet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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
 * Activities that contain this fragment must implement the
 * {@link FragmentVoc.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentVoc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentVoc extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView viewMot;
    EditText viewReponse;

    Button buttonValidate;
    private TupleVocabulaire tv1 = new TupleVocabulaire("work","travail");
    private TupleVocabulaire tv2 = new TupleVocabulaire("play","jouer");
    private TupleVocabulaire tv3 = new TupleVocabulaire("Hello","Bonjour");
    private TupleVocabulaire tv4 = new TupleVocabulaire("credit card","carte de crédit");
    private int posList = 0;
    private TupleVocabulaire tvActuel;

    private List<TupleVocabulaire> tvList = new ArrayList<TupleVocabulaire>(10);


    private OnFragmentInteractionListener mListener;

    public FragmentVoc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentVoc.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentVoc newInstance(String param1, String param2) {
        FragmentVoc fragment = new FragmentVoc();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment




        View rootView = inflater.inflate(R.layout.fragment_fragment_voc, container, false);

        TextView viewMot = rootView.findViewById(R.id.textView);

        EditText viewReponse = rootView.findViewById(R.id.editText);
        Button buttonValidate = rootView.findViewById(R.id.button_validate);

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initTuble(TupleVocabulaire tv){

        viewReponse.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        viewMot.setText(tv.Mot);

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
