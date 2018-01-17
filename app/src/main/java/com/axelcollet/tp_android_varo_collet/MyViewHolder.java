package com.axelcollet.tp_android_varo_collet;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Madoff on 15/10/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

    private TextView textViewView1;
    private TextView textViewView2;
    private TextView textView_list_score;
    ItemLongClickListener itemLongClickListener;

    //itemView est la vue correspondante à 1 cellule
    public MyViewHolder(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView
        textViewView1 = (TextView) itemView.findViewById(R.id.traductionFR);
        textViewView2 = (TextView) itemView.findViewById(R.id.traductionEN);
        textView_list_score = itemView.findViewById(R.id.textView_list_score);
        itemView.setOnLongClickListener(this);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(CarteVocabulaire myObject){
        textViewView1.setText(myObject.getTraductionFR());
        textViewView2.setText(myObject.getTraductionEN());
        textView_list_score.setText(String.valueOf(myObject.getScore()));

    }

    public void setItemLongClickListener(ItemLongClickListener ic)
    {
        this.itemLongClickListener=ic;
    }

    @Override
    public boolean onLongClick(View v) {
        this.itemLongClickListener.onItemLongClick(v,getLayoutPosition());
        return false;
    }
}