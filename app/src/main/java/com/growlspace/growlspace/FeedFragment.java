package com.growlspace.growlspace;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dexafree.materialList.cards.BigImageCard;
import com.dexafree.materialList.cards.WelcomeCard;
import com.dexafree.materialList.view.MaterialListView;
import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Nicholas on 4/25/2015.
 */
public class FeedFragment extends Fragment {
    private MaterialListView mListView;

    public FeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mListView = (MaterialListView) rootView.findViewById(R.id.material_listview);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.attachToRecyclerView(mListView);
        populateList();
        return rootView;
    }

    private void populateList() {
        BigImageCard card;
        Context context = getActivity().getApplicationContext();

        WelcomeCard welcomeCard = new WelcomeCard(context);
        welcomeCard.setTitle("Welcome to GrowlSpace!");
        welcomeCard.setSubtitle("The Sound Social Network");
        welcomeCard.setDescription("Touch the floating action button below to create your first growl, or just scroll your feed.");
        welcomeCard.setButtonText("Okay");
        welcomeCard.setBackgroundColor(getResources().getColor(R.color.primary));
        welcomeCard.setDescriptionColor(getResources().getColor(R.color.white));
        mListView.add(welcomeCard);

        for (int i = 1; i < 11; i++) {
            card = new BigImageCard(context);
            card.setDescription("Hello, for the " + i + " time.");
            card.setTitle("Greeting #" + i);
            mListView.add(card);
        }
    }
}
