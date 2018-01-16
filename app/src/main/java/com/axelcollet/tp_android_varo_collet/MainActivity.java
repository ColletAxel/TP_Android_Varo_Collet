package com.axelcollet.tp_android_varo_collet;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


// If you want you can add other log definition for info, warning etc
public class MainActivity extends FragmentActivity implements Fragment_vocabulary_addcards.dataFragmentToContainer{

    private ListView mDrawerList;
    private DatabaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init_drawer();

        db = new DatabaseHandler(this);
        db.addCard(new CarteVocabulaire("bonjour","hello"));
        db.addCard(new CarteVocabulaire("aurevoir","goodbye"));
        Log.e("list state ", String.valueOf(db.isCard_inDataBase("bonjour",DatabaseHandler.Langue.EN)));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.add(R.id.fragment_main_activity,Fragment_vocabulary_game2.newInstance(db.getAllCard()));
        // Complete the changes added above
        ft.commit();
    }

    public void sendNewCard(String traductionFR, String traductionEN) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        db.addCard(new CarteVocabulaire(traductionFR,traductionEN));
    }

    /*Initialisation du drawer. use for navigate within different fragment of activity*/
    private void init_drawer(){
        mDrawerList = (ListView) findViewById(R.id.my_drawer);
        String[] osArray = { "Fragment1", "Fragment2", "Fragment3", "Fragment4", "Fragment5" };
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "position = " + String.valueOf(position) + "/ id = " + String.valueOf(id), Toast.LENGTH_SHORT).show();
                if(id == 0){

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_main_activity,Fragment_vocabulary_game2.newInstance(db.getAllCard()));
                    ft.commit();
                }
                if(id == 1){
                    ArrayList<CarteVocabulaire> listCards = db.getAllCard();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_main_activity,Fragment_vocabulary_list.newInstance(listCards));
                    ft.commit();
                }
                if(id == 2){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_main_activity,new Fragment_vocabulary_addcards());
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
