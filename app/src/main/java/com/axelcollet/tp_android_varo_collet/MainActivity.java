package com.axelcollet.tp_android_varo_collet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;



// If you want you can add other log definition for info, warning etc
public class MainActivity extends FragmentActivity {

    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_drawer();


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.add(R.id.fragment_fragment_voc,new VocabularyFrament());
        // Complete the changes added above
        ft.commit();


    }

    private void init_drawer(){
        mDrawerList = (ListView) findViewById(R.id.my_drawer);
        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "position = " + String.valueOf(position) + "/ id = " + String.valueOf(id), Toast.LENGTH_SHORT).show();
                if(id == 0){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_fragment_voc,new Fragment_vocabulary_list());
                    ft.commit();
                }
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
