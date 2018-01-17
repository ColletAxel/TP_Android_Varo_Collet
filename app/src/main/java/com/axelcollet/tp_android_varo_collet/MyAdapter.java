package com.axelcollet.tp_android_varo_collet;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Madoff on 15/10/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<CarteVocabulaire> list;
    Context context;
    //ajouter un constructeur prenant en entrée une liste
    public MyAdapter(Context context, List<CarteVocabulaire> list) {
        this.context = context;
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_view,viewGroup,false);
        return new MyViewHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {
        CarteVocabulaire myObject = list.get(position);
        myViewHolder.bind(myObject);

        myViewHolder.setItemLongClickListener(new ItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, int pos) {
                Log.e("updateCardScore ", "LONG CLICK" + String.valueOf(pos));
                ((MainActivity) context).deleteCard(list.get(pos).getID());
                list.remove(pos);
                notifyDataSetChanged();


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}