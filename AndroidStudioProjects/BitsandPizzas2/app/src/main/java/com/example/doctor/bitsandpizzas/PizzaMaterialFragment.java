package com.example.doctor.bitsandpizzas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.bitsandpizzas.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PizzaMaterialFragment extends Fragment {


    public PizzaMaterialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pizzaRecycler=(RecyclerView) inflater.inflate(R.layout.fragment_pizza_material,container,false);
        String [] pizzaNames=new String[Pizza.pizzas.length];
        for (int i=0;i<pizzaNames.length;i++){
            pizzaNames[i]=Pizza.pizzas[i].getName();
        }
        int [] pizzaImages=new int[Pizza.pizzas.length];
        for (int i=0;i<pizzaImages.length;i++){
            pizzaImages[i]=Pizza.pizzas[i].getImageResourceId();
        }
        CaptionedImagesAdapter adapter=new CaptionedImagesAdapter(pizzaNames,pizzaImages);
        pizzaRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        pizzaRecycler.setLayoutManager(layoutManager);
        return pizzaRecycler;
    }

}
