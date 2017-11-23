package com.axelcollet.tp_android_varo_collet;

import android.app.Activity;
import android.graphics.Color;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
public class MainActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fragVoc = (FragmentVoc)getSupportFragmentManager().findFragmentById(R.id.fragment_fragment_voc);

        // Create an instance of ExampleFragment
        FragmentVoc fragVoc = new FragmentVoc();

        // In case this activity was started with special instructions from an Intent,
        // pass the Intent's extras to the fragment as arguments
        fragVoc.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_fragment_voc,FragmentVoc.newInstance("lool","lool")).commit();



    }

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

}
// TODO: 15.11.2017 faire classe carton
// TODO: 15.11.2017 faire classe qui contient plusieurs carton genre une liste des ids
// TODO: 15.11.2017 faire activity menu
// TODO: 15.11.2017 passer la liste de carton choisi dans le menu a cette activité
// TODO: 15.11.2017 renomer cette activité ?!?
// TODO: 15.11.2017  stats réussite / echec d'un carton
